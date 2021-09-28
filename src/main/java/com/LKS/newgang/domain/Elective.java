package com.LKS.newgang.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Elective {
    @Id
    @Column(name = "elective_no", nullable = false)
    private Long electiveNo;
    private String electiveName;
    private String classification;
    private String time;
    private String professor;
    @JoinColumn(name = "campus_name")
    @ManyToOne
    private Campus campus;
    @JoinColumn(name = "classification_name")
    @ManyToOne
    private ElectiveClassification electiveClassification;
    private int grade; //학년
    private int max_grade; // 현재학년중 최대 인원
    private int max_other_grade; //현재 학년이 아닌 학년중 최대 인원
    private int max_transfer;
    private int curr_grade; // 현재 학년중 신청한 인원
    private int curr_other_grade;
    private int curr_transfer;
}
