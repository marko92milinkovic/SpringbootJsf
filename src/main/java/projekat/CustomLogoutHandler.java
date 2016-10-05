/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import projekat.mb.MbRadnik;

/**
 *
 * @author marko
 */
@Service("customLogoutHandler")
public class CustomLogoutHandler implements LogoutHandler {
    
    @Autowired
    MbRadnik mbRadnik;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        mbRadnik.izlogujSe();
//logika pri odjavi radnik
    }
}
