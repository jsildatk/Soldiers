package org.soldiers.po.shared;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class DetailedItemPO {
    
    public SelenideElement getText() {
        return $(byXpath("/html/body/div/div/div/div/table/thead/tr/th"));
    }
    
    public SelenideElement getImage() {
        return $(byXpath("/html/body/div/div/div/div/table/tbody/tr/td/img"));
    }
    
}
