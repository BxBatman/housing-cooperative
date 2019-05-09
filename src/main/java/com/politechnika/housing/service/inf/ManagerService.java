package com.politechnika.housing.service.inf;

import com.politechnika.housing.exception.ManagerNotFoundException;
import com.politechnika.housing.model.Manager;

import java.util.List;

public interface ManagerService {

    int save(Manager manager);

    Manager get(int id) throws ManagerNotFoundException;

    int update(Manager manager);

    void delete(int id);

    List<Manager> getAll();
}
