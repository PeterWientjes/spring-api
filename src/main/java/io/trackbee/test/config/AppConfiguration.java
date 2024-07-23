package io.trackbee.test.config;

import io.trackbee.test.filter.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;

    /**
     * Register the custom authentication filter for all order calls ({@link io.trackbee.test.controller.OrderController}
     */
    @Bean
    public FilterRegistrationBean<CustomAuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<CustomAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(customAuthenticationFilter);
        registrationBean.addUrlPatterns("/order/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
