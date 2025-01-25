package com.UST.Store_MicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.UST.Store_MicroService.model.Store;
@Repository
public interface StoreRepository extends JpaRepository<Store,String>{

}
