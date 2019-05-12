package com.politechnika.housing.service.inf;

import com.politechnika.housing.model.Authorities;
import com.politechnika.housing.model.jsonModel.RoleJsonModel;

public interface AuthoritiesService {

    void save(Authorities authorities);

    RoleJsonModel getUserRole(String username);
}
