package com.example.pimpavee.mylazyinstragram.model;

/**
 * Created by Pimpavee on 18/10/2560.
 */

public class Post {
    private String url;
    private int like;
    private int comment;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }
}
