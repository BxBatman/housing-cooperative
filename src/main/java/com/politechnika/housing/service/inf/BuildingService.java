package com.politechnika.housing.service.inf;

import com.politechnika.housing.exception.BuildingNotFoundException;
import com.politechnika.housing.model.Building;
import com.politechnika.housing.model.Premises;

import java.util.List;
import java.util.Set;

public interface BuildingService {

    int save(Building building);

    Building get(int id) throws BuildingNotFoundException;

    int update(Building building);

    void delete(int id);

    void addPremisesToBuilding(Premises premises, int buildingId);

    void deletePremisesFromBuidling(int premisesId, int buildingId);

    List<Building> getAll();

    Set<Premises> getPremisesForBuilding(int buildingId) throws BuildingNotFoundException;
}
