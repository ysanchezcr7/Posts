package com.example.appbase.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.appbase.Negocio.Posts;
import com.example.appbase.R;
import com.example.appbase.VerComentPost;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ListaPostsAdapter extends RecyclerView.Adapter<ListaPostsAdapter.PostViewHolder> {



    public static ArrayList<Posts> posts;
    public static Context context;
    public static Posts post;
    public static ListaPostsAdapter adapter;
    private static View.OnClickListener listener;



    public ArrayList<Posts> getArrayList() {
        return posts;
    }

    public ListaPostsAdapter(ArrayList<Posts> posts, Context context) {

        this.posts = posts;
        this.context = context;
        adapter = this;


    }
    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listar_posts, parent, false);
        return new PostViewHolder(v);

    }


    @Override
    public void onBindViewHolder(PostViewHolder postsViewHolder, int position) {

        post = posts.get(position);
        //String id = String.valueOf(post.getIdPosts());
        //String usserid = String.valueOf(post.getIdUsuario());

      //  postsViewHolder.tvUsserId.setText(usserid);
       // postsViewHolder.tvID.setText(id);
        postsViewHolder.tvTitle.setText(post.getTitle());
        postsViewHolder.tvBody.setText(post.getBody());
        postsViewHolder.setIsRecyclable(false);

    }

    @Override
    public int getItemCount() {
        return posts == null ? 0 : posts.size() ;
    }
    public void setData(ArrayList<Posts> listposts) {

        this.posts = listposts;
        notifyDataSetChanged();
    }


    public class PostViewHolder extends RecyclerView.ViewHolder  implements AdapterView.OnClickListener {

        //private ImageView imgFotos;
        //private TextView tvUsserId;
        //private TextView tvID;
        private TextView tvTitle;
        private TextView tvBody;


        public PostViewHolder(final View itemView) {

            super(itemView);
            //tvUsserId = itemView.findViewById(R.id.tvUsserID);
           // tvID = itemView.findViewById(R.id.tvId);
            tvTitle = itemView.findViewById(R.id.tvtitle);
            tvBody = itemView.findViewById(R.id.tvbody);

            context = itemView.getContext();
            //itemView.setOnClickListener(this);



        itemView.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onClick(View view) {

                    int selected = getLayoutPosition();
                    final int postId= posts.get(selected).getIdPosts();

                    @SuppressLint("RestrictedApi") MenuBuilder menuBuilder = new MenuBuilder(context);
                    MenuInflater inflater = new MenuInflater(context);
                    inflater.inflate(R.menu.menu_popou_vercomentposts, menuBuilder);
                    @SuppressLint("RestrictedApi") MenuPopupHelper optionsMenuComent = new MenuPopupHelper(context, menuBuilder, view);
                    optionsMenuComent.setForceShowIcon(true);

                    // Set Item Click Listener
                    menuBuilder.setCallback(new MenuBuilder.Callback() {
                        @Override
                        public boolean onMenuItemSelected(MenuBuilder menu, MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_verComents: // Handle option1 Click
                                    Intent inte = new Intent(context, VerComentPost.class);
                                    inte.putExtra("idPosts", postId);
                                    context.startActivity(inte);
                                    //overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);

                                    return true;
                                default:
                                    return false;
                            }
                        }


                        @Override
                        public void onMenuModeChange(MenuBuilder menu) {}
                    });
                    // Display the menu
                    optionsMenuComent.show();

                }
            });




        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onClick(v);
        }


    }
}
