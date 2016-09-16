package com.example.jagdeepsingh.samplepro.firebase.realTimeDatabase.model;

/**
 * Created by Jagdeep.Singh on 13-09-2016.
 */
public class Person {
    //name and password string
    private String name;
    private String password;

    public Person() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}