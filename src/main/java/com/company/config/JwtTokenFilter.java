package com.company.config;
//User :Lenovo
//Date :04.07.2022
//Time :16:52
//Project Name :Kun.uz_rest_app

import com.company.entity.CompanyEntity;
import com.company.entity.ProfileEntity;
import com.company.service.CompanyService;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    @Lazy
    private ProfileService profileService;
    @Autowired
    @Lazy
    private CompanyService companyService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String token = header.split(" ")[1].trim();
            String id = JwtUtil.decode(token);
            UserDetails userDetails = null;




            if (profileService.hasById(id)) {
                ProfileEntity profile = profileService.getForJwt(id);
                userDetails = new CustomUserDetails(profile.getId(),profile.getUsername(), profile.getStatus(), profile.getRole(), profile.getPassword(), profile.getVisible());
            }

            if (companyService.hasById(id)) {
                CompanyEntity company = companyService.getForJwt(id);
                userDetails = new CustomUserDetails(company.getId(), company.getUsername(), company.getStatus(), company.getRole(), company.getPassword(), company.getVisible());
            }





            assert userDetails != null;
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (JwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.addHeader("Message", "Some Error");//orqaga qaytarmoqdamiz chunki xatolik bor frontend da
            return;
        }


    }
}
