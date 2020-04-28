package org.soldiers.po.soldier;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.soldiers.po.shared.ItemsPO;
import org.soldiers.po.shared.SettingsPO;

import java.util.List;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.*;

public class SoldierIndexPO {
    
    public SelenideElement getLinkByValue(String value) {
        return $(byLinkText(value));
    }
    
    public List<SelenideElement> getAllElementsByCss(String css) {
        return $$(byCssSelector(css));
    }
    
    public PersonalDataPO openPersonalDataPage() {
        getLinkByValue("Dane osobowe").click();
        return Selenide.page(PersonalDataPO.class);
    }
    
    public SoldierMissionsPO openMissionsPage() {
        getLinkByValue("Misje").click();
        return page(SoldierMissionsPO.class);
    }
    
    public ItemsPO openItemsPage() {
        getLinkByValue("Wyposażenie").click();
        return Selenide.page(ItemsPO.class);
    }
    
    public SettingsPO openSettingsPage() {
        getLinkByValue("Ustawienia").click();
        return Selenide.page(SettingsPO.class);
    }
    
}
