package com.dragos.gestiune_informatii.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "logo_url", length = Integer.MAX_VALUE)
    private String logoUrl;

    @Column(name = "oras")
    private String oras;

    @OneToMany(mappedBy = "idEchipa")
    private Set<Participanti> participantis = new LinkedHashSet<>();

    public Set<Participanti> getParticipantis() {
        return participantis;
    }

    public void setParticipantis(Set<Participanti> participantis) {
        this.participantis = participantis;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @OneToMany(mappedBy = "idEchipa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participanti> participanti = new ArrayList<>();
    // Constructors, Getters, and Setters
    public Echipe() {
    }

    public Echipe(String nume, Integer anInfiintare, String oras) {
        this.nume = nume;
        this.anInfiintare = anInfiintare;
    }

    public List<Participanti> getParticipanti() {
        return participanti;
    }

    public void setParticipanti(List<Participanti> participanti) {
        this.participanti = participanti;
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
