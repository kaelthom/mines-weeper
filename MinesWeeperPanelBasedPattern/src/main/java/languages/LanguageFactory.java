package languages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public final class LanguageFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageFactory.class);
    private static LanguageFactory instance = null;
    private String language;
    private String country;
    private Locale locale;

    private LanguageFactory() {

        ResourceBundle languageResourceBundle = ResourceBundle.getBundle("language");
        setLanguage(languageResourceBundle.getString("language"));
        LOGGER.info(language);

        setCountry(languageResourceBundle.getString("country"));
        LOGGER.info(country);
        locale = new Locale(language, country);
    }

    public static void initLanguage() {
        if (LanguageFactory.instance == null) {
            instance = new LanguageFactory();
        }
    }

    public static LanguageFactory getInstance() {
        initLanguage();
        return instance;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
