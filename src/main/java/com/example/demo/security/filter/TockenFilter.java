package com.example.demo.security.filter;

import com.example.demo.exception.AuthException;
import com.example.demo.security.SecurityService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TockenFilter implements Filter {

    private final SecurityService securityService;

    @Override
    public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        var  token = req.getHeader("Bearer");
        if (token == null || token.isBlank()) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Bearer token is required");
            return;
        }
        if (!securityService.isTokenValid(token)) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return;
        }
        chain.doFilter(request, response);
    }

    // other methods 
}