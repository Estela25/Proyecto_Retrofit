package com.notificaciones_push;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_p);

        Button cerrar = (Button)findViewById(R.id.btnSalir);
        cerrar.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          finish();}
                                  });
    }
    public void post(View view) {
        Intent post = new Intent(this, MainActivity.class);
        startActivity(post);
    }

    public void coments(View view) {
        Intent coments = new Intent(this, Comments.class);
        startActivity(coments);
    }


    public void album(View view) {
        Intent album = new Intent(this, albums1.class);
        startActivity(album);
    }

    public void todo(View view) {
        Intent todo= new Intent(this, todo1.class);
        startActivity(todo);
    }

}
