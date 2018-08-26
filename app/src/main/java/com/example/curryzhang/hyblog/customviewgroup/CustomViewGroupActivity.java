package com.example.curryzhang.hyblog.customviewgroup;

import android.app.Activity;
import android.os.Bundle;

import com.example.curryzhang.hyblog.R;

import java.util.Arrays;
import java.util.List;

public class CustomViewGroupActivity extends Activity {

    private List<String> mDatas = Arrays.asList("Android", "Java");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);

        // setContentView(R.layout.activity_main);
//		setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main,
//				R.id.id_txt, mDatas));

    }

}
