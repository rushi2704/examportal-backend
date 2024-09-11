package com.examportal.portal.controller;

import com.examportal.portal.config.JwtUtils;
import com.examportal.portal.helper.UserNotFoundException;
import com.examportal.portal.model.JwtRequest;
import com.examportal.portal.model.JwtResponse;
import com.examportal.portal.model.User;
import com.examportal.portal.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try{

            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch(UsernameNotFoundException e ){
            e.printStackTrace();
            throw new UserNotFoundException();
        }
  /// authenticate user
        UserDetails userDetails=  this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        String username = userDetails.getUsername();
        return ResponseEntity.ok(new JwtResponse(token,username));
    }

    private  void authenticate(String username, String password) throws Exception {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        }catch(DisabledException e){
            throw new Exception("User Disable"+e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("invalid credentials"+e.getMessage());
        }

    }


    // return the details of current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal)
    {
    return ((User)this.userDetailsService.loadUserByUsername(principal.getName()));
    }
}
