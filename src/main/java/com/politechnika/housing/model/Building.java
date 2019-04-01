package com.politechnika.housing.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "BUILDINGS")
@Data
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;


}
