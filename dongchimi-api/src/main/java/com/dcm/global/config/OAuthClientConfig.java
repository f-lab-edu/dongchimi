package com.dcm.global.config;

import com.dcm.auth.domain.OAuthClient;
import com.dcm.auth.service.OAuthClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class OAuthClientConfig {

    @Bean
    public OAuthClients oAuthClients(Set<OAuthClient> clients) {
        return new OAuthClients(clients);
    }

}
