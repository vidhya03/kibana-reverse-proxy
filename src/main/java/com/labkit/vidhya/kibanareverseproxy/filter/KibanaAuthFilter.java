package com.labkit.vidhya.kibanareverseproxy.filter;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vidhyadharan
 *
 * 27-Feb-2018
 *
 */
public class KibanaAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //nothing
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws
            IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;


        String queryString = request.getQueryString();
        if(queryString!=null){
            System.out.println("query string not null");

        }else {

            String token = request.getHeader("Authorization");
            if (token == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        //nothing
    }
}
