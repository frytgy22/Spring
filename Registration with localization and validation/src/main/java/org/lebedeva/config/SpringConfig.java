package org.lebedeva.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class SpringConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages/validation", "classpath:messages/label");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public AcceptHeaderLocaleResolver acceptHeaderLocaleResolver() {
        return new AcceptHeaderLocaleResolver();
    }
}
