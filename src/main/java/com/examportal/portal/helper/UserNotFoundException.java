package com.examportal.portal.helper;

public class UserNotFoundException extends  Exception{

    public UserNotFoundException(){
        super("User with this user name not found is database");

    }
    public UserNotFoundException(String msg ){super(msg);}
}
