package config;

import filter.BESLogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JasonFitch on 12/5/2018.
 */

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registrationBESLogFilter() {

        System.out.println("FilterRegistrationBean registrationBESLogFilter");

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new BESLogFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("BESLogFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

}
