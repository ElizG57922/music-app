package com.example.music.connections;

public class Matches {
    private String userID;
    private String name;
    private String profilePicURL;

    public Matches(String userID, String name, String profilePicURL){
        this.userID=userID;
        this.name=name;
        this.profilePicURL=profilePicURL;
    }
    public String getUserID(){
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProfilePicURL() {
        return profilePicURL;
    }
    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }
}
