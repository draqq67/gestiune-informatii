package com.dragos.gestiune_informatii.model;

import java.util.List;
import java.util.ArrayList;  // Missing import for ArrayList
import jakarta.persistence.*;

@Entity
@Table(name = "organizatori")  // Specify the table name in the database
public class Organizatori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-generate the ID
    private Integer id;

    @Column(name = "name")  // Column name in the table
    private String name;

    @Column(name = "role")  // Column name in the table
    private String role;

    // One organizer can manage multiple competitions
    @OneToMany(mappedBy = "organizator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompetitiiMain> competitions = new ArrayList<>();  // Initialize the list

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<CompetitiiMain> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<CompetitiiMain> competitions) {
        this.competitions = competitions;
    }
}
