package com.homics.monolith.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserFilter implements Filter {
    private static final String USER_HEADER_NAME = "user";

    public UserFilter() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String userName = request.getHeader(USER_HEADER_NAME);
        UserStore.setUserName(userName);
        chain.doFilter(req, res);
    }
}
