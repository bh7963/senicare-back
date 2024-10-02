package com.phu.senicare.common.object;

import java.util.List;

import com.phu.senicare.entity.CareRecordEntity;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class CareRecord {
    private Integer recordNumber;
    private String recordDate;
    private String contents;
    private String usedToolName;
    private Integer count;

    private CareRecord(CareRecordEntity careRecordEntity) {
        this.recordNumber = careRecordEntity.getRecordNumber();
        this.recordDate = careRecordEntity.getRecordDate();
        this.contents = careRecordEntity.getContents();
        this.usedToolName = careRecordEntity.getUsedToolName();
        this.count = careRecordEntity.getCount();
    }
    
    public static List<CareRecord> getList(List<CareRecordEntity> careRecordEntities) {
        List<CareRecord> careRecords = new ArrayList<>(); 
        for(CareRecordEntity careRecordEntity:careRecordEntities){
            CareRecord careRecord = new CareRecord(careRecordEntity);
            careRecords.add(careRecord);
        }

        return careRecords;
    } 
}
