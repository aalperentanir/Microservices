package com.aalperen.taskUserService.repository;
import com.aalperen.taskUserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{

    User findByEmail(String email);

}