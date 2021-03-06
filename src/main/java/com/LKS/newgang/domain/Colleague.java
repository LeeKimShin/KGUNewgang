package com.LKS.newgang.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Colleague {

    @Id
    @Column(name = "colleague_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(name = "colleague_name", nullable = false)
    private String colleagueName;

    @JoinColumn(name = "campus_name")
    @ManyToOne
    private Campus campus;

    public Colleague(String colleagueName){
        this.colleagueName = colleagueName;
    }

    public Colleague(String colleagueName, Campus campus) {
        this.colleagueName = colleagueName;
        this.campus = campus;
    }

    public Campus getCampus() {
        return campus;
    }

    public String getColleagueName() {
        return colleagueName;
    }
}
