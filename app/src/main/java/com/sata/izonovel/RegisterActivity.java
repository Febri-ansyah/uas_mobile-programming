package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sata.izonovel.Model.InsertResponseModel;
import com.sata.izonovel.Model.RegisterRequestModel;
import com.sata.izonovel.Retrofit.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText etFirstname, etLastName, etPassword, etEmail;
    Button btnRegister, btnCancelRegister;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstname = findViewById(R.id.et_firs_name);
        etLastName = findViewById(R.id.et_last_name);
        etPassword = findViewById(R.id.et_password);
        etEmail = findViewById(R.id.et_email);
        btnRegister = findViewById(R.id.btn_register_submit);
        btnCancelRegister = findViewById(R.id.btn_cancel_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitRegister();
            }
        });
    }

    private  void  onSubmitRegister(){
        RegisterRequestModel registerRequestModel = new RegisterRequestModel();
        registerRequestModel.setDataSource("Cluster0");
        registerRequestModel.setDatabase("izonovel");
        registerRequestModel.setCollection("users");

        RegisterRequestModel.Document document = new RegisterRequestModel.Document();
        String FullName = etFirstname.getText().toString() +" "+ etLastName.getText().toString();
        document.setFullName(FullName);
        document.setUsername(etEmail.getText().toString());
        document.setPassword(etPassword.getText().toString());

        registerRequestModel.setDocument(document);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Info");
        progressDialog.setMessage("Submiting Data....");
        progressDialog.show();


        APIService.endpoint().registerUser(registerRequestModel).enqueue(new Callback<InsertResponseModel>() {
            @Override
            public void onResponse(Call<InsertResponseModel> call, Response<InsertResponseModel> response) {
                progressDialog.dismiss();
                String responseId = response.body().getInsertedId().toString();

                String pesan = "Data berhasil disimpan dengan ID "+responseId+"\n \n Klik Ok unutk melanjutkan";
                AlertDialog.Builder builder =  new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Infro");
                builder.setMessage(pesan)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                clearForm();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);

                            }
                        });
                builder.show();
            }

            @Override
            public void onFailure(Call<InsertResponseModel> call, Throwable t) {
                progressDialog.dismiss();

                Log.d("ERR registerUser", t.toString());
                Toast.makeText(RegisterActivity.this, t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void clearForm(){
        etFirstname.setText("");
        etLastName.setText("");
        etEmail.setText("");
        etPassword.setText("");
    }

}