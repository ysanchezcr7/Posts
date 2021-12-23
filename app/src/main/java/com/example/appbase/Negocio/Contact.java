package com.example.appbase.Negocio;

import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Contact {

    private String numero;
    private String nombre;
    private  String contactId;
    private Bitmap img;
    // constructor
    public Contact(String userName, String contactNumber, String contacID, Bitmap imageView) {
        this.nombre = userName;
        this.contactId = contacID;
        this.numero = contactNumber;
        this.img = imageView;
    }


    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;

    }


    public String getNumero() {
        return numero;
    }

    public void setNumero(String num) {
        this.numero = num;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nom) {
        this.nombre = nom;
    }

    public Bitmap getImg(){
        return img;
    }
    public void setImg(Bitmap img) {
        this.img = img;
    }



}