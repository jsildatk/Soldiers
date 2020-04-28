package org.soldiers.stepdefs.shared;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.soldiers.AbstractStepdef;

public class PersonalDataStepdefs extends AbstractStepdef {
    
    @Then("^\"([^\"]*)\" input is \"([^\"]*)\" and is \"([^\"]*)\"$")
    public void inputIsAndIs(String id, String enabled, String empty) {
        final SelenideElement elementById = personalDataPO.getElementById(id);
        if ( "enabled".equals(enabled) ) {
            elementById.shouldBe(Condition.enabled);
        } else if ( "disabled".equals(enabled) ) {
            elementById.shouldBe(Condition.disabled);
        }
        if ( "empty".equals(empty) ) {
            elementById.shouldBe(Condition.empty);
        } else if ( "not empty".equals(empty) ) {
            elementById.shouldNotBe(Condition.empty);
        }
    }
    
    @And("^Edit button is \"([^\"]*)\"$")
    public void editButtonIs(String enabled) {
        final SelenideElement selenideElement = personalDataPO.getEditButton();
        if ( "enabled".equals(enabled) ) {
            selenideElement.shouldBe(Condition.enabled);
        } else if ( "disabled".equals(enabled) ) {
            selenideElement.shouldBe(Condition.disabled);
        }
    }
    
    @And("^I select (\\d+) value in address input$")
    public void iSelectValueInAddressInput(int index) {
        personalDataPO.getElementById("address")
                .selectOption(index);
    }
    
    @Then("^I click edit button$")
    public void iClickEditButton() {
        personalDataPO.getEditButton()
                .click();
    }
    
}
