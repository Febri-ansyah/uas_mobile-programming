package com.sata.izonovel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.sata.izonovel.Model.InsertNovelModel;
import com.sata.izonovel.Model.InsertResponseModel;
import com.sata.izonovel.Retrofit.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormInptActivity extends AppCompatActivity {
    String[] genre = {"Action", "Romance", "Fantasi", "Horor", "Comedy", "Sci-fi"};
    TextInputEditText Judul, Pengarang, Penerbit, Tahunterbit, Sinopsis, ImgUrl;
    AutoCompleteTextView Genre;
    Button simpanNovel;

    ProgressDialog progressDialog;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_inpt);
        setTitle("Add New Novel");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, genre);
        AutoCompleteTextView actv = (AutoCompleteTextView) findViewById(R.id.act_genre);
        actv.setThreshold(1);
        actv.setAdapter(adapter);


        Judul= findViewById(R.id.met_judul);
        Pengarang= findViewById(R.id.met_pengarang);
        Penerbit= findViewById(R.id.met_penerbit);
        Tahunterbit= findViewById(R.id.met_tahun_terbit);
        Genre= findViewById(R.id.act_genre);
        Sinopsis= findViewById(R.id.met_sinopsis);
        simpanNovel= findViewById(R.id.save_novel);
        ImgUrl = findViewById(R.id.met_img_url);

        simpanNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onsavenovel();
            }
        });



    }
    private void onsavenovel() {
        InsertNovelModel insertNovelModel=new InsertNovelModel();
        insertNovelModel.setCollection("novel");
        insertNovelModel.setDataSource("Cluster0");
        insertNovelModel.setDatabase("izonovel");
        InsertNovelModel.Document document=new InsertNovelModel.Document();
        document.setJudul(Judul.getText().toString());
        document.setPengarang(Pengarang.getText().toString());
        document.setPenerbit(Penerbit.getText().toString());
        document.setTahunTerbit(Tahunterbit.getText().toString());
        document.setGenre(Genre.getText().toString());
        document.setSinopsis(Sinopsis.getText().toString());
        document.setGambar(ImgUrl.getText().toString());
        insertNovelModel.setDocument(document);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("info");
        progressDialog.setMessage("Sedang Mengirim...");
        progressDialog.show();

        APIService.endpoint().insertNovel(insertNovelModel).enqueue(new Callback<InsertResponseModel>() {
            @Override
            public void onResponse(Call<InsertResponseModel> call, Response<InsertResponseModel> response) {
                progressDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(FormInptActivity.this);
                builder.setTitle("Info");
                builder.setMessage("Data berhasil disimpan");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Judul.setText("");
                        Pengarang.setText("");
                        Penerbit.setText("");
                        Tahunterbit.setText("");
                        Genre.setText("");
                        Sinopsis.setText("");
                        ImgUrl.setText("");
                    }
                });

                builder.show();
            }

            @Override
            public void onFailure(Call<InsertResponseModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}