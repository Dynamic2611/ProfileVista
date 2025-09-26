package com.ankit.profilevista.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    public int id;

    @SerializedName("firstName")
    public String firstName;

    @SerializedName("lastName")
    public String lastName;

    public String maidenName;
    public int age;
    public String gender;
    public String email;
    public String phone;
    public String username;
    public String password;
    public String birthDate;
    public String image;
    public String bloodGroup;
    public double height;
    public double weight;
    public String eyeColor;

    public Hair hair;
    public Address address;
    public Company company;
    public Bank bank;

    public String domain;
    public String ip;
    public String university;
    public String ein;
    public String ssn;
    public String userAgent;
}
