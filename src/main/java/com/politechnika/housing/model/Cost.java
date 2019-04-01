package com.politechnika.housing.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "COST")
@Data
public class Cost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "VALUE")
    private Double value;

}
