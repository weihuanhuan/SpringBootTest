package hello;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

//指定扫描config包，否则不会实例化该包下的bean，便无法注册相应的配置项
//@ComponentScan("config")
//默认会扫描hello包（启动包），但是由于指定了扫描config包，导致默认值被指定值覆盖了,不会扫描hello，使其中的bean无法初始化
//java.lang.annotation.Repeatable这个原注解使得一个注解可以多次在同一位置使用，同时可以按照数组的形式,方便的将多个要扫描的包写在一起，
//@ComponentScan({"config", "hello"})

//指定要扫描的servlet组件filter的包，否则也不会加载该包下的filter。
//@ServletComponentScan("filter")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
