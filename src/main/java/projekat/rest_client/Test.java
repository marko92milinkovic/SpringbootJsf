/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.rest_client;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import static org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import projekat.domen.Klijent;
import projekat.domen.Mesto;

/**
 *
 * @author marko
 */
public class Test {

    public static void main2(String[] args) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {

        String uri = "https://localhost:8443/secure/rest/places";
        RestTemplateFactory factory = new RestTemplateFactory();
        factory.afterPropertiesSet();
        RestTemplate restTemplate = factory.getObject();
//        restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<T>(createHeaders("marko", "marko")), List.class);
        HttpClient httpClient = factory.getAuth().getHttpClient();
        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
            @Override
            public boolean isTrusted(X509Certificate[] certificate, String authType) {
                return true;
            }
        };
        SSLSocketFactory sf
                = new SSLSocketFactory(acceptingTrustStrategy, ALLOW_ALL_HOSTNAME_VERIFIER);
        httpClient.getConnectionManager().getSchemeRegistry()
                .register(new Scheme("https", 8443, sf));
        ResponseEntity<List> exchange = restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<>(createHeaders("marko", "marko")), List.class);

        List body = exchange.getBody();
        for (Object object : body) {
            System.out.println("Mesto: " + object);
        }

        System.out.println("******************");
        ResponseEntity<List> forEntity = restTemplate.getForEntity(uri, List.class);
        List body1 = forEntity.getBody();
        for (Object object : body1) {
            System.out.println("Mesto: " + object);
        }

    }

    static HttpHeaders createHeaders(String username, String password) {
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

    public static void mainsss(String[] args) throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, KeyManagementException, Exception {
//        String uri = "https://localhost:8443/secure/rest/clients/search/as";

        RestTemplateFactory factory = new RestTemplateFactory();
        factory.afterPropertiesSet();


        String uri = "https://localhost:8443/secure/rest/places";
        String uri2 = "https://localhost:8443/secure/rest/clients/search/8";

        Response<List<Mesto>> executeGetRequest = factory.executeRequest(uri, (Class<List<Mesto>>) (Object) List.class, HttpMethod.GET);

        Response<LinkedList<Klijent>> executeRequest = factory.executeRequest(uri2,(Class<LinkedList<Klijent>>) (Object) LinkedList.class , HttpMethod.GET);
        List<Klijent> data = executeRequest.getData();
        System.out.println("Tip liste: "+data.getClass().getSimpleName());
        for (Klijent klijent : data) {
            System.out.println("Klijent: "+klijent.getIme());
        }
        
        List<Mesto> mesta = executeGetRequest.getData();
        System.out.println(mesta);
        for (Mesto m : mesta) {
            System.out.println("Mesto: "+m);
        }
    }

    public static void main2312(String[] args) {
        Klijent klijent = new Klijent();
        Mesto mesto = new Mesto(11000);
        klijent.setIme("ASDsasadsa");
        klijent.setPrezime("sasadsadsaasd");
        klijent.setJmbg("7852145269852");
        klijent.setMail("sdsadsadsa");
        klijent.setTelefon("sadsasad");
        klijent.setAdresa("asdssad");
        String uri = "https://localhost:8443/secure/rest/clients/new";
        klijent.setMesto(mesto);

        RestTemplateFactory factory = new RestTemplateFactory();
        factory.afterPropertiesSet();
        RestTemplate restTemplate = factory.getObject();
        
//        try {
//            Response<ArrayList> executePostRequest = factory.executeRequest(uri, new ArrayList<Mesto>());
//            System.out.println(executePostRequest.getStatusCode());
//            System.out.println(executePostRequest.getStatusMessage());
//        } catch (Exception ex) {
//ex.printStackTrace();
//        }
        
    }

}
