package com.phu.senicare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phu.senicare.entity.ToolEntity;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntity, Integer> {

    ToolEntity findByToolNumber(Integer toolNumber);
    List<ToolEntity> findByOrderByToolNumberDesc();    

}
