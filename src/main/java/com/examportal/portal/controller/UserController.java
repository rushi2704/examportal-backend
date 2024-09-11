package com.examportal.portal.controller;

import com.examportal.portal.model.Role;
import com.examportal.portal.model.User;
import com.examportal.portal.model.UserRole;
import com.examportal.portal.repo.UserRepository;
import com.examportal.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

   // for creating user


    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {

        user.setProfile("default.png");
        ///// goging to encoded  password with BcryptpasswordEncoder
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword() ));

        Set<UserRole> roles= new HashSet<>();
        Role role = new Role();
        role.setRoleID(27L);
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        roles.add(userRole);

       return this.userService.creatUser(user,roles);

    }

    // get user by using username
    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
       //return this.userService.getUser(username);
        // using userrepository calling findbyusername method pass user name then will provide user
        return this.userRepository.findByUserName(username);
    }

    // this api for delete user by using userId
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
        return ResponseEntity.ok("Delete SuccessFull");
    }

    // delete by username
   /* @DeleteMapping("/{username}")
    public  ResponseEntity<Void> deleteUsers(@PathVariable String username){
        boolean isDeleted = userService.deleteUsers(username);
        if(isDeleted){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

   //update api
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@RequestBody User username){

        return ResponseEntity.ok(this.userService.updateUser(username));



    }





}
