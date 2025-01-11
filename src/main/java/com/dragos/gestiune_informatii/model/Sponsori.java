/**
 * Clasa pentru Sponsori
 * @author Zarnescu Dragos
 * @version 11 Ianuarie 2025
 */

package com.dragos.gestiune_informatii.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sponsori")
public class Sponsori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the ID
    private Integer id;

    @Column(name ="denumire")
    private String denumire;

    @Column(name = "tip_pachet")
    private String tip_pachet;

    @ManyToOne(fetch = FetchType.LAZY)  // Load competition details only when accessed
    @JoinColumn(name = "competition_id",referencedColumnName ="id", nullable = false)  // Foreign key in Categorii table
    private CompetitiiMain competition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getTip_pachet() {
        return tip_pachet;
    }

    public void setTip_pachet(String tip_pachet) {
        this.tip_pachet = tip_pachet;
    }

    public CompetitiiMain getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitiiMain competition) {
        this.competition = competition;
    }
}
