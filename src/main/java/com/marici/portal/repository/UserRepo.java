package com.marici.portal.repository;

import com.marici.portal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
    User findByuserName(String name);
    User findByuserId(int id);
}
