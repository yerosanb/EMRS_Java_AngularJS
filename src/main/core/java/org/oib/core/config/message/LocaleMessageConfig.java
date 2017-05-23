package org.oib.core.config.message;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocaleMessageConfig extends WebMvcConfigurerAdapter {

	private SerializableResourceBundleMessageSource messageSource;

	 @Override
     public void addInterceptors(InterceptorRegistry registry) {
             registry.addInterceptor(localeChangeInterceptor());
     }

     @Bean
     public LocaleChangeInterceptor localeChangeInterceptor() {
             LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
             localeChangeInterceptor.setParamName("locale");
             return localeChangeInterceptor;
     }
     
	@Bean
	public SessionLocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Autowired
	public LocaleMessageConfig(SerializableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource);
		return bean;
	}

	public Validator getValidator() {
		return validator();
	}

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
		methodValidationPostProcessor.setValidator(validator());
		return methodValidationPostProcessor;
	}

}
