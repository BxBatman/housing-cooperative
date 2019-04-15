package com.politechnika.housing.service.impl;

import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Bill;
import com.politechnika.housing.model.Premises;
import com.politechnika.housing.service.inf.BillService;
import com.politechnika.housing.service.inf.PremisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private PremisesService premisesService;


    @Override
    public List<Bill> getBillsForSpecificPremises(int id) throws PremisesNotFoundException {
        Premises premises = premisesService.get(id);
        return premises.getBills();
    }
}
