package com.example.music;

public class Matches {
    private String userID;

    public Matches(String userID){
        this.userID=userID;

    }
    public String getUserID(){
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
