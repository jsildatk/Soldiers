package org.soldiers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressDTO {
    
    private long id;
    
    private String street;
    
    private String town;
    
    private String postCode;
    
}
