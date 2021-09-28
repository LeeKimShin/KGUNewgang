package com.LKS.newgang.domain;

import javax.persistence.*;

@Entity
public class ElectiveClassification {
    @Id
    @Column(name = "classification_name", nullable = false)
    private String classification;
    @JoinColumn(name = "campus_name")
    @ManyToOne
    private Campus campus;

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
