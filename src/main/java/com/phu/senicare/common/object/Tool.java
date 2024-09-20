package com.phu.senicare.common.object;

import com.phu.senicare.entity.ToolEntity;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class Tool {
    private Integer toolNumber;
    private String name;
    private String purpose;
    private Integer count;

    private Tool(ToolEntity toolEntity){
        this.toolNumber = toolEntity.getToolNumber();
        this.name = toolEntity.getName();
        this.purpose = toolEntity.getPurpose();
        this.count = toolEntity.getCount();
    }

    public static List<Tool> getList(List<ToolEntity> toolEntities){

        List<Tool> tools = new ArrayList<>();
        for (ToolEntity toolEntity: toolEntities) {
            Tool tool = new Tool(toolEntity);
            tools.add(tool);
        }
        return tools;

    }
}


