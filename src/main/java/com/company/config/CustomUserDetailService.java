package com.company.config;
//User :Lenovo
//Date :02.07.2022
//Time :17:07
//Project Name :YouTobe_Security

import com.company.entity.CompanyEntity;
import com.company.entity.ProfileEntity;
import com.company.repository.CompanyRepository;
import com.company.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        if (username.startsWith("uz_card")){
            Optional<ProfileEntity> profileEntity= profileRepository.findByUsername(username);

            if (profileEntity.isEmpty()){
                log.error("Profile not found by username");
                throw new UsernameNotFoundException("User not found 1");

            }
            ProfileEntity profile = profileEntity.get();

            return new CustomUserDetails(profile.getId(), profile.getUsername(),profile.getStatus(),profile.getRole(),profile.getPassword(),profile.getVisible());
        }
        else {

            Optional<CompanyEntity> company= companyRepository.findByUsername(username);

            if (company.isEmpty()){
                log.error("Profile not found by username");
                throw new UsernameNotFoundException("User not found 2");

            }
            CompanyEntity companyEntity = company.get();
            return new CustomUserDetails(companyEntity.getId(), companyEntity.getUsername(),companyEntity.getStatus(),companyEntity.getRole(),companyEntity.getPassword(),companyEntity.getVisible());

        }




    }
}
