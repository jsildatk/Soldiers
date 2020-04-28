package org.soldiers.po.shared;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ItemsPO {
    
    public SelenideElement getItemsTable() {
        return $(byId("equipmentTable"));
    }
    
    public SelenideElement getItemsAmount() {
        return $(byId("itemsAmount"));
    }
    
    public List<SelenideElement> getItems() {
        return $$(byXpath(".//tr/td"));
    }
    
    public SelenideElement getRemoveButtonByText(String text) {
        return $(byXpath("/html/body/div/div[1]/div/div/table/tbody/tr/td[text()='" + text + "']/following-sibling::td[1]"));
    }
    
    public ItemsPO clickAddNewItemButton() {
        $(byXpath("/html/body/div/div[1]/div/div/button")).click();
        return this;
    }
    
    public ItemsPO clickSubmitButton() {
        $(byXpath("//*[@id=\"addItem\"]/input[3]")).click();
        return this;
    }
    
    public ItemsPO selectValue(String value) {
        $(byId("item")).selectOption(value);
        return this;
    }
    
    public Object goToAllItemsPage() {
        $(byLinkText("Przeglądaj przedmioty")).click();
        return page(AllItemsPO.class);
    }
    
}
