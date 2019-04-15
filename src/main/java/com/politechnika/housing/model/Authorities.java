package com.politechnika.housing.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AUTHORITIES")
@Data
public class Authorities implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "AUTHORITY")
    private String authority;


    @Column(name = "USERNAME")
    private String username;

}
