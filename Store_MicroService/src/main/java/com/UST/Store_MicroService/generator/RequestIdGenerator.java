package com.UST.Store_MicroService.generator;

import com.UST.Store_MicroService.repository.StoreRepository;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component
public class RequestIdGenerator implements IdentifierGenerator, ApplicationContextAware {
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

        StoreRepository storeRepository = applicationContext.getBean(StoreRepository.class);
        long count = storeRepository.count();
        return "REQU" + (count + 1) + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}
