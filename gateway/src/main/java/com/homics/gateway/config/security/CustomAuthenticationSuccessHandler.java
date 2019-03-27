package com.homics.gateway.config.security;

import com.homics.gateway.service.UserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handle Authentication success and save the username in the DB
 */
class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserActivityService userActivityService;

    public CustomAuthenticationSuccessHandler(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        logger.info("User <{}> logged in.", username);
        userActivityService.addLogin(username);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
