package com.example.appbase.Adapters;

import android.content.Context;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appbase.Negocio.PostsComent;
import com.example.appbase.R;
import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class ListaPostsComentsAdapter extends RecyclerView.Adapter<ListaPostsComentsAdapter.PostComentsViewHolder> {



    public static ArrayList<PostsComent> postsComents;
    public static Context context;
    public static PostsComent post;
    public static ListaPostsComentsAdapter adapter;


    public ListaPostsComentsAdapter(ArrayList<PostsComent> postsComents, Context context) {

        this.postsComents = postsComents;
        this.context = context;
        adapter = this;


    }
    @Override
    public PostComentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listar_posts_coment_string, parent, false);
        return new PostComentsViewHolder(v);

    }


    @Override
    public void onBindViewHolder(PostComentsViewHolder postsViewHolder, int position) {

        post = postsComents.get(position);
        postsViewHolder.tvName.setText(post.getName());
        postsViewHolder.tvEmail.setText(post.getemail());
        postsViewHolder.tvBody.setText(post.getBody());
        postsViewHolder.setIsRecyclable(false);

    }

    @Override
    public int getItemCount() {
        return postsComents.size();
    }
    public void setData(ArrayList<PostsComent> listposts) {

        this.postsComents = listposts;
        notifyDataSetChanged();
    }


    public class PostComentsViewHolder extends RecyclerView.ViewHolder {

        //private ImageView imgFotos;
        //private TextView tvUsserId;
        //private TextView tvID;
        private TextView tvName;
        private TextView tvBody;
        private TextView tvEmail;


        public PostComentsViewHolder(final View itemView) {

            super(itemView);
            //tvUsserId = itemView.findViewById(R.id.tvUsserID);
            tvEmail = itemView.findViewById(R.id.tvemail);
            tvName = itemView.findViewById(R.id.tvname);
            tvBody = itemView.findViewById(R.id.tvbodycoment);

            context = itemView.getContext();
            //itemView.setOnClickListener(this);




        }





    }
}
