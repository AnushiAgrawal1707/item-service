package com.mykart.repository.authentication;

import com.mykart.model.Authentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationRepository extends JpaRepository<Authentication,Integer> {

     public Authentication findById(int user_id);
     public Authentication findByUsername(String username);
}
