package com.UST.Product_Service.generator;

import com.UST.Product_Service.repository.ProductRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class CustomIdGenerator implements IdentifierGenerator, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.applicationContext = context;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext is not initialized");
        }

        ProductRepository productRepository = applicationContext.getBean(ProductRepository.class);
        long count = productRepository.count(); // Avoid casting to int
        return "PROD" + (count + 1) + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}
