package com.politechnika.housing.repository;

import com.politechnika.housing.model.Occupant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupantRepository extends JpaRepository<Occupant, Integer> {

}
