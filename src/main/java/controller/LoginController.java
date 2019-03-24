package controller;

import model.Authorities;
import model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.UserRepository;
import service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class LoginController {

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("message","You are logged in as " + principal.getName());
        return "index";
    }



}
