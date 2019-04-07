package com.politechnika.housing.controller;

import com.politechnika.housing.config.MailConfig;
import com.politechnika.housing.model.Occupant;
import com.politechnika.housing.model.User;
import com.politechnika.housing.repository.AuthoritiesRepository;
import com.politechnika.housing.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("username") String username) {
        User user = userRepository.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }


    @RequestMapping(value = "/user/mail",method = RequestMethod.POST)
    public ResponseEntity sendMail(@RequestBody Occupant occupant){
        MailConfig.configure();
        MailConfig.sendMail(occupant.getEmail(),occupant.getFirstname(),occupant.getLastname(),occupant.getUser().getPassword());
        return ResponseEntity.ok().build();
    }

}
