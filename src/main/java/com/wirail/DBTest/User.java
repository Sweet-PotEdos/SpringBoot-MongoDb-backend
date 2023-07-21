package com.wirail.DBTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mycollection")
public class User {

    @Id
    private String id;

    private String username;
    private String password;

    public User(String username, String password){
        this.id = null;
        this.username = username;
        this.password = password;
    }

    // getters and setters

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public String getPassword(){
        return this.password;
    }

}