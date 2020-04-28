package org.soldiers.po.shared;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class AllItemsPO {
    
    public SelenideElement getAllItemsTable() {
        return $(byXpath("/html/body/div/div/div/div/table"));
    }
    
    public List<SelenideElement> getItems() {
        return $$(byXpath(".//tr/td"));
    }
    
    public Object openDetailedItemPage(String item) {
        $(byLinkText(item)).click();
        return page(DetailedItemPO.class);
    }
    
}
