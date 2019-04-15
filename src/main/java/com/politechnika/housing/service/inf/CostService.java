package com.politechnika.housing.service.inf;

import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Cost;

import java.util.List;

public interface CostService {

    List<Cost> getCostsForSpecificPremises(int id) throws PremisesNotFoundException;
}
