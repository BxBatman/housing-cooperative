package com.politechnika.housing.repository;

import com.politechnika.housing.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository  extends JpaRepository<Manager, Integer>{

    @Query(value= "SELECT u.id FROM Manager u where u.user.id =:id ")
    Integer getManagerByUserId(@Param("id") int id);

}
