package com.politechnika.housing.repository;

import com.politechnika.housing.model.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostRepository extends JpaRepository<Cost,Integer> {
}
