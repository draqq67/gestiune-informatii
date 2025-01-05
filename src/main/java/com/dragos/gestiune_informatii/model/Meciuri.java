package com.dragos.gestiune_informatii.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "meciuri")
public class Meciuri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categorie", referencedColumnName = "id", nullable = false)
    private Categorii categorie;

    @Column(name = "data_meci", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataMeci;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_echipa1", referencedColumnName = "id", nullable = false)
    private Echipe echipa1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_echipa2", referencedColumnName = "id", nullable = false)
    private Echipe echipa2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_locatie", referencedColumnName = "id", nullable = false)
    private Locatii locatie;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "scor")
    private String scor;

    // Constructors, Getters, and Setters
    public Meciuri() {
    }

    public Meciuri(Categorii categorie, LocalDateTime dataMeci, Echipe echipa1, Echipe echipa2, Locatii locatie, String status, String details, String scor) {
        this.categorie = categorie;
        this.dataMeci = dataMeci;
        this.echipa1 = echipa1;
        this.echipa2 = echipa2;
        this.locatie = locatie;
        this.status = status;
        this.scor = scor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataMeci() {
        return dataMeci;
    }

    public void setDataMeci(LocalDateTime dataMeci) {
        this.dataMeci = dataMeci;
    }

    public Locatii getLocatie() {
        return locatie;
    }

    public void setLocatie(Locatii locatie) {
        this.locatie = locatie;
    }

    public Echipe getEchipa1() {
        return echipa1;
    }

    public void setEchipa1(Echipe echipa1) {
        this.echipa1 = echipa1;
    }

    public Echipe getEchipa2() {
        return echipa2;
    }

    public void setEchipa2(Echipe echipa2) {
        this.echipa2 = echipa2;
    }

    public Categorii getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorii categorie) {
        this.categorie = categorie;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getScor() {
        return scor;
    }
    public void setScor(String scor) {
        this.scor = scor;
    }

    // Add remaining getters and setters
}
