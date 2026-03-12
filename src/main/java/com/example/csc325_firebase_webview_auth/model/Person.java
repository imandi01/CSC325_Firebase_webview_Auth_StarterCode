/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.csc325_firebase_webview_auth.model;

/**
 *
 * @author MoaathAlrajab
 *
 *
 *
 *
 * Model:
 *
 *
 */

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class Person {

    private StringProperty name;
    private StringProperty major;
    private IntegerProperty age;

    public Person(String name, String major, int age) {
        this.name = new SimpleStringProperty(name);
        this.major = new SimpleStringProperty(major);
        this.age = new SimpleIntegerProperty(age);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty majorProperty() {
        return major;
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public String getName() {
        return name.get();
    }

    public String getMajor() {
        return major.get();
    }

    public int getAge() {
        return age.get();
    }
}