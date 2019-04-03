package com.politechnika.housing.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "COST")
@Getter
@Setter
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
