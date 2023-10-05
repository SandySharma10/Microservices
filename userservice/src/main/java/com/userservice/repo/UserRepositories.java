package com.userservice.repo;

import com.userservice.entityes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<User, String> {

    //we can write own query and function
}
