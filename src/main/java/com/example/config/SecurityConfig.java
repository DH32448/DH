package com.example.config;

import com.example.entity.RestBean;
import com.example.filter.JwtAuthenticationFilter;
import com.example.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

@Slf4j
@Configuration
public class SecurityConfig {
    @Resource
    @Lazy
    AccountService accountService;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    /**
     * Security 6.x
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth ->{
                    auth
                            .requestMatchers(
                                    new AntPathRequestMatcher("/api/p/**"),
                                    new AntPathRequestMatcher("/api/acc/create/**"),
                                    new AntPathRequestMatcher("/api/email/**")
//                                    new AntPathRequestMatcher("/api/hi/**")
                            ).permitAll()
                            .requestMatchers(
                                    new AntPathRequestMatcher("/api/auth/*")
                            ).hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .formLogin(config ->{
                    config.loginProcessingUrl("/api/login").permitAll()
                            .successHandler(this::onAuthenticationSuccess)
                            .failureHandler(this::onAuthenticationFailure)
                            .permitAll();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(config -> config.disable())
                .sessionManagement(conf -> conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("用户：{}，登录服务器",authentication.getName());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        log.info("权限：{}",authorities);
        Object credentials = authentication.getCredentials();
        log.info("凭证：{}",credentials);
        Object details = authentication.getDetails();
        log.info("认证请求：{}",details);
        Object principal = authentication.getPrincipal();
        log.info("认证主体：{}",principal);
        boolean authenticated = authentication.isAuthenticated();
        log.info("认证情况：{}",authenticated);
        //手动设置认证状态
//        authentication.setAuthenticated(true);
        String name = authentication.getName();
        log.info("用户名：{}",name);
        writer.write("登录成功");
        writer.flush();
        log.info("onAuthenticationSuccess 完成");
    }
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(RestBean.failure(401,"登录失败").asJsonString());
        writer.flush();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
