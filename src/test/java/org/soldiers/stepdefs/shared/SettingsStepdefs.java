package org.soldiers.stepdefs.shared;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.soldiers.AbstractStepdef;
import org.soldiers.model.User;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class SettingsStepdefs extends AbstractStepdef {
    
    @Then("^\"([^\"]*)\" input is \"([^\"]*)\" and has proper value$")
    public void inputIsAndHasProperValue(String id, String enabled) {
        final SelenideElement input = settingsPO.getInput(id);
        final User user = userRepository.findByUsername(soldierLogin);
        
        if ( "enabled".equals(enabled) ) {
            input.shouldBe(Condition.enabled);
        } else if ( "disabled".equals(enabled) ) {
            input.shouldBe(Condition.disabled);
        }
        
        if ( "username".equals(id) ) {
            assertThat(input.getValue(), is(user.getUsername()));
        } else if ( "email".equals(id) ) {
            assertThat(input.getValue(), is(user.getEmail()));
        } else if ( "role".equals(id) ) {
            assertThat(input.getValue(), is(user.getRole()
                    .getRole()));
        }
    }
    
    @When("^I click change password button$")
    public void iClickChangePasswordButton() {
        settingsPO.clickEditPasswordButton();
    }
    
    @And("^I enter \"([^\"]*)\" as old password$")
    public void iEnterAsOldPassword(String oldPassword) {
        settingsPO.enterPassword(oldPassword)
                .clickNextButton();
    }
    
    @And("^I enter \"([^\"]*)\" as new password$")
    public void iEnterAsNewPassword(String newPassword) {
        settingsPO.enterPassword(newPassword)
                .clickNextButton();
    }
    
}
