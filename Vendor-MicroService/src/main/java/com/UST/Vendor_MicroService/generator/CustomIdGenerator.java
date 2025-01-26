package com.UST.Vendor_MicroService.generator;


import com.UST.Vendor_MicroService.repository.VendorRepository;
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

        VendorRepository vendorRepository = applicationContext.getBean(VendorRepository.class);
        long count = vendorRepository.count(); 
        return "VEND" + (count + 1) + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
}