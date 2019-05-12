package com.politechnika.housing.service.impl;

import com.politechnika.housing.model.User;
import com.politechnika.housing.repository.UserRepository;
import com.politechnika.housing.service.inf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void activateAccount(String token) {
       User user = userRepository.findUserByActivationToken(token);
        if (user != null) {
            user.setEnabled(true);
        }
        userRepository.save(user);
    }
}
