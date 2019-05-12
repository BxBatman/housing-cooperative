package com.politechnika.housing.service.inf;

import com.politechnika.housing.exception.BuildingNotFoundException;
import com.politechnika.housing.exception.OccupantNotFoundException;
import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Bill;
import com.politechnika.housing.model.Premises;

import java.util.List;
import java.util.Set;

public interface PremisesService {

    int save(Premises premises);

    Premises get(int id) throws PremisesNotFoundException;

    int update(Premises premises);

    void delete(int id);

    void addBillToPremises(Bill bill, int premisesId);

    void deleteBillFromPremises(int billId, int premisesId);

    Set<Premises> getPremisesForSpecificOccupant(int occupantId) throws OccupantNotFoundException;

    void setBillAccepted(int billId);

    Set<Premises> getPremisesForSpecificBuidling(int buildingId) throws BuildingNotFoundException;

    Set<Premises> getAllAvailablePremises();

    List<Premises> getAllPremises();
}
