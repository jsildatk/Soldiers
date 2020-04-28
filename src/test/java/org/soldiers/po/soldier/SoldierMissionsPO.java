package org.soldiers.po.soldier;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;

public class SoldierMissionsPO {
    
    public List<SelenideElement> getAllRowsFromTable() {
        return $$(byXpath(".//tr/td"));
    }
    
}
