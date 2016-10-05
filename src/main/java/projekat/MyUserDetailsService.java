/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekat;

import java.util.Collection;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import projekat.domen.Radnik;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projekat.mb.MbRadnik;
import projekat.services.RadnikService;

/**
 *
 * @author marko
 */
@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    RadnikService radnikService;

    @Autowired
    MbRadnik mbRadnik;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username == null || username.equals("")) {
            return null;
        }
        final Radnik radnik = radnikService.pronadjiRadnika(username);
        mbRadnik.setRadnik(radnik);
        mbRadnik.setUlogovan(true);
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                HashSet hashSet = new HashSet<>();
                hashSet.add((GrantedAuthority) () -> "ROLE_ADMIN");
                return hashSet;
            }

            @Override
            public String getPassword() {
                return radnik.getKorisnickaSifra();
            }

            @Override
            public String getUsername() {
                return radnik.getKorisnickoIme();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }

}
