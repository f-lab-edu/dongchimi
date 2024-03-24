package com.dcm.global.config;

import com.dcm.auth.domain.OAuthHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpInterfaceConfig {

    @Bean
    public OAuthHttpClient oAuthHttpClient() {
        WebClient webClient = WebClient.create();
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient)).build();

        return proxyFactory.createClient(OAuthHttpClient.class);
    }
}
