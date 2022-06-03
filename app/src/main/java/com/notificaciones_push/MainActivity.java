package com.notificaciones_push;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import Interface.JsonPlaceHolderApi;
import Model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);
        getPosts();
    }

    private void getPosts(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> Call = jsonPlaceHolderApi.getPosts();

        Call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Post>> call, Response<List<Post>> response) {

                if (!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo: "+ response.code());
                    return;
                }

                List<Post> postsList = response.body();

                for(Post post: postsList){
                    String content = "";
                    content += "userId:"+ post.getUserId() + "\n";
                    content += "id:"+ post.getId() + "\n";
                    content += "title:"+ post.getTitle() + "\n";
                    content += "body:"+ post.getBody() + "\n\n";
                    mJsonTxtView.append(content);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Post>> call, Throwable t) {

                mJsonTxtView.setText(t.getMessage());
            }
        });
    }
}