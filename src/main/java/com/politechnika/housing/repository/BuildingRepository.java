package com.politechnika.housing.repository;

import com.politechnika.housing.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
    @Query("SELECT u from Building u where manager_id is null")
    Set<Building> getAllAvailableBuildings();
}
