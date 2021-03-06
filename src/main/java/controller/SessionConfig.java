package controller;

import org.springframework.context.annotation.Bean;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * Created by JasonFitch on 8/31/2020.
 */
//@EnableRedisHttpSession
@EnableSpringHttpSession
public class SessionConfig {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookieName("JSESSIONID");
        defaultCookieSerializer.setCookiePath("/");
        //spring session 中读取到的 jvm route 信息
        defaultCookieSerializer.setJvmRoute("spring1");
        defaultCookieSerializer.setUseBase64Encoding(false);
        return defaultCookieSerializer;
    }

}
