package org.soldiers.stepdefs.soldier;

import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.soldiers.AbstractStepdef;
import org.soldiers.po.shared.SharedPO;
import org.soldiers.stepdefs.StepdefUtils;

import java.util.List;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.hamcrest.CoreMatchers.allOf;

public class SoldierIndexStepdefs extends AbstractStepdef {
    
    @Then("^\"([^\"]*)\" link is visible$")
    public void linkIsVisible(String link) {
        soldierIndexPO.getLinkByValue(link)
                .shouldBe(visible);
    }
    
    @And("^Information about logged user is visible$")
    public void informationAboutLoggedUserIsVisible() {
        soldierIndexPO.getLinkByValue(StepdefUtils.createInformationAboutUser(soldierLogin, soldierEmail))
                .shouldBe(visible);
    }
    
    @When("^I click \"([^\"]*)\" link$")
    public void iClickLink(String link) {
        soldierIndexPO.getLinkByValue(link)
                .click();
    }
    
    @Then("^I am on \"([^\"]*)\" page$")
    public void iAmOnPage(String page) {
        SharedPO.getPageTitle()
                .shouldHave(attribute("text", page));
    }
    
    @And("^I see (\\d+) news$")
    public void iSeeNews(int count) {
        final List<SelenideElement> news = soldierIndexPO.getAllElementsByCss("tr");
        assertThat(news, allOf(hasSize(count), hasSize(newsRepository.findAll()
                .size())));
    }
    
    @And("^I see \"([^\"]*)\" news$")
    public void iSeeNews(String news) {
        soldierIndexPO.getLinkByValue(news)
                .shouldBe(visible);
    }
    
    @When("^I go to personal data page$")
    public void iGoToPersonalDataPage() {
        personalDataPO = soldierIndexPO.openPersonalDataPage();
    }
    
    @When("^I go to missions page$")
    public void iGoToMissionsPage() {
        soldierMissionsPO = soldierIndexPO.openMissionsPage();
    }
    
    @And("^I go to items page$")
    public void iGoToItemsPage() {
        itemsPO = soldierIndexPO.openItemsPage();
    }
    
    @When("^I go to settings page$")
    public void iGoToSettingsPage() {
        settingsPO = soldierIndexPO.openSettingsPage();
    }
    
}
