package configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
//the empty string can be used as a valid prefix, it is default value.
//at this time, spring will directly use the secondary prefix to find the properties in the property file and assign values
//the secondary prefix here is the [servers] specified by the getter/setter method below
@ConfigurationProperties()
@PropertySource("classpath:servers.properties")
public class ServerInfoList {

    /**
     * here variable name doesn't matter, it can be addressProperties as well
     * setter / getter should match with properties in property file.
     * i.e. getServers() and setServers()
     */
    private Map<String, ServerInfo> serverList = new HashMap<>();

    public Map<String, ServerInfo> getServers() {
        return serverList;
    }

    public void setServers(Map<String, ServerInfo> serverList) {
        this.serverList = serverList;
    }

    /**
     * In order for ConfigurationProperties to correctly instantiate this class, it needs to be set to static.
     */
    public static class ServerInfo {

        private String host;
        private String port;

        /**
         * in order for @ConfigurationProperties to work on Map, the value type must have a No-Arguments constructor.
         */

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        @Override
        public String toString() {
            return "{" + host + ":" + port + "}";
        }
    }

    @Override
    public String toString() {
        return "ServerInfoList{" + "servers=" + serverList + '}';
    }
}
