package com.wirail.DBTest.auth;

public class AuthenticationRequest {
    private String email;
    String password;

    AuthenticationRequest(){
        this.email = null;
        this.password = null;
    }

    AuthenticationRequest(String email, String password){
        this.email = null;
        this.password = null;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
