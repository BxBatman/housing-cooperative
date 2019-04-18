package com.politechnika.housing.controller;

import com.politechnika.housing.model.jsonModel.RoleJsonModel;
import com.politechnika.housing.repository.UserRepository;
import com.politechnika.housing.service.inf.AuthoritiesService;
import com.politechnika.housing.service.inf.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesService authoritiesService;

    @Autowired
    UserService userService;



    @RequestMapping(value = "/user/login/{username}", method = RequestMethod.POST)
    @ResponseBody
    public RoleJsonModel login(@PathVariable String username) {
        return authoritiesService.getUserRole(username);
    }

    @RequestMapping(value = "/user/activate/{token}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity activateUser(@PathVariable("token") String token) {
        userService.activateAccount(token);
        return ResponseEntity.ok().build();
    }

}
