package com.example.security.Controller;

import com.example.security.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/user")
    public List<User> user(){;
    User u1 = new User(1,"Abhishek","abhishek@123");
    User u2 = new User(2,"Sam","Sam@123");
        return List.of(u1,u2);
    }


}
