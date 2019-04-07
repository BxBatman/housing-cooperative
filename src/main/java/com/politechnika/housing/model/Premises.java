package com.politechnika.housing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PREMISES")
@Getter
@Setter
public class Premises {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;


    @Column(name = "NUMBER")
    private String number;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cost> costs =  new ArrayList<>();
}
