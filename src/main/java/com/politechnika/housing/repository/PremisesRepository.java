package com.politechnika.housing.repository;

import com.politechnika.housing.model.Premises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremisesRepository extends JpaRepository<Premises, Integer> {
}
