package org.soldiers.stepdefs.soldier;

import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.Then;
import org.soldiers.AbstractStepdef;
import org.soldiers.dto.MissionDTO;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SoldierMissionsStepdefs extends AbstractStepdef {
    
    @Then("^I see following missions$")
    public void iSeeFollowingMissions(List<MissionDTO> missionDTOS) {
        final List<SelenideElement> elements = soldierMissionsPO.getAllRowsFromTable();
        final List<MissionDTO> converted = convertToDTO(elements);
        final String[] expected = convertNamesToArray(missionDTOS);
        final String[] actual = convertNamesToArray(converted);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    private String[] convertNamesToArray(List<MissionDTO> missionDTOS) {
        List<String> list = new ArrayList<>();
        for ( MissionDTO missionDTO : missionDTOS ) {
            list.add(missionDTO.getName());
        }
        return list.toArray(new String[0]);
    }
    
    private List<MissionDTO> convertToDTO(List<SelenideElement> elements) {
        List<MissionDTO> result = new ArrayList<>();
        int counter = 1;
        String name = null;
        String groups = null;
        String startDate = null;
        for ( SelenideElement element : elements ) {
            if ( counter == 1 ) {
                name = element.getText();
            } else if ( counter == 2 ) {
                groups = element.getText();
            } else if ( counter == 3 ) {
                startDate = element.getText();
            } else if ( counter == 4 ) {
                result.add(new MissionDTO(name, groups, startDate, element.getText()));
                counter = 1;
                continue;
            }
            counter++;
        }
        return result;
    }
    
}
