package com.phu.senicare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Entity(name="tools")
@Table(name="tools")
public class ToolEntity {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer toolNumber;
    private String name;
    private String purpose;
    private Integer count;
}
