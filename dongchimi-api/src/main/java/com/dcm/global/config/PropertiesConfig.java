package com.dcm.global.config;

import com.dcm.global.properties.OAuthGoogleProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(OAuthGoogleProperties.class)
public class PropertiesConfig {
}
