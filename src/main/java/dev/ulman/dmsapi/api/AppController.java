package dev.ulman.dmsapi.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping(value = "/")
    public String enter(){
        return "index";
    }

    @GetMapping(value = {"/login"})
    public String login(){
        return "login";
    }

}
