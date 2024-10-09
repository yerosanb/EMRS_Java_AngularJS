package org.ab.core.config.message;

import java.util.Locale;
import java.util.Properties;

import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SerializableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    public Properties getAllProperties(Locale locale) {
        clearCacheIncludingAncestors();
        PropertiesHolder propertiesHolder = getMergedProperties(locale);
        Properties properties = propertiesHolder.getProperties();

        return properties;
    }
    
    private SerializableResourceBundleMessageSource(){
    	super.setBasename("classpath:locale/messages");
        super.setCacheSeconds(3600); //refres
    }
    
    
}