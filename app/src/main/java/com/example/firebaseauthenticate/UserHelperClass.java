package com.example.firebaseauthenticate;

public class UserHelperClass {

    String Name,Number,Adderess;

    public UserHelperClass(String name, String number, String adderess) {
        Name = name;
        Number = number;
        Adderess = adderess;
    }
    public UserHelperClass()
    {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getAdderess() {
        return Adderess;
    }

    public void setAdderess(String adderess) {
        Adderess = adderess;
    }
}
