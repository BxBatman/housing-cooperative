package com.politechnika.housing.service.impl;

import com.politechnika.housing.exception.OccupantNotFoundException;
import com.politechnika.housing.model.Occupant;
import com.politechnika.housing.repository.OccupantRepository;
import com.politechnika.housing.service.inf.OccupantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OccupantServiceImpl implements OccupantService {

    @Autowired
    private OccupantRepository occupantRepository;

    @Override
    public int save(Occupant occupant) {
       return occupantRepository.saveAndFlush(occupant).getId();
    }

    @Override
    public Occupant get(int id) throws OccupantNotFoundException {
      return  occupantRepository.findById(id).orElseThrow(() -> new OccupantNotFoundException("User id:" + id));
    }

    @Override
    public int update(Occupant occupant) {
        return occupantRepository.saveAndFlush(occupant).getId();
    }

    @Override
    public void delete(int id) {
        occupantRepository.deleteById(id);
    }


}
