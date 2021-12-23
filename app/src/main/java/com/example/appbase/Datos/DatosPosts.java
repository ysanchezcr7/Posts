package com.example.appbase.Datos;


import com.example.appbase.Negocio.Posts;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DatosPosts {

    private ArrayList<Posts> data;
    public ArrayList<Posts> getData() {
        return data;
    }
    public void setData(ArrayList<Posts> data) {
        this.data = data;
    }

}