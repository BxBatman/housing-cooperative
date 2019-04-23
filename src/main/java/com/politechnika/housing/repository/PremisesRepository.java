package com.politechnika.housing.repository;

import com.politechnika.housing.model.Premises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PremisesRepository extends JpaRepository<Premises, Integer> {

    @Query("SELECT u from Premises u where occupant_id is null")
    Set<Premises> getAllAvailablePremises();
}
