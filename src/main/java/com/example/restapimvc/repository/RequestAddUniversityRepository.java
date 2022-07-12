package com.example.restapimvc.repository;

import com.example.restapimvc.domain.ReportReceive;
import com.example.restapimvc.domain.RequestAddUniversity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestAddUniversityRepository  extends JpaRepository<RequestAddUniversity,Long> {
}
