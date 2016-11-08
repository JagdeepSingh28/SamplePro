package com.example.jagdeepsingh.samplepro.firebase.realTimeDatabase.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jagdeep.Singh on 13-09-2016.
 */
public class Person implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(password);
    }

    public static final Parcelable.Creator CREATOR = new Creator() {
        @Override
        public Person createFromParcel(Parcel source) {
            return new Person(source);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[0];
        }
    };

    Person(Parcel parcel){
        name        = parcel.readString();
        password    = parcel.readString();
    }
}