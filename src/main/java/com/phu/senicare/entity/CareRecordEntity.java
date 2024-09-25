package com.phu.senicare.entity;

import com.phu.senicare.dto.request.customer.PostCareRecordRequestDto;

import java.text.SimpleDateFormat;
import java.util.Date;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="careRecords")
@Table(name="care_records")
public class CareRecordEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recordNumber;
    private String recordDate;
    private String contents;
    private String usedToolName;
    private Integer count;
    private String charger;
    private Integer customerNumber;

    public CareRecordEntity(
        PostCareRecordRequestDto dto, 
        String usedToolName,
        String charger,
        Integer customerNumber
    ) {
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String recordDate = simpleDateFormat.format(now);

        this.recordDate = recordDate;
        this.contents = dto.getContents();
        this.usedToolName = usedToolName;
        this.count = dto.getCount();
        this.charger = charger;
        this.customerNumber = customerNumber;
    }
}
