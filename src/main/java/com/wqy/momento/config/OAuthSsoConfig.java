//package com.wqy.momento.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableOAuth2Sso // 开启 Sso 功能
//public class OAuthSsoConfig {// extends WebSecurityConfigurerAdapter {
////    @Override
////    public void configure(HttpSecurity http) throws Exception {
////        http.authorizeRequests()
//////                .antMatchers("/login").permitAll()
////                .anyRequest().authenticated()
////                .and().formLogin().permitAll()
////        ;
////    }
//
////    @Bean
////    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        return http.
////                antMatcher("/**")
////                .authorizeRequests(authorize -> authorize
////                        .antMatchers("/login").permitAll()
////                        .anyRequest().authenticated()
////                )
////                .build();
////    }
//}