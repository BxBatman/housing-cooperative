package com.politechnika.housing.service.inf;

import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Bill;

import java.util.List;

public interface BillService {

    List<Bill> getBillsForSpecificPremises(int id) throws PremisesNotFoundException;
}
