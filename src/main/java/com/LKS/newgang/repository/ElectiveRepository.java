package com.LKS.newgang.repository;

import com.LKS.newgang.domain.Campus;
import com.LKS.newgang.domain.Elective;
import com.LKS.newgang.domain.ElectiveClassification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ElectiveRepository extends JpaRepository<Elective, String> {
    List<Elective> findByCampusAndClassification(Campus campus, ElectiveClassification electiveClassification);
}
