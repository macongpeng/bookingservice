package com.grandia.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.beans.factory.annotation.Value

@Configuration
class SecurityConfig(
    @Value("\${spring.mail.host}") private val host: String = "",
    @Value("\${spring.mail.port}") private val port: Int = 0,
    @Value("\${spring.mail.username}") private val username: String = "",
    @Value("\${spring.mail.password}") private val password: String =""  
) {

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun mailSender(): JavaMailSender {
     
        return JavaMailSenderImpl().apply {
            host = this@SecurityConfig.host
            port = this@SecurityConfig.port
            username = this@SecurityConfig.username
            password = this@SecurityConfig.password
        }
    }
}