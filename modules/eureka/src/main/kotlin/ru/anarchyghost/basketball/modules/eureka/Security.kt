package ru.anarchyghost.basketball.modules.eureka

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

//TODO: replace
@EnableWebSecurity
internal class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().ignoringAntMatchers("/eureka/**")
        super.configure(http)
    }
}