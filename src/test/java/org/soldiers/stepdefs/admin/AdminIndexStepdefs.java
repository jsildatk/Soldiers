package org.soldiers.stepdefs.admin;

import com.codeborne.selenide.Condition;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.soldiers.AbstractStepdef;
import org.soldiers.stepdefs.StepdefUtils;

public class AdminIndexStepdefs extends AbstractStepdef {
    
    @Then("^I can see \"([^\"]*)\" link$")
    public void iCanSeeLink(String link) {
        adminIndexPO.getElementByLinkText(link)
                .shouldBe(Condition.visible);
    }
    
    @And("^I can see information about logged admin$")
    public void iCanSeeInformationAboutLoggedAdmin() {
        adminIndexPO.getElementByLinkText(StepdefUtils.createInformationAboutUser(adminLogin, adminEmail))
                .shouldBe(Condition.visible);
    }
    
    @When("^I go to addresses page$")
    public void iGoToAddressesPage() {
        adminAddressesPO = adminIndexPO.openAddressesPage();
    }
    
}
