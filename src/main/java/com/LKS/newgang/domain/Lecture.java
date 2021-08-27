package com.LKS.newgang.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
public class Lecture {

    @Id
    @Column(name = "lec_no", nullable = false)
    private int no;
    private String lectureName;
    private String classification;
    private String time;
    private String professor;
    @JoinColumn(name = "department_name")
    @ManyToOne
    private Department department;
    @JoinColumn(name = "major_name")
    @ManyToOne
    private Major major;
    private int grade;
    private int max_grade;
    private int max_other_grade;
    private int max_transfer;
    private int curr_grade;
    private int curr_other_grade;
    private int curr_transfer;
}
