package com.example.appbase.Negocio;


import com.google.gson.annotations.SerializedName;

public class Posts {

    @SerializedName("userId")
    private int userId;
    @SerializedName("id")
    private int id;
    @SerializedName("title")
    private String title;
    @SerializedName("body")
    private String body;


    public int getIdUsuario() {
        return userId;
    }

    public void setIdUsuaio(int id) {
        this.userId = id;

    }

    public int getIdPosts() {
        return id;
    }

    public void setIdPosts(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}