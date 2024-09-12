package com.phu.senicare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="nurses")
@Table(name="nurses")
public class NurseEntity {
    
    @Id
    private String userId;
    private String password;
    private String name;
    private String telNumber;
    private String joinPath;
    private String snsId;

}
