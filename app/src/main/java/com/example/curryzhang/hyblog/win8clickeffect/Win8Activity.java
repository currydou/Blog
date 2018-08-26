package com.example.curryzhang.hyblog.win8clickeffect;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.curryzhang.hyblog.R;

public class Win8Activity extends AppCompatActivity {

    private MyImageView1 joke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win8);

        joke = (MyImageView1) findViewById(R.id.c_joke);
        joke.setOnClickIntent(new MyImageView1.OnViewClickListener() {

            @Override
            public void onViewClick(MyImageView1 view) {
                Toast.makeText(Win8Activity.this, "Joke", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
