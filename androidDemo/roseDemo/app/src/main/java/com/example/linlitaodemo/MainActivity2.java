package com.example.linlitaodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String username=intent.getStringExtra("username");
        String password=intent.getStringExtra("password");

        Toast.makeText(MainActivity2.this, username, Toast.LENGTH_SHORT).show();

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.equals("小米")&&password.equals("123456")){
                    Intent intent2 = new Intent();
                    intent2.putExtra("data","登录成功");
                    setResult(2,intent2);

                    finish();
                }else {
                    Intent intent2 = new Intent();
                    intent2.putExtra("data","失败");

                    setResult(2,intent2);
                    finish();
                }
            }
        });
    }
}