//package com.example.demo.security;
//
//import com.example.demo.security.filter.TockenFilter;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final SecurityService securityService;
//
//    @Bean
//    public FilterRegistrationBean<TockenFilter> loggingFilter(){
//        FilterRegistrationBean<TockenFilter> registrationBean
//                = new FilterRegistrationBean<>();
//
//        registrationBean.setFilter(new TockenFilter(securityService));
//        registrationBean.addUrlPatterns("/api/v1/*");
////        registrationBean.setOrder(2);
//
//        return registrationBean;
//    }
//}
