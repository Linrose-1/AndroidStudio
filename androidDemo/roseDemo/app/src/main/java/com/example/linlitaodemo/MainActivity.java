package com.example.linlitaodemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this,MainActivity2.class);

                intent.putExtra("username","肖鸡婉玉");
                intent.putExtra("password","123456");

//                startActivity(intent);

                startActivityForResult(intent,1);

            }
        });
    }


    protected void onActivityResult(int requestCode, int resrequestCode, @Nullable Intent data){
        super.onActivityResult(requestCode,requestCode,data);
        if (requestCode==1&&requestCode==2){
            String s = data.getStringExtra("data");
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        }
    }
}