package com.politechnika.housing.service.impl;

import com.politechnika.housing.exception.BuildingNotFoundException;
import com.politechnika.housing.model.Building;
import com.politechnika.housing.repository.BuildingRepository;
import com.politechnika.housing.service.inf.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public int save(Building building) {
        return buildingRepository.saveAndFlush(building).getId();
    }

    @Override
    public Building get(int id) throws BuildingNotFoundException {
        return buildingRepository.findById(id).orElseThrow(() -> new BuildingNotFoundException("Buidling id" + id));
    }

    @Override
    public int update(Building building) {
        return buildingRepository.saveAndFlush(building).getId();
    }

    @Override
    public void delete(int id) {
        buildingRepository.deleteById(id);
    }
}
