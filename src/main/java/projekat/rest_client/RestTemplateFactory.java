/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.rest_client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.apache.http.HttpHost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import projekat.domen.Klijent;
import projekat.domen.Mesto;
import projekat.mb.MbRadnik;

/**
 *
 * @author marko
 */
@Component
public class RestTemplateFactory implements FactoryBean<RestTemplate>, InitializingBean {

    private RestTemplate restTemplate;
    private HttpComponentsClientHttpRequestFactoryBasicAuth auth;

    @Autowired
    MbRadnik mbRadnik;

    private Map<Class, ParameterizedTypeReference> types;
    @Value("${rest.hostname}")
    private String rest_hostname;

    @Value("${rest.host.port}")
    private String res_host_port;

    @Value("${rest.keystore}")
    private String rest_keystore;

    @Value("${rest.keystore.password}")
    private String rest_keystore_password;

    @Override
    public RestTemplate getObject() {
        return restTemplate;
    }

    public HttpComponentsClientHttpRequestFactoryBasicAuth getAuth() {
        return auth;
    }

    @Override
    public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() {

        fillTypesForRestService();
        //za potrebe testirnja
        if (rest_keystore == null || "".equals(rest_keystore)) {
            rest_keystore = "/etc/keystores/nst2.jks";
            rest_keystore_password = "changeit";
            res_host_port = "8443";
            rest_hostname = "localhost";
        }
        InputStream keyStoreInputStream = null;
        try {
            keyStoreInputStream = new FileInputStream(rest_keystore);
            if (keyStoreInputStream == null) {
                throw new FileNotFoundException("");
            }
            final KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            try {
                trustStore.load(keyStoreInputStream, rest_keystore_password.toCharArray());
            } finally {
                keyStoreInputStream.close();
            }
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf
                    = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                            SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            auth
                    = new HttpComponentsClientHttpRequestFactoryBasicAuth(new HttpHost(rest_hostname, Integer.parseInt(res_host_port), "https"), httpClient);
            auth.setConnectTimeout(60000);
            auth.setReadTimeout(180000);
            restTemplate = new RestTemplate(auth);
        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException | KeyManagementException ex) {
            Logger.getLogger(RestTemplateFactory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                keyStoreInputStream.close();
            } catch (Exception ex) {
                Logger.getLogger(RestTemplateFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public <T> Response<T> executeRequest(String uri, Class<T> responseType,
            HttpMethod httpMethod, Object bodyData) throws Exception {

        ResponseEntity exchange = restTemplate.exchange(uri,
                httpMethod, new HttpEntity<>(bodyData, createHeaders("marko", "marko")),
                types.get(responseType));
        if (exchange.getStatusCode().is2xxSuccessful()) {
            return (Response<T>) exchange.getBody();
        }
        throw new Exception(exchange.getStatusCode().toString());
    }

    public <T> Response<T> executeRequest(String uri, Class<T> responseType,
            HttpMethod httpMethod) throws Exception {
        ParameterizedTypeReference get = types.get(responseType);
        String typeName = get.getType().getTypeName();
        System.out.println(typeName);
        ResponseEntity exchange = restTemplate.exchange(uri,
                httpMethod, new HttpEntity<>(createHeaders("marko", "marko")),
                types.get(responseType));
        if (exchange.getStatusCode().is2xxSuccessful()) {
            return (Response<T>) exchange.getBody();
        }
        throw new Exception(exchange.getStatusCode().toString());
    }

    private HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {
            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(
                        auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
            }
        };
    }

    private void fillTypesForRestService() {
        types = new HashMap<>();
        types.put((Class<LinkedList<Mesto>>) (Object) LinkedList.class, new ParameterizedTypeReference<Response<LinkedList<Mesto>>>() {
        });
        types.put(Mesto.class, new ParameterizedTypeReference<Response<Mesto>>() {
        });
        types.put(Klijent.class, new ParameterizedTypeReference<Response<Klijent>>() {
        });

        types.put(String.class, new ParameterizedTypeReference<Response<String>>() {
        });
        types.put((Class<List<Klijent>>) (Class<?>) List.class, new ParameterizedTypeReference<Response<List<Klijent>>>() {
        });

    }

}
