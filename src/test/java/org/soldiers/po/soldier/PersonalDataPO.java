package org.soldiers.po.soldier;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class PersonalDataPO {
    
    public SelenideElement getElementById(String id) {
        return $(byId(id));
    }
    
    public SelenideElement getEditButton() {
        return $(byXpath("//*[@id=\"updateSoldierData\"]/button"));
    }
    
}
