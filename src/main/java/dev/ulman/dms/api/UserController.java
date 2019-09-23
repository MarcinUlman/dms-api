package dev.ulman.dms.api;


import dev.ulman.dms.model.User;
import dev.ulman.dms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin/users")
@Controller
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
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

    @DeleteMapping(path = "{userId}")
    public String deleteUser(Model model, @RequestParam(value = "userId", required = true) long id){
        userService.deleteUser(id);

        return getUserList(model);
    }

    @GetMapping(path = "/AddNewUser")
    public String displayUserForm(Model model, @RequestParam (value="userId", defaultValue = "0") long id){
        User fondedUser = userService.getUserByUsername("user");
        if(id != 0)
            model.addAttribute("user", fondedUser);
        return "admin/userForm";
    }
}
