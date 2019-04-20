package com.politechnika.housing.service.impl;

import com.politechnika.housing.exception.OccupantNotFoundException;
import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Bill;
import com.politechnika.housing.model.Occupant;
import com.politechnika.housing.model.Premises;
import com.politechnika.housing.repository.PremisesRepository;
import com.politechnika.housing.service.inf.OccupantService;
import com.politechnika.housing.service.inf.PremisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PremisesServiceImpl implements PremisesService {

    @Autowired
    private PremisesRepository premisesRepository;
    @Autowired
    private OccupantService occupantService;

    @Override
    public int save(Premises premises) {
       return premisesRepository.saveAndFlush(premises).getId();
    }

    @Override
    public Premises get(int id) throws PremisesNotFoundException {
        return premisesRepository.findById(id).orElseThrow(()->new PremisesNotFoundException("Premises id:" + id));
    }

    @Override
    public int update(Premises premises) {
       return premisesRepository.saveAndFlush(premises).getId();
    }

    @Override
    public void delete(int id) {
        premisesRepository.deleteById(id);
    }

    @Override
    public void addBillToPremises(Bill bill, int premisesId) {
        Premises premises = null;

        try {
            premises = get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        if (premises != null) {
           List<Bill> bills =  premises.getBills();
           bill.setAccepted(false);
           bill.setDone(true);
           bills.add(bill);
           premises.setBills(bills);
           premisesRepository.save(premises);
        }

    }

    @Override
    public void deleteBillFromPremises(int billId, int premisesId) {
        Premises premises = null;

        try {
            premises = get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        List<Bill> bills = premises.getBills();

        bills.removeIf(bill -> bill.getId() == billId);
        premises.setBills(bills);
        premisesRepository.save(premises);

    }

    @Override
    public Set<Premises> getPremisesForSpecificOccupant(int occupantId) throws OccupantNotFoundException {
        Occupant occupant = occupantService.get(occupantId);
        return occupant.getPremises();
    }


}
