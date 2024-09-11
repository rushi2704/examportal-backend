package com.examportal.portal.repo;

import com.examportal.portal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUserName(String username);

   // public void deleteByUserName(String username);


}
