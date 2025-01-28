package com.ust.Login_service.repository;


//import com.ust.Login_service.model.Login;
import com.ust.Login_service.model.LoginDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginDetails,String>{
    @Query("SELECT l from LoginDetails l where l.userId = :userId")
    Optional<LoginDetails> findByUserId(@Param("userId") String userId);
}
