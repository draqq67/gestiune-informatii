/**
 * Clasa pentru Poze
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.model;

import jakarta.persistence.*;

@Entity
@Table(name = "poze")
public class Poze {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "url_poza")
    private String url;

    @Column(name = "alt")
    private String altText;

    @ManyToOne(fetch = FetchType.LAZY)  // Load competition details only when accessed
    @JoinColumn(name = "id_competitie_main",referencedColumnName ="id", nullable = false)  // Foreign key in Categorii table
    private CompetitiiMain competitiiMain;


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getAltText() {
        return altText;
    }
    public void setAltText(String altText) {
        this.altText = altText;
    }
    public CompetitiiMain getCompetitiiMain() {
        return competitiiMain;
    }
    public void setCompetitiiMain(CompetitiiMain competitiiMain) {
        this.competitiiMain = competitiiMain;
    }
}
