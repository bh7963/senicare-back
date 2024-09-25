package com.phu.senicare.common.object;

import java.util.ArrayList;
import java.util.List;

import com.phu.senicare.entity.NurseEntity;

import lombok.Getter;

@Getter
public class Nurse {
    private String nurseId;
    private String name;
    private String telNumber;

    private Nurse(NurseEntity nurseEntity) {
        this.nurseId = nurseEntity.getUserId();
        this.name = nurseEntity.getName();
        this.telNumber = nurseEntity.getTelNumber();
    }

    public static List<Nurse> getList(List<NurseEntity> nurseEntities) {
        List<Nurse> nurses = new ArrayList<>();
        for (NurseEntity nurseEntity: nurseEntities){
            Nurse nurse = new Nurse(nurseEntity);
            nurses.add(nurse);
        }
        return nurses;
    }
}
