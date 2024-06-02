package com.example.mobileproject.ActivityPages;

public class Customerlogin {
    private String userName;
    private String password;

    public Customerlogin(){}
    public Customerlogin(String user,String pass){
        this.userName=user;
        this.password=pass;
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
}
