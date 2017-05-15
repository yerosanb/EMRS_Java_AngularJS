package org.oib.core.config.message;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocaleMessageConfig extends WebMvcConfigurerAdapter {
	
	 @Bean
	    public SessionLocaleResolver localeResolver() {
	        SessionLocaleResolver slr = new SessionLocaleResolver();
	        slr.setDefaultLocale(Locale.US);
	        return slr;
	    }
	    @Bean
	    public ReloadableResourceBundleMessageSource messageSource() {
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasename("classpath:locale/messages");
	        messageSource.setCacheSeconds(3600); //refresh cache once per hour
	        return messageSource;
	    }
	    
	    @Bean
	    public LocalValidatorFactoryBean validator()
	    {
	        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	        bean.setValidationMessageSource(messageSource());
	        return bean;
	    }
	    
	    @Override
	    public Validator getValidator()
	    {
	        return validator();
	    }
	  
	    @Bean
	    public MethodValidationPostProcessor methodValidationPostProcessor() {
	        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
	        methodValidationPostProcessor.setValidator(validator());
	        return methodValidationPostProcessor;
	    }

}
