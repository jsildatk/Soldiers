package org.soldiers.po.shared;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SettingsPO {
    
    public SelenideElement getInput(String id) {
        return $(byId(id));
    }
    
    public SettingsPO clickNextButton() {
        $(byXpath("/html/body/div[2]/div/div[3]/div/button")).click();
        return this;
    }
    
    public SettingsPO clickEditPasswordButton() {
        $(byXpath("/html/body/div/div/div/div/div/button")).click();
        return this;
    }
    
    public SettingsPO enterPassword(String oldPassword) {
        $(byXpath("/html/body/div[2]/div/div[2]/input")).setValue(oldPassword);
        return this;
    }
    
}
