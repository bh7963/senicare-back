package com.phu.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phu.senicare.entity.TelAuthNumberEntity;


@Repository
// JpaRepository의 제너릭 타입은 리포지토리를 사용하는 엔터티와 PK의 타입
public interface TelAuthNumberRepository extends JpaRepository<TelAuthNumberEntity, String> { 

    boolean existsByTelNumberAndAuthNumber(String telNumber, String authNumber);
}
