package com.consultorio.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration

@Configuration
class AuthConfig {
    
    @Bean
    fun primaryAuthManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}
