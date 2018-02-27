package com.labkit.vidhya.kibanareverseproxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class KibanaProxyProperties {


    @Value("${security.basic.enabled}")
    private String securityBasicEnabled;
    @Value("${server.port}")
    private String serverPort;
    @Value("${proxy.kibana.servlet_url}")
    private String proxyKibanaServlet_url;

    public String getSecurityBasicEnabled() {
        return securityBasicEnabled;
    }

    public void setSecurityBasicEnabled(String securityBasicEnabled) {
        this.securityBasicEnabled = securityBasicEnabled;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getProxyKibanaServlet_url() {
        return proxyKibanaServlet_url;
    }

    public void setProxyKibanaServlet_url(String proxyKibanaServlet_url) {
        this.proxyKibanaServlet_url = proxyKibanaServlet_url;
    }
}
