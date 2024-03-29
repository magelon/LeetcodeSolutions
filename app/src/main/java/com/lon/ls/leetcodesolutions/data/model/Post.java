package com.lon.ls.leetcodesolutions.data.model;


import java.util.HashMap;
import java.util.Map;
  import java.io.Serializable;
public class Post {

    public String uid;
    public String author;
    public String title;
    public String body;
    public int stars;
    public int starCount = 0;
   // public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String uid, String author, String title, String body,int stars) {
        this.uid = uid;
        this.author = author;
        this.title = title;
        this.body = body;
        this.stars=stars;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("title", title);
        result.put("body", body);
        result.put("starCount", starCount);
        result.put("stars", stars);

        return result;
    }

}
