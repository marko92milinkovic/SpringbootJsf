/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import projekat.domen.Klijent;
import projekat.domen.Zaduzenje;
import projekat.rest_client.Response;
import projekat.rest_client.RestTemplateFactory;
import projekat.services.KlijentService;

/**
 *
 * @author marko
 */
@Service("klijentServiceRESTImpl")
public class KlijentServiceRESTImpl implements KlijentService {

    @Autowired
    RestTemplateFactory restTemplateFactory;

    @Override
    public void sacuvaj(Klijent klijent) throws Exception {
        String uri = "https://localhost:8443/secure/rest/clients/new";
        System.out.println("Velicina liste:" + klijent.getZaduzenjeList());
        Response<String> response = restTemplateFactory.executeRequest(uri, String.class, HttpMethod.POST, klijent);
        if (response.getStatusCode() != 0) {
            throw new Exception(response.getStatusMessage());
        }
    }

    @Override
    public List<Klijent> pronadjiKlijente(String kljuc) throws Exception {
        String uri = "https://localhost:8443/secure/rest/clients/search/" + kljuc;

        Response<List<Klijent>> executeRequest = restTemplateFactory.executeRequest(uri, (Class<List<Klijent>>) (Object) List.class, HttpMethod.GET);
        System.out.println("VRACEBO:" + executeRequest.getData().get(0).getZaduzenjeList());
        if (executeRequest.getStatusCode() == 0) {
            return executeRequest.getData();
        }
        throw new Exception(executeRequest.getStatusMessage());
    }

    @Override
    public void obrisi(Klijent klijent) throws Exception {
        String uri = "https://localhost:8443/secure/rest/clients/" + klijent.getKlijentID();
        Response<String> response = restTemplateFactory.executeRequest(uri, String.class, HttpMethod.DELETE);
        if (response.getStatusCode() != 0) {
            throw new Exception(response.getStatusMessage());
        }
    }

    @Override
    public void izmeniKlijenta(Klijent klijent) throws Exception {
        String uri = "https://localhost:8443/secure/rest/clients/";
        klijent.setZaduzenjeList(klijent.getZaduzenjeList());
        Response<String> response = restTemplateFactory.executeRequest(uri, String.class, HttpMethod.PUT,
                new LinkedList<>(Arrays.asList(klijent)));
        if (response.getStatusCode() != 0) {
            throw new Exception(response.getStatusMessage());
        }
    }

}
