package com.LKS.newgang.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Department {

    @Id
    @Column(name = "dept_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @JoinColumn(name = "colleague_name")
    @ManyToOne
    private Colleague colleague;

    public Department(String departmentName){
        this.departmentName = departmentName;
    }

    public Department(String departmentName, Colleague colleague) {
        this.departmentName = departmentName;
        this.colleague = colleague;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public Colleague getColleague() {
        return colleague;
    }
}
