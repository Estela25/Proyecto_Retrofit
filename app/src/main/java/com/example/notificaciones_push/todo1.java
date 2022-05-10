package com.example.notificaciones_push;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import Interface.JsonPlaceHolderApi;
import Model.Album;
import Model.Todo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class todo1 extends AppCompatActivity {

    private TextView mJsonTxtView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo1);

        mJsonTxtView4 = findViewById(R.id.jsonText4);
        getTodos();
    }

    private void getTodos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Todo>> Call = jsonPlaceHolderApi.getTodos();

        Call.enqueue(new Callback<List<Todo>>() {

            @Override
            public void onResponse(retrofit2.Call<List<Todo>> call, Response<List<Todo>> response) {
                if (!response.isSuccessful()){
                    mJsonTxtView4.setText("Codigo: "+ response.code());
                    return;
                }

                List<Todo> postsList = response.body();

                for(Todo post: postsList){
                    String content = "";
                    content += "useerId:"+ post.getUserId() + "\n";
                    content += "id:"+ post.getId() + "\n";
                    content += "title:"+ post.getTitle() + "\n";
                    content += "complemented:"+ post.isCompleted() + "\n\n";
                    mJsonTxtView4.append(content);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Todo>> call, Throwable t) {
                mJsonTxtView4.setText(t.getMessage());
            }
        });
    }
}