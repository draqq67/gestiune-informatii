package com.dragos.gestiune_informatii.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "competitii_main")
public class CompetitiiMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;// Ensure auto-generation of the ID

    @Column(name = "nume")
    private String nume;

    @Column(name = "data_start")
    private Date dataStart;

    @Column(name = "data_end")
    private Date dataEnd;

    @Column(name = "descriere")
    private String descriere;

    @Column(name = "detalii")
    private String detalii;

    @Column(name = "site")
    private String site;

    @Column(name = "status")
    private boolean status;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "organizator_id", nullable = false)  // Foreign key in CompetiiMain table
    public Organizatori organizator;


    // One competition can have multiple categories
    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Categorii> categorii = new ArrayList<>();

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Sponsori> sponsor = new ArrayList<>();


    public List<Sponsori> getSponsor() {
        return sponsor;
    }

    public void setSponsor(List<Sponsori> sponsor) {
        this.sponsor = sponsor;
    }

    public List<Categorii> getCategorii() {
        return categorii;
    }

    public void setCategorii(List<Categorii> categorii) {
        this.categorii = categorii;
    }

    // Getters and setters
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

    public Date getDataStart() {
        return dataStart;
    }

    public void setDataStart(Date dataStart) {
        this.dataStart = dataStart;
    }

    public Date getDataEnd() {
        return dataEnd;
    }

    public void setDataEnd(Date dataEnd) {
        this.dataEnd = dataEnd;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getDetalii() {
        return detalii;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setOrganizator(Organizatori organizatori) {
        this.organizator = organizatori;
    }

    public Organizatori getOrganizator() {
        return organizator;
    }
}