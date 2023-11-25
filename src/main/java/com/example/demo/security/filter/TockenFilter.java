package com.example.demo.security.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
//@Order(1)
public class TockenFilter implements Filter {

    @Override
    public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
//        LOG.info(
//          "Starting a transaction for req : {}",
//          req.getRequestURI());
 
        chain.doFilter(request, response);
//        LOG.info(
//          "Committing a transaction for req : {}",
//          req.getRequestURI());
    }

    // other methods 
}