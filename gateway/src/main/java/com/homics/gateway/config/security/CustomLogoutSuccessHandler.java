package com.homics.gateway.config.security;

import com.homics.gateway.service.UserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handle Logout success and save logout user activity in the DB
 */
class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserActivityService userActivityService;

    public CustomLogoutSuccessHandler(UserActivityService userActivityService) {
        this.userActivityService = userActivityService;
    }

    @Override
    public void onLogoutSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        logger.info("User <{}> logged out.", username);
        userActivityService.addLogout(username);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
