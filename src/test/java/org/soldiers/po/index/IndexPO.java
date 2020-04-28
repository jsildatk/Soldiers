package org.soldiers.po.index;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.soldiers.po.admin.AdminIndexPO;
import org.soldiers.po.soldier.SoldierIndexPO;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class IndexPO {
    
    public SelenideElement getElementByValue(String value) {
        return $(byXpath("//*[@value='" + value + "']"));
    }
    
    public SelenideElement getElementBySelectorAndName(String selector, String name) {
        if ( "id".equals(selector) ) {
            return $(byId(name));
        } else if ( "class".equals(selector) ) {
            return $(byClassName(name));
        }
        return null;
    }
    
    public IndexPO clickButtonByValue(String value) {
        getElementByValue(value).click();
        return this;
    }
    
    public IndexPO switchPaneToRegister() {
        $(byId("register-form-link")).click();
        return this;
    }
    
    public IndexPO selectValue(int index) {
        $(byId("select")).selectOption(index);
        return this;
    }
    
    public IndexPO enterValue(String value, String name) {
        $$(byId(name)).filter(Condition.visible)
                .get(0)
                .setValue(value);
        return this;
    }
    
    public SoldierIndexPO logInAsSoldier() {
        enterValue("soldier_test", "username").enterValue("zaq1@WSX", "password")
                .clickButtonByValue("Zaloguj");
        return Selenide.page(SoldierIndexPO.class);
    }
    
    public AdminIndexPO logInAsAdmin() {
        enterValue("admin_test", "username").enterValue("zaq1@WSX", "password")
                .clickButtonByValue("Zaloguj");
        return Selenide.page(AdminIndexPO.class);
    }
    
}
