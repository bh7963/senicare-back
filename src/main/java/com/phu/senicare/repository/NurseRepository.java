package com.phu.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phu.senicare.entity.NurseEntity;

@Repository
public interface NurseRepository extends JpaRepository<NurseEntity, String> {
    

    boolean existsByUserId(String userId);
    boolean existsByTelNumber(String telNumber);

    NurseEntity findByUserId(String userId);

}
