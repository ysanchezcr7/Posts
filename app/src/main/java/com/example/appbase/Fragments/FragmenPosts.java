package com.example.appbase.Fragments;

import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appbase.Adapters.ListaPostsAdapter;
import com.example.appbase.Api.RetofitClient;
import com.example.appbase.Negocio.Posts;
import com.example.appbase.R;
import com.example.appbase.Utiles.RecyclerItemClickListener;
import com.example.appbase.VerComentPost;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FragmenPosts extends Fragment {

    private ListaPostsAdapter adaptador;
    private Context context;
    ArrayList<Posts> posts;
    RecyclerView listaPost;
    SwipeRefreshLayout refreshLayout;
    ProgressDialog progressDialog ;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        context = v.getContext();
        findView(v);


        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaPost.setLayoutManager(llm);

        lanzarPeticion();
        inicializarAdaptador();


        // Obtener el refreshLayout
        // Iniciar la tarea asíncrona al revelar el indicador
        refreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        new HackingBackgroundTask().execute();
                    }
                }
        );
        return v;

    }

    public void findView(View v) {
        listaPost = v.findViewById(R.id.rvPosts);
        refreshLayout = v.findViewById(R.id.refresh);


    }


    private class HackingBackgroundTask extends AsyncTask<Void, Void, List<Posts>> {

        static final int DURACION = 1 * 1000; // 3 segundos de carga

        @Override
        protected List<Posts> doInBackground(Void... params) {
            // Simulación de la carga de items
            try {
                Thread.sleep(DURACION);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Retornar en nuevos elementos para el adaptador
            //return ConjuntoListas.randomList(CANTIDAD_ITEMS);
            return null;
        }

        @Override
        protected void onPostExecute(List result) {
            super.onPostExecute(result);

            // Limpiar elementos antiguos
            //adapter.clear();

            // Añadir elementos nuevos
            //adapter.addAll(result);
             lanzarPeticion();
            // Parar la animación del indicador
            refreshLayout.setRefreshing(false);
        }

    }
    private void inicializarAdaptador() {

        adaptador = new ListaPostsAdapter(posts, getContext() );
        listaPost.setAdapter(adaptador);
       // adaptador.notifyDataSetChanged();

    }

    private void lanzarPeticion() {
        progressDialog = ProgressDialog.show(getContext(), "",
                "Por favor, espere cargando Posts...", true);
        posts = new ArrayList<>();
       //final android.app.AlertDialog progressDialog = new SpotsDialog(this, R.style.Custom1);
      //  progressDialog.show();
       // final AlertDialog progressDialog  = new SpotsDialog.Builder().setContext(context).build()

        Call<ArrayList<Posts>> call =RetofitClient
                .getInstance()
                .getApi()
                .getPosts();
        call.enqueue(new Callback<ArrayList<Posts>>() {
            @Override
            public void onResponse(Call<ArrayList<Posts>> call, Response<ArrayList<Posts>> response) {
              //  Log.d("Respuesta", response.body() + "respuesta");
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(),"Error"+ response.message() , Toast.LENGTH_LONG).show();
                }

               Toast.makeText(getContext(),"Succesful Data " , Toast.LENGTH_LONG).show();
               adaptador.setData(response.body());
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;

                }
              // progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<ArrayList<Posts>> call, Throwable t) {
                Toast.makeText(getContext(),"Ocurrio un error en la red : " + t , Toast.LENGTH_SHORT).show();
               // Log.d("error", t + "sin respuesta");
                if (progressDialog != null) {
                    progressDialog.dismiss();
                    progressDialog = null;

                }
            }
        });

    }

}