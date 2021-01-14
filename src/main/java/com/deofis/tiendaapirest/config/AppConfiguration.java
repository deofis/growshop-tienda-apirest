package com.deofis.tiendaapirest.config;

import com.deofis.tiendaapirest.pagos.services.strategy.PagoStrategyFactory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AppConfiguration {

    private final BeanFactory beanFactory;

    public ServiceLocatorFactoryBean pagoFactoryLocator() {
        final ServiceLocatorFactoryBean locator = new ServiceLocatorFactoryBean();
        locator.setServiceLocatorInterface(PagoStrategyFactory.class);
        locator.setBeanFactory(beanFactory);

        return locator;
    }

    @Bean
    public PagoStrategyFactory pagoStrategyFactory() {
        final ServiceLocatorFactoryBean locator = pagoFactoryLocator();
        locator.afterPropertiesSet();
        return (PagoStrategyFactory) locator.getObject();
    }
}
