/**
 * Clasa pentru Categorii
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorii")
public class Categorii {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sport")
    private String sport;

    @Column(name = "numarechipe")
    private int numarEchipe;

    @Column(name = "detalii")
    private String detalii;

    // Many categories belong to one competition
    @ManyToOne(fetch = FetchType.LAZY)  // Load competition details only when accessed
    @JoinColumn(name = "competition_id",referencedColumnName ="id", nullable = false)  // Foreign key in Categorii table
    private CompetitiiMain competition;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public int getNumarEchipe() {
        return numarEchipe;
    }

    public void setNumarEchipe(int numarEchipe) {
        this.numarEchipe = numarEchipe;
    }

    public String getDetalii() {
        return detalii;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public CompetitiiMain getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitiiMain competition) {
        this.competition = competition;
    }
}
