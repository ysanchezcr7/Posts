package com.example.appbase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.appbase.Adapters.ListaPostsAdapter;
import com.example.appbase.Adapters.ListaPostsComentsAdapter;
import com.example.appbase.Api.RetofitClient;
import com.example.appbase.Negocio.Posts;
import com.example.appbase.Negocio.PostsComent;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerComentPost extends AppCompatActivity{


    private ListaPostsComentsAdapter adaptador;
    private Context context;
    ArrayList<PostsComent> posts;
    RecyclerView listaComentssPost;
    SwipeRefreshLayout refreshLayout;
    int idPost ;
    Toolbar toolbar;
    int onStartCount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_coment_post);
        onStartCount = 1;
        if (savedInstanceState == null) // 1st time
        {
            this.overridePendingTransition(R.anim.push_left_in,
                    R.anim.push_left_out);
        } else // already created so reverse animation
        {
            onStartCount = 2;
        }
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.back);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Intent inte = new Intent(VerComentPost.this, Navegacion.class);
                   // startActivity(inte);
                   // overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    onBackPressed();
                }
            });
        }


        listaComentssPost = (RecyclerView) findViewById(R.id.rvPostsComent);

        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaComentssPost.setLayoutManager(llm);

        Intent a = getIntent();
        idPost = a.getIntExtra("idPosts",0);

        lanzarPeticion(idPost);
        inicializarAdaptador();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void inicializarAdaptador() {

        adaptador = new ListaPostsComentsAdapter(posts, this );
        listaComentssPost.setAdapter(adaptador);

    }
    private void lanzarPeticion(int idPosts) {
        posts = new ArrayList<>();

        Call<ArrayList<PostsComent>> call = RetofitClient
                .getInstance()
                .getApi()
                .getPostsComents(idPosts);
        call.enqueue(new Callback<ArrayList<PostsComent>>() {
            @Override
            public void onResponse(Call<ArrayList<PostsComent>> call, Response<ArrayList<PostsComent>> response) {
                //  Log.d("Respuesta", response.body() + "respuesta");
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error"+ response.message() , Toast.LENGTH_LONG).show();
                }

                Toast.makeText(getApplicationContext(),"Succesful Data " , Toast.LENGTH_LONG).show();
                adaptador.setData(response.body());
                // progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ArrayList<PostsComent>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Ocurrio un error en la red : " + t , Toast.LENGTH_SHORT).show();
                // Log.d("error", t + "sin respuesta");
            }
        });

    }
}
