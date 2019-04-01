package com.politechnika.housing.service.impl;

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
        return buildingRepository.save(building).getId();
    }
}
