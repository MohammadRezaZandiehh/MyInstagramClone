package com.example.myinstagramclone.model;

import java.util.List;

public class PixabayPosts {
    int total;
    int totalHits;
    List<Posts> hits;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<Posts> getHits() {
        return hits;
    }

    public void setHits(List<Posts> hits) {
        this.hits = hits;
    }
}
