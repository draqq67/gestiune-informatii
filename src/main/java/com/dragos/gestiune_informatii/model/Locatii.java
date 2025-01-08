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


    public Locatii(){
    }
    public Locatii(String adresa, String urlmaps) {
        this.adresa = adresa;
        this.urlmaps = urlmaps;
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
}
