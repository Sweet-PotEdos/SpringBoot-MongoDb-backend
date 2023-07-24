package com.wirail.DBTest.auth;


public class RegisterRequest {

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    RegisterRequest(){
        this.firstname = null;
        this.lastname = null;
        this.email = null;
        this.password = null;
    }

    RegisterRequest( String firstname, String lastname, String email, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    //getters and setters
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
