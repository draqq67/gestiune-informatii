package com.dragos.gestiune_informatii.model;

import jakarta.persistence.*;

@Entity
@Table(name = "participanti")
public class Participanti {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nume", length = Integer.MAX_VALUE)
    private String nume;

    @Column(name = "varsta")
    private Integer varsta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_echipa")
    private Echipe idEchipa;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "telefon", length = Integer.MAX_VALUE)
    private String telefon;

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

    public Integer getVarsta() {
        return varsta;
    }

    public void setVarsta(Integer varsta) {
        this.varsta = varsta;
    }

    public Echipe getIdEchipa() {
        return idEchipa;
    }

    public void setIdEchipa(Echipe idEchipa) {
        this.idEchipa = idEchipa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

}