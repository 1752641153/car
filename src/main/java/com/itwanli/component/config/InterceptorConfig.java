package com.itwanli.component.config;

import com.itwanli.component.interceptor.AdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Bean
    public AdminInterceptor authInterceptor() {
        return new AdminInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList();
        patterns.add("/webjars/**");
        patterns.add("/druid/**");
        patterns.add("/sys/login");
        patterns.add("/swagger/**");
        patterns.add("/v2/api-docs");
        patterns.add("/swagger-ui.html");
        patterns.add("/swagger-resources/**");
        patterns.add("/park/login");
        patterns.add("/**");

        registry.addInterceptor(authInterceptor()).addPathPatterns("/**").excludePathPatterns(patterns);
    }
}

