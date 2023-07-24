package com.wirail.DBTest.auth;

public class AuthenticationResponse {
    private String token;

    AuthenticationResponse(String token){
        this.token = token;
    }

    AuthenticationResponse(){
        this.token = null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
