package com.example.appbase.Negocio;


import com.google.gson.annotations.SerializedName;

public class PostsComent {

    @SerializedName("postId")
    private int postId;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("body")
    private String body;

    @SerializedName("email")
    private String email;


    public int getIdComent() {
        return id;
    }

    public void setIdComent(int id) {
        this.id = id;

    }

    public int getIdPosts() {
        return postId;
    }

    public void setIdPosts(int id) {
        this.postId = id;
    }

    public String getemail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}