package com.example.seg2105project;

public class Rating {

    private int totalRatings;
    private int totalRaters;

    public Rating(){
        totalRaters = 0;
        totalRatings = 0;
    }

    public int getRating(){
        return totalRatings/totalRaters;
    }

    public int getTotalRaters() {
        return totalRaters;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRaters() {
        this.totalRaters += 1;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings += totalRatings;
    }
}
