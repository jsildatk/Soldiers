package org.soldiers.stepdefs.shared;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.soldiers.AbstractStepdef;
import org.soldiers.po.shared.DetailedItemPO;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.soldiers.stepdefs.StepdefUtils.convertElements;

public class AllItemsStepdefs extends AbstractStepdef {
    
    @Then("^I see table with all items$")
    public void iSeeTableWithAllItems() {
        allItemsPO.getAllItemsTable()
                .shouldBe(Condition.visible);
    }
    
    @And("^I see (\\d+) items$")
    public void iSeeItems(int count) {
        final List<SelenideElement> items = allItemsPO.getItems();
        assertThat(items).hasSize(count);
    }
    
    @And("^All items table contains \"([^\"]*)\" item$")
    public void allItemsTableContainsItem(String item) {
        final List<String> allItems = convertElements(allItemsPO.getItems());
        assertThat(allItems).contains(item);
    }
    
    @When("^I click \"([^\"]*)\" item$")
    public void iClickItem(String item) {
        detailedItemPO = (DetailedItemPO) allItemsPO.openDetailedItemPage(item);
    }
    
}
