package com.politechnika.housing.repository;

import com.politechnika.housing.model.Occupant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupantRepository extends JpaRepository<Occupant, Integer> {

    @Query(value= "SELECT u.id FROM Occupant u where u.user.id =:id ")
    Integer getOccupantIdByUserId(@Param("id") int id);
}
