package org.ab.admin.service;

import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

import org.ab.core.config.message.SerializableResourceBundleMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Service
public class I18nService {

	@Autowired
	SerializableResourceBundleMessageSource messageBundle;

	@Setter
	@Getter
	private Locale currentLocale;

	@Value("${locales.supported}")
	private String locales;

	public Set<String> getLocales() {
		Set<String> localeSet = new HashSet<>();

		String[] localeCodes = locales.split(",");

		for (String locale : localeCodes) {
			localeSet.add(locale.trim());
		}

		return localeSet;
	}

	public Properties getAllProperties(String lang) {
		return messageBundle.getAllProperties(new Locale(lang));
	}
	
	public Properties allMessages() {
		return messageBundle.getAllProperties(currentLocale);
	}

}
