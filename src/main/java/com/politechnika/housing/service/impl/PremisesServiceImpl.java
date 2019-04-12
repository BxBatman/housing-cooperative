package com.politechnika.housing.service.impl;

import com.politechnika.housing.exception.OccupantNotFoundException;
import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Cost;
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
    public void addCostToPremises(Cost cost, int premisesId) {
        Premises premises = null;

        try {
            premises = get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        if (premises != null) {
           List<Cost> costs =  premises.getCosts();
           costs.add(cost);
           premises.setCosts(costs);
           premisesRepository.save(premises);
        }

    }

    @Override
    public void deleteCostFromPremises(int costId, int premisesId) {
        Premises premises = null;

        try {
            premises = get(premisesId);
        } catch (PremisesNotFoundException e) {
            e.printStackTrace();
        }

        List<Cost> costs = premises.getCosts();

        costs.removeIf(cost -> cost.getId() == costId);
        premises.setCosts(costs);
        premisesRepository.save(premises);

    }

    @Override
    public Set<Premises> getPremisesForSpecificOccupant(int occupantId) throws OccupantNotFoundException {
        Occupant occupant = occupantService.get(occupantId);
        return occupant.getPremises();
    }


}
