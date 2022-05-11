package com.example.notificaciones_push;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.List;

import Interface.JsonPlaceHolderApi;
import Model.Comments1;
import Model.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Comments extends AppCompatActivity {

    private TextView mJsonTxtView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        mJsonTxtView2 = findViewById(R.id.jsonText2);
        getComments();
    }

    private void getComments(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Comments1>> Call = jsonPlaceHolderApi.getComments();

        Call.enqueue(new Callback<List<Comments1>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Comments1>> call, Response<List<Comments1>> response) {
                if (!response.isSuccessful()){
                    mJsonTxtView2.setText("Codigo: "+ response.code());
                    return;
                }

                List<Comments1> postsList = response.body();

                for(Comments1 post: postsList){
                    String content = "";
                    content += "postif:"+ post.getPostId() + "\n";
                    content += "id:"+ post.getId() + "\n";
                    content += "name:"+ post.getName() + "\n";
                    content += "email:"+ post.getEmail() + "\n";
                    content += "body:"+ post.getBody() + "\n\n";
                    mJsonTxtView2.append(content);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Comments1>> call, Throwable t) {
                mJsonTxtView2.setText(t.getMessage());
            }



        });
    }

}