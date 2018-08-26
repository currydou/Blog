package com.example.curryzhang.hyblog.verticalViewGroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.curryzhang.hyblog.R;

public class VerticalViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_view_group);

        VerticalLinearLayout layout = (VerticalLinearLayout) findViewById(R.id.activity_custom_view_group);
        layout.setOnPageChangeListener(new VerticalLinearLayout.OnPageChangeListener() {
            @Override
            public void onPageChange(int currentPage) {
                Toast.makeText(VerticalViewGroupActivity.this, " "+currentPage, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
