package com.logicalsapien.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);

        boolean isRolesPreset = false;
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("READ".equals(auth.getAuthority()) || "WRITE".equals(auth.getAuthority()) || "ADMIN".equals(auth.getAuthority())) {
                response.sendRedirect("/list-galaxies");
                break;
            }
        }
    }

}