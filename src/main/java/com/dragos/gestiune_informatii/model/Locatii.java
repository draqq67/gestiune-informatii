package com.dragos.gestiune_informatii.model;

import jakarta.persistence.*;
@Entity
@Table(name="locatii")
public class Locatii {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name ="adresa")
    private String adresa;

    @Column(name = "urlmaps")
    private String urlmaps;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_competitie_main",referencedColumnName ="id", nullable = false)  // Foreign key in Categorii table
    private CompetitiiMain competition;

    public Locatii(){
    }
    public Locatii(String adresa, String urlmaps,CompetitiiMain competition) {
        this.adresa = adresa;
        this.urlmaps = urlmaps;
        this.competition = competition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAdresa() {
        return adresa;
    }
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    public String getUrlmaps() {
        return urlmaps;
    }
    public void setUrlmaps(String urlmaps) {
        this.urlmaps = urlmaps;
    }
    public CompetitiiMain getCompetition() {
        return competition;
    }
    public void setCompetition(CompetitiiMain competition) {
        this.competition = competition;
    }
}
