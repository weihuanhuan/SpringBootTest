package config;

import javax.xml.transform.Templates;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by JasonFitch on 2/14/2019.
 */
//JF 不知道本 WebMvcConfigurer 接口的使用方法，其2.X的接口中都是default方法，1.X则是抽象的
//    即使不实现这个类，spring boot 也有默认的配置来实现视图解析，那么默认是如何配置的呢？
//@Configuration
public class WebMvcConfig /* implements WebMvcConfigurer  */ {

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setPrefix("/");
//        internalResourceViewResolver.setSuffix(".jsp");
//        internalResourceViewResolver.setExposeContextBeansAsAttributes(true);
//        registry.viewResolver(internalResourceViewResolver);
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/view").setViewName("index");
//    }


}
