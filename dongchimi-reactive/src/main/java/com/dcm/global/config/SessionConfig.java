package com.dcm.global.config;

import com.dcm.session.manager.SessionManager;
import com.dcm.session.strategy.SessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class SessionConfig {

    @Bean
    public SessionStrategy sessionStrategy(Set<SessionManager> managers) {
        return new SessionStrategy(managers);
    }

}
