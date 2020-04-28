package org.soldiers.stepdefs.index;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.soldiers.AbstractStepdef;
import org.soldiers.po.index.IndexPO;
import org.soldiers.po.shared.SharedPO;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;

public class IndexStepdefs extends AbstractStepdef {
    
    private IndexPO indexPO;
    
    @Given("^I open index page$")
    public void iOpenIndexPage() {
        Configuration.browserSize = "1920x1080";
        indexPO = open("/", IndexPO.class);
    }
    
    @And("^I click \"([^\"]*)\" button$")
    public void iClickButton(String button) {
        indexPO.clickButtonByValue(button);
    }
    
    @Then("^Message \"([^\"]*)\" is displayed in container with \"([^\"]*)\" \"([^\"]*)\"$")
    public void messageIsDisplayedInContainerWith(String message, String selector, String value) {
        final SelenideElement element = indexPO.getElementBySelectorAndName(selector, value);
        element.shouldHave(Condition.text(message));
    }
    
    @And("^I switch pane to register$")
    public void iSwitchPaneToRegister() {
        indexPO.switchPaneToRegister();
    }
    
    @Then("^I select (\\d+) value$")
    public void iSelectValue(int index) {
        indexPO.selectValue(index);
    }
    
    @And("^I enter \"([^\"]*)\" as \"([^\"]*)\"$")
    public void iEnterAs(String value, String selector) {
        indexPO.enterValue(value, selector);
    }
    
    @Then("^\"([^\"]*)\" button is \"([^\"]*)\"$")
    public void buttonIs(String button, String status) {
        final SelenideElement element = indexPO.getElementByValue(button);
        if ( "enabled".equals(status) ) {
            element.shouldBe(enabled);
        } else if ( "disabled".equals(status) ) {
            element.shouldBe(disabled);
        }
    }
    
    @Given("^I am logged in as \"([^\"]*)\"$")
    public void iAmLoggedInAs(String role) {
        iOpenIndexPage();
        if ( "soldier".equals(role) ) {
            soldierIndexPO = indexPO.logInAsSoldier();
        } else if ( "admin".equals(role) ) {
            adminIndexPO = indexPO.logInAsAdmin();
        }
        
    }
    
    @Given("^I am on index page$")
    public void iAmOnPage() {
        SharedPO.getPageTitle()
                .shouldHave(attribute("text", " Strona główna wojska "));
    }
    
    @Then("^I see alert with \"([^\"]*)\" title$")
    public void iSeeAlertWithTitle(String title) {
        SharedPO.getAlertTitle()
                .shouldHave(text(title));
    }
    
    @And("^I click alert button$")
    public void iClickAlertButton() {
        SharedPO.getAlertButton()
                .click();
    }
    
    @And("^Alert has \"([^\"]*)\" text$")
    public void alertHasText(String text) {
        SharedPO.getAlertMessage()
                .shouldHave(text(text));
    }
    
}
