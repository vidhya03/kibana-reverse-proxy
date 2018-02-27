package com.labkit.vidhya.kibanareverseproxy.filter;


import com.labkit.vidhya.kibanareverseproxy.KibanaProxyConstants;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vidhyadharan
 * <p>
 * 27-Feb-2018
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


        Cookie sessionCookieToken = getCookieByName(request, KibanaProxyConstants.SESSION_COOKIE_NAME);

        if (sessionCookieToken != null) {
            //Do the session cookie token validation here
            // Or Do your out authentication here


            chain.doFilter(req, resp);

        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
    }

    /**
     * Find a specific HTTP cookie in a request.
     *
     * @param request The HTTP request object.
     * @param name    The cookie name to look for.
     * @return The cookie, or <code>null</code> if not found.
     */
    protected Cookie getCookieByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals(name)) {
                return request.getCookies()[i];
            }
        }
        return null;
    }

    @Override
    public void destroy() {
        //nothing
    }
}
