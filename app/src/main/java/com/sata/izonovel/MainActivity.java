package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sata.izonovel.Database.DatabaseClient;
import com.sata.izonovel.Database.model.Session;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView menuFavorite, menuInfoPengguna, menuDaftarNovel, menuInputNovel, userName;

    Button btnSignOut, btnSignIn;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.tv_user);

        menuInfoPengguna = findViewById(R.id.informasi_pengguna);
        menuInfoPengguna.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(MainActivity.this, BiodataActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuFavorite = findViewById(R.id.favorite_more);
        menuFavorite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, FavoritActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuDaftarNovel = findViewById(R.id.daftar_novel);
        menuDaftarNovel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, DaftarNovelActivity.class);
                startActivity(intent);
                return false;
            }
        });

        menuInputNovel = findViewById(R.id.tvInputNovel);
        menuInputNovel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(MainActivity.this, FormInptActivity.class);
                startActivity(intent);
                return false;
            }
        });

        getSessions();

        btnSignOut = findViewById(R.id.btn_sign_out);
        btnSignIn = findViewById(R.id.btn_signin);

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseClient.getInstance(MainActivity.this).getAppDatabase()
                                .dataBaseAction().clearSessionList();

                    }
                });

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getSessions() {
        class GetSessions extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                session = DatabaseClient.getInstance(MainActivity.this).getAppDatabase()
                        .dataBaseAction().getSessions();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                if(session != null){
                    btnSignOut.setVisibility(View.VISIBLE);
                    userName.setVisibility(View.VISIBLE);
                    btnSignIn.setVisibility(View.INVISIBLE);

                    userName.setText(session.getFullName());
                }else{
                    userName.setVisibility(View.INVISIBLE);
                    btnSignOut.setVisibility(View.INVISIBLE);

                    btnSignIn.setVisibility(View.VISIBLE);
                }

            }
        }

        GetSessions getSessions = new GetSessions();
        getSessions.execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // getSessions();
    }
}