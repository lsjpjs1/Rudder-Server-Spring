package com.example.restapimvc.repository;

import com.example.restapimvc.domain.ReportReceive;
import com.example.restapimvc.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportReceive,Long> {
}
