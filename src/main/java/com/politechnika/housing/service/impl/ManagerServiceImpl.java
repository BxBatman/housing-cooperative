package com.politechnika.housing.service.impl;

import com.politechnika.housing.config.MailConfig;
import com.politechnika.housing.config.WebSecurityTokens;
import com.politechnika.housing.exception.ManagerNotFoundException;
import com.politechnika.housing.model.Authorities;
import com.politechnika.housing.model.Manager;
import com.politechnika.housing.model.User;
import com.politechnika.housing.repository.ManagerRepository;
import com.politechnika.housing.service.inf.AuthoritiesService;
import com.politechnika.housing.service.inf.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthoritiesService authoritiesService;
    @Override
    public int save(Manager manager) {
        Authorities authorities = new Authorities();
        authorities.setUsername(manager.getFirstname()+manager.getLastname());
        authorities.setAuthority(WebSecurityTokens.ROLE_MANAGER);
        MailConfig.configure();

        String pass = new Random().ints(10, 33, 122).collect(StringBuilder::new,
                StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        String token = UUID.randomUUID().toString();


        User user = new User();
        user.setUsername(manager.getFirstname()+manager.getLastname());
        user.setPassword(passwordEncoder.encode(pass));
        user.setActivationToken(token);
        manager.setUser(user);
        authoritiesService.save(authorities);

        int id =managerRepository.saveAndFlush(manager).getId();

        MailConfig.sendMail("dev312.test@gmail.com",manager.getFirstname(),manager.getLastname(),pass,token);

        return id;
    }

    @Override
    public Manager get(int id) throws ManagerNotFoundException {
      return  managerRepository.findById(id).orElseThrow(()->new ManagerNotFoundException("Manager id" +id));
    }

    @Override
    public int update(Manager manager) {
      return  managerRepository.saveAndFlush(manager).getId();
    }

    @Override
    public void delete(int id) {
        managerRepository.deleteById(id);
    }

    @Override
    public List<Manager> getAll() {
       return managerRepository.findAll();
    }
}
