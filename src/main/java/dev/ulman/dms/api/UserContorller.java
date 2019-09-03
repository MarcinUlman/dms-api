package dev.ulman.dms.api;


import dev.ulman.dms.model.User;
import dev.ulman.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("admin/users")
@Controller
public class UserContorller {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public UserContorller(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping
    public String getUserList(Model model){

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);

        return "admin/usersList";
    }

    @PostMapping
    public String addUser(Model model, @RequestBody User user){
        User fondedUser = userService.getUserByUsername(user.getUsername());
        if (fondedUser != null){
            model.addAttribute("message", "User already exist");
        }
        userService.addUser(user);
        model.addAttribute("message", "User added");

        return getUserList(model);
    }
}
