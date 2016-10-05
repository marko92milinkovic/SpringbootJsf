/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat.rest_client;

/**
 *
 * @author marko
 */
public class Response<T> {

    private  Integer statusCode;
    private  String statusMessage;
    private  T data;

    public Response(Integer statusCode, String statusMessage, T data) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public T getData() {
        return data;
    }

    public Response() {

    }

}
