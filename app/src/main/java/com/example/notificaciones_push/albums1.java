package com.example.notificaciones_push;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import Interface.JsonPlaceHolderApi;
import Model.Album;
import Model.Comments1;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class albums1 extends AppCompatActivity {

    private TextView mJsonTxtView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums1);

        mJsonTxtView3 = findViewById(R.id.jsonText3);
        getAlbums();
    }

    private void getAlbums(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Album>> Call = jsonPlaceHolderApi.getAlbums();

        Call.enqueue(new Callback<List<Album>>() {

            @Override
            public void onResponse(retrofit2.Call<List<Album>> call, Response<List<Album>> response) {
                if (!response.isSuccessful()){
                    mJsonTxtView3.setText("Codigo: "+ response.code());
                    return;
                }

                List<Album> postsList = response.body();

                for(Album post: postsList){
                    String content = "";
                    content += "postif:"+ post.getUserId() + "\n";
                    content += "id:"+ post.getId() + "\n";
                    content += "body:"+ post.getTitle() + "\n\n";
                    mJsonTxtView3.append(content);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Album>> call, Throwable t) {
                mJsonTxtView3.setText(t.getMessage());
            }
        });
    }
}