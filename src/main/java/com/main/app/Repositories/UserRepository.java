package com.main.app.Repositories;


import com.main.app.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
