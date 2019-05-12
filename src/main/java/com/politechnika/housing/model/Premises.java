package com.politechnika.housing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "occupant_id")
    @JsonIgnore
    private Occupant occupant;

    @ManyToOne
    @JoinColumn(name = "building_id")
    @JsonIgnore
    private Building building;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "premises")
    private List<Bill> bills =  new ArrayList<>();
}
