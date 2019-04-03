package com.politechnika.housing.service.impl;

import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Premises;
import com.politechnika.housing.repository.PremisesRepository;
import com.politechnika.housing.service.inf.PremisesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremisesServiceImpl implements PremisesService {

    @Autowired
    private PremisesRepository premisesRepository;

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
}
