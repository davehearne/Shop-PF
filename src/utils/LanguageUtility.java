package utils;

import java.util.HashSet;
import java.util.Set;

public class LanguageUtility {

    //Available languages in the app
    private static Set<String> languages = new HashSet<>(){{
        add("ENGLISH");
        add("GERMAN");
        add("FRENCH");
        add("SPANISH");
    }};


    public static Set<String> getLanguages() {
        return languages;
    }


    public static boolean isValidLanguage(String languageToCheck) {
        for (String language : languages) {
            if (language.equalsIgnoreCase(languageToCheck)) {
                return true;
            }
        }
        return false;
    }

}