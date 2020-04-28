package org.soldiers.po.admin;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;

public class AdminIndexPO {
    
    public SelenideElement getElementByLinkText(String link) {
        return $(byLinkText(link));
    }
    
    public AdminAddressesPO openAddressesPage() {
        getElementByLinkText("Adresy").click();
        return Selenide.page(AdminAddressesPO.class);
    }
    
}
