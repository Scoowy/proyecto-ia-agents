package com.group.models;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Class
 *
 * @author Scoowy
 * @version 2022.01.15.1615
 */
public class TweetData implements Serializable {
    private String id;
    private String user;
    private String content;
    private int likes;
    private int comments;
    private int goodComments;
    private int badComments;
    private int neutralComments;
    private int retweets;
    private int followers;

    public TweetData(){
    }

    public TweetData(String id){
        this.id = id;
    }

    public TweetData(String id, String user, String content, int likes, int comments, int goodComments, int badComments, int neutralComments, int retweets, int followers) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.likes = likes;
        this.comments = comments;
        this.goodComments = goodComments;
        this.badComments = badComments;
        this.neutralComments = neutralComments;
        this.retweets = retweets;
        this.followers = followers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getGoodComments() {
        return goodComments;
    }

    public void setGoodComments(int goodComments) {
        this.goodComments = goodComments;
    }

    public int getBadComments() {
        return badComments;
    }

    public void setBadComments(int badComments) {
        this.badComments = badComments;
    }

    public int getNeutralComments() {
        return neutralComments;
    }

    public void setNeutralComments(int neutralComments) {
        this.neutralComments = neutralComments;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TweetData.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("user='" + user + "'")
                .add("likes=" + likes)
                .add("comments=" + comments)
                .add("retweets=" + retweets)
                .add("followers=" + followers)
                .toString();
    }
}
