/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 18/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.dashboard.dataservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ApplicationSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()
                .csrf().disable()
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/_/health").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer().jwt();
        return http.build();
    }

}
