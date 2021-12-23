package com.example.appbase.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbase.DetalleContact;
import com.example.appbase.Negocio.Contact;
import com.example.appbase.R;
import com.example.appbase.VerComentPost;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListaContactAdapter extends RecyclerView.Adapter<ListaContactAdapter.PostViewHolder> {


    DetalleContact detallesFragmen;
    public static ArrayList<Contact> contacts;
    public static Context context;
    public static Contact contact;
    public static ListaContactAdapter adapter;


    private static View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public ListaContactAdapter(ArrayList<Contact> contacts) {

        this.contacts = contacts;
        //this.context = context;
        adapter = this;


    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item, parent, false);

        PostViewHolder viewHolder = new PostViewHolder(v);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(PostViewHolder postsViewHolder, int position) {

        contact = contacts.get(position);

        postsViewHolder.imgFotos.setImageBitmap(contact.getImg());
        postsViewHolder.tvNumero.setText(contact.getNumero());
        postsViewHolder.tvNombre.setText(contact.getNombre());

        postsViewHolder.setIsRecyclable(false);

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setData(ArrayList<Contact> listcontacts) {

        this.contacts = listcontacts;
        notifyDataSetChanged();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView imgFotos;
        private TextView tvNombre;
        private TextView tvNumero;


        public PostViewHolder(final View itemView) {

            super(itemView);
            //tvUsserId = itemView.findViewById(R.id.tvUsserID);

            imgFotos = itemView.findViewById(R.id.imgListContact);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvNumero = itemView.findViewById(R.id.tvNumero);

            context = itemView.getContext();
            //itemView.setOnClickListener(this);


            itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(View view) {

                    int selected = getLayoutPosition();
                    final String postId = contacts.get(selected).getContactId();
                    final String nombre = contacts.get(selected).getNombre();
                    final String phone = contacts.get(selected).getNumero();
                    final Bitmap img = contacts.get(selected).getImg();
                    Log.d("bitmap", img + " imagen ");
                    //    li = new ListasDeUsuarios();

                    @SuppressLint("RestrictedApi") MenuBuilder menuBuilder = new MenuBuilder(context);
                    MenuInflater inflater = new MenuInflater(context);
                    inflater.inflate(R.menu.menu_popou_verdetallescontact, menuBuilder);
                    @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenu = new MenuPopupHelper(context, menuBuilder, view);
                    optionsMenu.setForceShowIcon(true);

                    // Set Item Click Listener
                    menuBuilder.setCallback(new MenuBuilder.Callback() {
                        @Override
                        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_detalles: // Handle option1 Click
                                    Intent inte = new Intent(context, DetalleContact.class);
                                    inte.putExtra("idContact", postId);
                                    inte.putExtra("nombre", nombre);
                                    inte.putExtra("phone", phone);
                                    inte.putExtra("imagenContact", img);
                                    context.startActivity(inte);
                                    return true;

                                default:
                                    return false;
                            }
                        }

                        @Override
                        public void onMenuModeChange(MenuBuilder menu) {
                        }
                    });
                    // Display the menu
                    optionsMenu.show();
                }
            });


        }

       /* @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onClick(v);
        }*/


    }

}
