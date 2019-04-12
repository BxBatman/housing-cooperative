package com.politechnika.housing.service.impl;

import com.politechnika.housing.exception.BuildingNotFoundException;
import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Building;
import com.politechnika.housing.model.Premises;
import com.politechnika.housing.repository.BuildingRepository;
import com.politechnika.housing.service.inf.BuildingService;
import com.politechnika.housing.service.inf.PremisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private PremisesService premisesService;

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

    @Override
    public void addPremisesToBuilding(int premisesId, int buildingId) {
        Building building = null;
        Premises premises = null;

        try {
            building = get(buildingId);
        } catch (BuildingNotFoundException e) {
            e.printStackTrace();
        }

        try {
            premises = premisesService.get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        if (building != null && premises != null) {

            Set<Premises> premisesSet = building.getPremises();
            premisesSet.add(premises);

            building.setPremises(premisesSet);
            save(building);
        }

    }

    @Override
    public void deletePremisesFromBuidling(int premisesId, int buildingId) {
        Building building = null;

        try {
            building = get(buildingId);
        } catch (BuildingNotFoundException e) {
            e.printStackTrace();
        }

        Set<Premises> premisesSet = building.getPremises();

        premisesSet.removeIf(premises -> premises.getId() == premisesId);

        building.setPremises(premisesSet);
        save(building);

    }

    @Override
    public List<Building> getAll() {
        return buildingRepository.findAll();
    }
}
