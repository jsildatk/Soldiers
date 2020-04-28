package org.soldiers.po.admin;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AdminAddressesPO {
    
    public SelenideElement getAddressesTable() {
        return $(byId("addressesTable"));
    }
    
    public List<SelenideElement> getAddresses() {
        return $$(byXpath(".//tr/td"));
    }
    
    public AdminAddressesPO typeInSearchInput(String value) {
        $(byId("searchAddress")).setValue(value);
        return this;
    }
    
    public AdminAddressesPO clickButtonForEdit() {
        $(byCssSelector("#\\37  > td:nth-child(5) > button")).click();
        return this;
    }
    
    public AdminAddressesPO clickEditButton() {
        $(byCssSelector("#updateAddressForm > input.btn.btn-primary")).click();
        return this;
    }
    
    public AdminAddressesPO clickSearchButton() {
        $(byCssSelector("body > div > div.row > div > div > div > div > button")).click();
        return this;
    }
    
    public AdminAddressesPO clickAddButton() {
        $(byXpath("/html/body/div/div[1]/div/div/button")).scrollTo()
                .click(); ;
        return this;
    }
    
    public AdminAddressesPO clickAddNewAddressButton() {
        $(byCssSelector("#addAddressForm > input.btn.btn-primary")).click();
        return this;
    }
    
    public AdminAddressesPO clickRemovalButton() {
        $(byCssSelector("#\\37  > td:nth-child(6) > button")).click();
        return this;
    }
    
}
