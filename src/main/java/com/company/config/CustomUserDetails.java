package com.company.config;
//User :Lenovo
//Date :02.07.2022
//Time :17:30
//Project Name :YouTobe_Security

import com.company.entity.ProfileEntity;
import com.company.enums.GeneralRole;
import com.company.enums.GeneralStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

@Service
@Slf4j
public class CustomUserDetails implements UserDetails {


    private String id;
    private String username;
    private GeneralStatus status;
    private GeneralRole role;
    private String password;
    private boolean visible;


    public CustomUserDetails() {
    }

    public CustomUserDetails(String id,String username, GeneralStatus status, GeneralRole role, String password, boolean visible) {

        this.id = id;
        this.username = username;
        this.status = status;
        this.role = role;
        this.password = password;
        this.visible = visible;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return new LinkedList<>(Collections.singletonList(new SimpleGrantedAuthority(role.name())));
    }


    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (status.equals(GeneralStatus.ACTIVE)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return visible;
    }


    public String getId() {
        return id;
    }
}
