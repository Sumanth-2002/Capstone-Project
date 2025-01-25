package com.ust.Billing_Service.generator;


import com.ust.Billing_Service.repository.BillingRepository;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component // Register this class as a Spring bean
public class CustomIdGenerator implements IdentifierGenerator, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        BillingRepository billingRepository = applicationContext.getBean(BillingRepository.class);
        int count = (int) billingRepository.count();
        return "BILL" + (count + 1) + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}