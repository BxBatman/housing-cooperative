package com.politechnika.housing.service.inf;

import com.politechnika.housing.exception.BuildingNotFoundException;
import com.politechnika.housing.model.Building;

public interface BuildingService {

    int save(Building building);

    Building get(int id) throws BuildingNotFoundException;

    int update(Building building);

    void delete(int id);

    void addPremisesToBuilding(int premisesId, int buildingId);

    void deletePremisesFromBuidling(int premisesId, int buildingId);
}
