package com.dragos.gestiune_informatii.model;

import jakarta.persistence.*;

@Entity
@Table(name = "echipe")
public class Echipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "an_infiintare")
    private Integer anInfiintare;


    // Constructors, Getters, and Setters
    public Echipe() {
    }

    public Echipe(String nume, Integer anInfiintare, String oras) {
        this.nume = nume;
        this.anInfiintare = anInfiintare;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getAnInfiintare() {
        return anInfiintare;
    }

    public void setAnInfiintare(Integer anInfiintare) {
        this.anInfiintare = anInfiintare;
    }


}
