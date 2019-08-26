package dev.ulman.dms.api;

import dev.ulman.dms.dao.EmployeeDao;
import dev.ulman.dms.dao.UserDao;
import dev.ulman.dms.model.Employee;
import dev.ulman.dms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
