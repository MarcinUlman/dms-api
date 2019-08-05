package dev.ulman.dms.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppContoller {


    @GetMapping(value = "/")
    public String enter(){
        return "index";
    }

    @GetMapping(value = "/error")
    public String loadErrorPage(){
        return "error";
    }
}
