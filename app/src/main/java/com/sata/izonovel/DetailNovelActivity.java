package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DetailNovelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_novel);

        //digunakan untuk mengambil data kiriman dari activity intent, sebelum nya
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String judul = intent.getStringExtra("judul");
        setTitle("Detail Novel - "+judul);

        Log.d("INFO-id",id );
        Log.d("INFO-judul",judul );

    }
}