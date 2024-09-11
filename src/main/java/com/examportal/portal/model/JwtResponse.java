package com.examportal.portal.model;

public class JwtResponse
{

    String token;
    String username;

    public JwtResponse(String token,String username) {
        this.token = token;
        this.username=username;
    }

    public JwtResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
