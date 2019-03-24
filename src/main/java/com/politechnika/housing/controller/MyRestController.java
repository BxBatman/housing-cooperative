package com.politechnika.housing.controller;

import com.politechnika.housing.model.User;
import com.politechnika.housing.repository.AuthoritiesRepository;
import com.politechnika.housing.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    private static final Logger logger = Logger.getLogger(MyRestController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @RequestMapping("/")
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("username") String username) {
        User user = userRepository.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }

}
