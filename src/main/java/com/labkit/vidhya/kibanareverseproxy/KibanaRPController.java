package com.labkit.vidhya.kibanareverseproxy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class KibanaRPController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot Kibana proxy servlet";
    }

    @RequestMapping("/authenticate")
    public ResponseEntity<String> helloDashboard(HttpServletResponse response) {


        final Cookie cookie = new Cookie(KibanaProxyConstants.SESSION_COOKIE_NAME, "dummyjwt");
        // cookie.setDomain("localhost:3333");//Optional
//        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(45000);

        response.addCookie(cookie);

        String htmlLink = "<html>\n" +
                "<body>\n" +
                "\n" +
                "<div>Authenticated access the dashboard here...</div>"+
                "<a href=\"http://localhost:3333/portal/kibanadashboard.htm\">Dashboard</a>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";

        return new ResponseEntity<String>(htmlLink, HttpStatus.OK);
    }


    @RequestMapping("/mydashboard")
    public String iframe() {
        return "<html>\n" +
                "<body>\n" +
                "\n" +
                "<iframe src=\"http://localhost:3333/kibanadashboard/app/kibana#/dashboard/AWGkOy82bI6n3UP9i1eq?embed=true&_g=(refreshInterval%3A(display%3AOff%2Cpause%3A!f%2Cvalue%3A0)%2Ctime%3A(from%3Anow-5y%2Cmode%3Aquick%2Cto%3Anow))\" height=\"600\" width=\"800\"></iframe>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }

    @RequestMapping("/kibana")
    public String kibanaAsIframe() {
        return "<html>\n" +
                "<body>\n" +
                "\n" +
                "<iframe src=\"http://localhost:3333/kibanadashboard/app/kibana#/\" height=\"600\" width=\"800\"></iframe>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>\n";
    }


}