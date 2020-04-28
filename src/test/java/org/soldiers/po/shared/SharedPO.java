package org.soldiers.po.shared;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;

public class SharedPO {
    
    public static SelenideElement getPageTitle() {
        return $("title");
    }
    
    public static SelenideElement getAlertTitle() {
        return $(byClassName("swal-title"));
    }
    
    public static SelenideElement getAlertMessage() {
        return $(byClassName("swal-text"));
    }
    
    public static SelenideElement getAlertButton() {
        return $(byClassName("swal-button"));
    }
    
}
