package com.ust.Billing_Service.generator;



import com.ust.Billing_Service.entity.Customer;
import com.ust.Billing_Service.repository.CustomerRepository;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;

@Component // Register this class as a Spring bean
public class CustomerIdGenerator implements IdentifierGenerator, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);
        int count = (int) customerRepository.count();
        return "CUST" + (count + 1) + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}