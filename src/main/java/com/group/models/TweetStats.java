package com.group.models;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Class
 *
 * @author Scoowy
 * @version 2022.01.16.2129
 */
public class TweetStats implements Serializable {
    private TweetData tweet;
    private String socialImpact;
    private double retweetRate;
    private double badCommentsRate;
    private double neutralCommentsRate;
    private double goodCommentsRate;
    private double acceptanceLevel;


    public TweetStats(TweetData tweet, String socialImpact, double retweetRate, double badCommentsRate, double neutralCommentsRate, double goodCommentsRate, double acceptanceLevel) {
        this.tweet = tweet;
        this.socialImpact = socialImpact;
        this.retweetRate = retweetRate;
        this.badCommentsRate = badCommentsRate;
        this.neutralCommentsRate = neutralCommentsRate;
        this.goodCommentsRate = goodCommentsRate;
        this.acceptanceLevel = acceptanceLevel;
    }

    public TweetData getTweet() {
        return tweet;
    }

    public void setTweet(TweetData tweet) {
        this.tweet = tweet;
    }

    public String getSocialImpact() {
        return socialImpact;
    }

    public void setSocialImpact(String socialImpact) {
        this.socialImpact = socialImpact;
    }

    public double getRetweetRate() {
        return retweetRate;
    }

    public void setRetweetRate(double retweetRate) {
        this.retweetRate = retweetRate;
    }

    public double getBadCommentsRate() {
        return badCommentsRate;
    }

    public void setBadCommentsRate(double badCommentsRate) {
        this.badCommentsRate = badCommentsRate;
    }

    public double getNeutralCommentsRate() {
        return neutralCommentsRate;
    }

    public void setNeutralCommentsRate(double neutralCommentsRate) {
        this.neutralCommentsRate = neutralCommentsRate;
    }

    public double getGoodCommentsRate() {
        return goodCommentsRate;
    }

    public void setGoodCommentsRate(double goodCommentsRate) {
        this.goodCommentsRate = goodCommentsRate;
    }

    public double getAcceptanceLevel() {
        return acceptanceLevel;
    }

    public void setAcceptanceLevel(double acceptanceLevel) {
        this.acceptanceLevel = acceptanceLevel;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TweetStats.class.getSimpleName() + "[", "]")
                .add("tweet=" + tweet)
                .add("socialImpact='" + socialImpact + "'")
                .add("retweetRate=" + retweetRate)
                .add("badCommentsRate=" + badCommentsRate)
                .add("neutralCommentsRate=" + neutralCommentsRate)
                .add("goodCommentsRate=" + goodCommentsRate)
                .add("acceptanceLevel=" + acceptanceLevel)
                .toString();
    }
}
