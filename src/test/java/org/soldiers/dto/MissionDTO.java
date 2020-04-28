package org.soldiers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MissionDTO {
    
    private String name;
    
    private String groups;
    
    private String startDate;
    
    private String endDate;
    
    public static MissionDTO toDTO(String name, String groups, String startDate, String endDate) {
        return new MissionDTO(name, groups, startDate, endDate);
    }
    
}
