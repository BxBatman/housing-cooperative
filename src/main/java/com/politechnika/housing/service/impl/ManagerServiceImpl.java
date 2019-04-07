package com.politechnika.housing.service.impl;

import com.politechnika.housing.exception.ManagerNotFoundException;
import com.politechnika.housing.model.Manager;
import com.politechnika.housing.repository.ManagerRepository;
import com.politechnika.housing.service.inf.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Override
    public int save(Manager manager) {
      return   managerRepository.saveAndFlush(manager).getId();
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
}
