package com.politechnika.housing.repository;

import com.politechnika.housing.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Integer> {
    List<Authorities> findByUsername(String username);
}
