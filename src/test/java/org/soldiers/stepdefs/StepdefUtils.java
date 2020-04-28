package org.soldiers.stepdefs;

import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.List;

public class StepdefUtils {
    
    public static List<String> convertElements(List<SelenideElement> elements) {
        List<String> result = new ArrayList<>();
        for ( SelenideElement selenideElement : elements ) {
            result.add(selenideElement.getText());
        }
        return result;
    }
    
    public static String createInformationAboutUser(String login, String email) {
        return "Zalogowany jako: " + login + " (" + email + ")";
    }
    
}
