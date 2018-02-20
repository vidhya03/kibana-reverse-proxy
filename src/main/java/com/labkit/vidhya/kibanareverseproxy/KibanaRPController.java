package com.labkit.vidhya.kibanareverseproxy;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KibanaRPController {

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot Kibana proxy servlet";
  }




  @RequestMapping("/mydashboard")
  public String iframe(){
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
  public String kibanaAsIframe(){
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