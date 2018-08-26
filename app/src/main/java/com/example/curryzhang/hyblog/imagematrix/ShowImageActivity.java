package com.example.curryzhang.hyblog.imagematrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.curryzhang.hyblog.R;

public class ShowImageActivity extends AppCompatActivity {


    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);


        mImageView = (ImageView) findViewById(R.id.id_showImage);
        byte[] b = getIntent().getByteArrayExtra("bitmap");
        Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
        if (bitmap != null) {
            mImageView.setImageBitmap(bitmap);
        }
    }
}
