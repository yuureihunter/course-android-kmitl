package com.example.pimpavee.mylazyinstragram.model;

/**
 * Created by Pimpavee on 19/10/2560.
 */

public class FollowRequest {

    private String user;
    private boolean isFollow;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }
}
