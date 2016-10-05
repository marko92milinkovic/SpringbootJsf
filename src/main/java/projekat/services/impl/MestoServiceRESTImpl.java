/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.services.impl;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import projekat.domen.Mesto;
import projekat.rest_client.Response;
import projekat.rest_client.RestTemplateFactory;
import projekat.services.MestoService;

/**
 *
 * @author marko
 */
@Service("mestoServiceRestImpl")
public class MestoServiceRESTImpl implements MestoService {

    @Autowired
    RestTemplateFactory restTemplateFactory;

    @Override
    public List<Mesto> vratiSvaMesta() throws Exception {
        String uri = "https://localhost:8443/secure/rest/places";

        Response<LinkedList<Mesto>> executeGetRequest = restTemplateFactory.executeRequest(uri, (Class<LinkedList<Mesto>>) (Object) LinkedList.class, HttpMethod.GET);

        if (executeGetRequest.getStatusCode() == 0) {
            return executeGetRequest.getData();
        }
        throw new Exception(executeGetRequest.getStatusMessage());
    }

    @Override
    public Mesto vratiMesto(int ptt) {
        try {
            String uri = "https://localhost:8443/secure/rest/places/" + ptt;

            Response<Mesto> executeGetRequest = restTemplateFactory.executeRequest(uri, Mesto.class, HttpMethod.GET);
            if (executeGetRequest.getStatusCode() == 0) {
                return executeGetRequest.getData();
            }
            throw new RuntimeException(executeGetRequest.getStatusMessage());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

}
