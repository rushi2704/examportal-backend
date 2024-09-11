package com.examportal.portal.service;

import com.examportal.portal.model.User;
import com.examportal.portal.model.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    // creating user
    public User creatUser(User user, Set<UserRole>userRoles) throws Exception;

    //for getting user
    public User getUser(String username);

    // for deleting by id
    public void deleteUser(Long userId);

    // deleting by username
   // public boolean deleteUsers(String username);

    //update username
    public User updateUser(User user);


}
