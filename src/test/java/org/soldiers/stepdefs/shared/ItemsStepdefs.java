package org.soldiers.stepdefs.shared;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.soldiers.AbstractStepdef;
import org.soldiers.po.shared.AllItemsPO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.soldiers.stepdefs.StepdefUtils.convertElements;

public class ItemsStepdefs extends AbstractStepdef {
    
    @Then("^I see equipment table$")
    public void iSeeEquipmentTable() {
        itemsPO.getItemsTable()
                .shouldBe(Condition.visible);
    }
    
    @And("^I have (\\d+) item\\(s\\) in inventory$")
    public void iHaveItemInInventory(int count) {
        itemsPO.getItemsAmount()
                .shouldHave(Condition.text("Posiadasz aktualnie " + count));
    }
    
    @And("^Equipment table contains \"([^\"]*)\" item$")
    public void equipmentTableContainsItem(String item) {
        final List<SelenideElement> items = itemsPO.getItems();
        final List<String> converted = convertElements(items);
        assertThat(converted).contains(item);
    }
    
    @When("^I go to all items page$")
    public void iGoToAllItemsPage() {
        allItemsPO = (AllItemsPO) itemsPO.goToAllItemsPage();
    }
    
    @When("^I click add button$")
    public void iClickAddButton() {
        itemsPO.clickAddNewItemButton();
    }
    
    @And("^I select \"([^\"]*)\" item$")
    public void iSelectItem(String item) {
        itemsPO.selectValue(item);
    }
    
    @And("^I click submit button$")
    public void iClickSubmitButton() {
        itemsPO.clickSubmitButton();
    }
    
    @And("^Equipment table does not contain \"([^\"]*)\" item$")
    public void equipmentTableDoesNotContainItem(String item) {
        final List<SelenideElement> items = itemsPO.getItems();
        final List<String> converted = convertElements(items);
        assertThat(converted).doesNotContain(item);
    }
    
    @When("^I click remove button for \"([^\"]*)\"$")
    public void iClickRemoveButtonFor(String item) {
        itemsPO.getRemoveButtonByText(item)
                .click();
    }
    
}
