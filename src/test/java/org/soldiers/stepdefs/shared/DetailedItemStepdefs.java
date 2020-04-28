package org.soldiers.stepdefs.shared;

import com.codeborne.selenide.Condition;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.soldiers.AbstractStepdef;

public class DetailedItemStepdefs extends AbstractStepdef {
    
    @Then("^I see description about item$")
    public void iSeeDescriptionAboutItem() {
        detailedItemPO.getText()
                .shouldNotBe(Condition.empty);
    }
    
    @And("^I see image of an item$")
    public void iSeeImageOfAnItem() {
        detailedItemPO.getImage()
                .isImage();
    }
    
}
