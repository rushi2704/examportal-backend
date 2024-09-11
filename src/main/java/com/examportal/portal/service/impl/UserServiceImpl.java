package com.examportal.portal.service.impl;

import com.examportal.portal.helper.UserFoundException;
import com.examportal.portal.model.User;
import com.examportal.portal.model.UserRole;
import com.examportal.portal.repo.RoleRepository;
import com.examportal.portal.repo.UserRepository;
import com.examportal.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

   // creating user
    @Override
    public User creatUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findByUserName(user.getUserName());
        if (local != null) {
            System.out.println("user is already there");
            throw new UserFoundException();
        } else {
            //user create
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    // getting user by user name
    @Override
    public User getUser(String username) {
        return this.userRepository.findByUserName(username);
    }

    // delete user by using uerId
    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

/*    // delete by user name
    @Override
    public boolean deleteUsers(String username) {
       User user=  this.userRepository.findByUserName(username);
       if(user!= null){
           userRepository.deleteByUserName(username);
           return true;
       }
       return false;
    }*/
   /* @Override
    public User updateUser(String username, User updatedUser) {
        Optional<User> euser = userRepository.findByUserName(username);
        if (euser.isPresent()) {
            User user = euser.get();
            user.setFirstName(updatedUser.getFirstName());

            return userRepository.save(user);
        }
        return null;

    }*/
}