package com.politechnika.housing.service.inf;

import com.politechnika.housing.exception.PremisesNotFoundException;
import com.politechnika.housing.model.Premises;

public interface PremisesService {

    int save(Premises premises);

    Premises get(int id) throws PremisesNotFoundException;

    int update(Premises premises);

    void delete(int id);
}
