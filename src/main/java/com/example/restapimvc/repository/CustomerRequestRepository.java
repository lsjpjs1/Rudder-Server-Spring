package com.example.restapimvc.repository;

import com.example.restapimvc.domain.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRequestRepository extends JpaRepository<CustomerRequest,Long> {
}
