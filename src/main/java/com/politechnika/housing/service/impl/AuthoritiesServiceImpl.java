package com.politechnika.housing.service.impl;

import com.politechnika.housing.model.Authorities;
import com.politechnika.housing.model.Occupant;
import com.politechnika.housing.model.User;
import com.politechnika.housing.model.jsonModel.RoleJsonModel;
import com.politechnika.housing.repository.AuthoritiesRepository;
import com.politechnika.housing.repository.OccupantRepository;
import com.politechnika.housing.repository.UserRepository;
import com.politechnika.housing.service.inf.AuthoritiesService;
import com.politechnika.housing.service.inf.OccupantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesServiceImpl implements AuthoritiesService{

    @Autowired
    private AuthoritiesRepository authoritiesRepository;
    @Autowired
    private OccupantRepository occupantRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(Authorities authorities) {
        authoritiesRepository.saveAndFlush(authorities);
    }

    @Override
    public RoleJsonModel getUserRole(String username) {
        RoleJsonModel roleJsonModel = new RoleJsonModel();
        User user = userRepository.findUserByUsername(username);
        int occupantId = occupantRepository.getOccupantIdByUserId(user.getId());
        roleJsonModel.setId(occupantId);
        roleJsonModel.setRole(authoritiesRepository.findByUsername(username).getAuthority());
        return roleJsonModel;
    }
}
