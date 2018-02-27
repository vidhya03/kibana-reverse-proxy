package com.labkit.vidhya.kibanareverseproxy.servlet;

import com.labkit.vidhya.kibanareverseproxy.filter.KibanaAuthFilter;
import org.apache.http.HttpRequest;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class KibanaProxyServletConfiguration implements EnvironmentAware {


    private static final String ENV_PROXY_SOLR = "proxy.kibana.";

    @Bean
    public ServletRegistrationBean kibanaServletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new ProxyServlet(){
            @Override
            protected void copyRequestHeaders(HttpServletRequest servletRequest, HttpRequest proxyRequest) {

                String method = servletRequest.getMethod();
                log("The requested method = " + method);
                if(Boolean.valueOf(propertyResolver.getProperty("is_auth_enabled"))) {
                    proxyRequest.addHeader("Authorization", propertyResolver.getProperty("basic_auth"));
                }

                super.copyRequestHeaders(servletRequest, proxyRequest);
            }
        }, propertyResolver.getProperty("servlet_url"));
        servletRegistrationBean.addInitParameter("targetUri", propertyResolver.getProperty("target_url"));
        servletRegistrationBean.addInitParameter(ProxyServlet.P_LOG, propertyResolver.getProperty("logging_enabled", "false"));
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean kibanaFilterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        KibanaAuthFilter kibanaAuthFilter = new KibanaAuthFilter();
        filterRegistrationBean.setFilter(kibanaAuthFilter);
        filterRegistrationBean.addUrlPatterns(propertyResolver.getProperty("servlet_url"));

        return filterRegistrationBean;
    }

    private RelaxedPropertyResolver propertyResolver;


    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_PROXY_SOLR);
    }
}
