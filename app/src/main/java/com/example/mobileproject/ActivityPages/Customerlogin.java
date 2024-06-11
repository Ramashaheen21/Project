package com.example.mobileproject.ActivityPages;

public class Customerlogin {
    private String userName;
    private String password;
    private String Firstname;
    private String Lastname;
    private String gender;
    private int PhoneNum;


    public Customerlogin(){}
    public Customerlogin(String user,String pass){
        this.userName=user;
        this.password=pass;
    }

    public Customerlogin(String userName, String password, String firstname, String lastname, String gender, int phoneNum) {
        this.userName = userName;
        this.password = password;
        Firstname = firstname;
        Lastname = lastname;
        this.gender = gender;
        PhoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        PhoneNum = phoneNum;
    }
}
