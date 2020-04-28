package org.soldiers.stepdefs.admin;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.soldiers.AbstractStepdef;
import org.soldiers.dto.AddressDTO;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AdminAddressesStepdefs extends AbstractStepdef {
    
    @Then("^I see addresses table$")
    public void iSeeAddressesTable() {
        adminAddressesPO.getAddressesTable()
                .shouldBe(Condition.visible);
    }
    
    @And("^Addresses table contains$")
    public void addressesTableContains(List<AddressDTO> addressDTOS) {
        Selenide.sleep(2000);
        final List<SelenideElement> elements = adminAddressesPO.getAddresses();
        final List<AddressDTO> converted = convertToDTO(elements);
        
        assertThat(converted).isEqualTo(addressDTOS);
    }
    
    @When("^I type \"([^\"]*)\" in search input$")
    public void iTypeInSearchInput(String town) {
        adminAddressesPO.typeInSearchInput(town);
    }
    
    @And("^I click search button$")
    public void iClickSearchButton() {
        adminAddressesPO.clickSearchButton();
    }
    
    @When("^I click add new address$")
    public void iClickAddNewAddress() {
        adminAddressesPO.clickAddButton();
    }
    
    @And("^I click add new address button$")
    public void iClickAddNewAddressButton() {
        adminAddressesPO.clickAddNewAddressButton();
    }
    
    @Then("^I click button for edit$")
    public void iClickButtonForEdit() {
        adminAddressesPO.clickButtonForEdit();
    }
    
    @And("^I click edit address button$")
    public void iClickEditAddressButton() {
        adminAddressesPO.clickEditButton();
    }
    
    @Then("^I click button for removal$")
    public void iClickButtonForRemoval() {
        adminAddressesPO.clickRemovalButton();
    }
    
    private List<AddressDTO> convertToDTO(List<SelenideElement> elements) {
        List<AddressDTO> result = new ArrayList<>();
        int counter = 1;
        long id = 0;
        String street = null;
        String town = null;
        String postCode = null;
        for ( SelenideElement element : elements ) {
            if ( counter == 1 ) {
                id = Long.parseLong(element.getText());
            } else if ( counter == 2 ) {
                street = element.getText();
            } else if ( counter == 3 ) {
                town = element.getText();
            } else if ( counter == 4 ) {
                postCode = element.getText();
            } else if ( counter == 5 ) {
                counter++;
                continue;
            } else if ( counter == 6 ) {
                counter = 1;
                result.add(new AddressDTO(id, street, town, postCode));
                continue;
            }
            counter++;
        }
        return result;
    }
    
}
