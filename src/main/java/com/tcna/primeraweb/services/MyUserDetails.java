package com.tcna.primeraweb.services;

import com.tcna.primeraweb.models.Role;
import com.tcna.primeraweb.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {

    private User user;

    //M3todo que nos obtiene los roles de un usuario
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getNombre()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
