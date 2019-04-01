package com.politechnika.housing.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PREMISES")
@Data
public class Premises {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @OneToMany
    private List<Cost> costs =  new ArrayList<>();
}
