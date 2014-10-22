package languages;

import java.util.Locale;
import java.util.ResourceBundle;

public final class LanguageFactory {
	private static LanguageFactory instance= null;
	private String language;
	private String country;
	private Locale locale;

	private LanguageFactory(){
		setLanguage(ResourceBundle.getBundle("language").getString("language"));
		System.out.println(language);
		
		setCountry(ResourceBundle.getBundle("language").getString("country"));
		System.out.println(country);
		locale = new Locale(language, country);
	}

	public final static void initLanguage(){
		if (LanguageFactory.instance==null) {
			instance = new LanguageFactory();
		}
	}
	public final static LanguageFactory getInstance() {
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
