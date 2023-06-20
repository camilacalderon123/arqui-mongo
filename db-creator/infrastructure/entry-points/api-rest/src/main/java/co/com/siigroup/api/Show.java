
package co.com.siigroup.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Show {
    @RequestMapping("/layout")
    public String dashboard(){
        return "layout";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/databases")
    public String databases(){
        return "databases";
    }

    @RequestMapping("/commands")
    public String commands(){
        return "commands";
    }



}
