package com.wirail.DBTest;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Document(collection = "mycollection")
public class User implements UserDetails {

    @Id
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    private Role role;


    public User(String firstname, String lastname, String email, String password){
        this.id = null;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }

    // getters and setters

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setPassword(String pssw){
        this.password = pssw;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getId(){
        return this.id;
    }

    @Override
    public String getPassword(){
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

}