package com.example.restapimvc.repository;

import com.example.restapimvc.dao.CategoryDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDao, Long> {


}
