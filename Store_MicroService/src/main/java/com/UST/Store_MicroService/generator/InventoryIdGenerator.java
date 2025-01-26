package com.UST.Store_MicroService.generator;

import com.UST.Store_MicroService.repository.InventoryRepository;
import com.UST.Store_MicroService.repository.StoreRepository;
import jdk.jfr.Category;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.UUID;
@Component
public class InventoryIdGenerator implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.applicationContext = context;
    }

    public String generateId() {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext is not initialized");
        }

        InventoryRepository inventoryRepository = applicationContext.getBean(InventoryRepository.class);
        long count = inventoryRepository.getNumberOfUniqueInventory(); // Get the count of unique inventories
        return "INVE" + (count + 1)+UUID.randomUUID().toString().substring(0, 5).toUpperCase(); // Generate ID like INVE1, INVE2, etc.
    }
}

