package com.example.appbase.Api;
import com.example.appbase.Utiles.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetofitClient  {

    private static RetofitClient mInstance;
    private Retrofit retofit;

    private HttpLoggingInterceptor httpLoggingInterceptor;
    private OkHttpClient.Builder httClirntBuilder;
    //Context context ;
    //Reservado reservado  ;

    private  RetofitClient(){

        httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httClirntBuilder=new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor);
        //String server = reservado.getServer();
       // Log.d("el server es:", Constant.server);
        retofit= new Retrofit.Builder()
                .baseUrl(Constant.server)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httClirntBuilder.build())
                .build();
    }

    public  static synchronized RetofitClient getInstance(){
        if(mInstance== null){
            mInstance = new RetofitClient();
        }
        return mInstance;
    }

    public InterfasRetrofit getApi(){
        return retofit.create(InterfasRetrofit.class);
    }
}
