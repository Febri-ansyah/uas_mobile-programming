package com.sata.izonovel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class BiodataActivity extends AppCompatActivity {
    Spinner spnCountry;
    private String[] optCountry = {"Indonesia","Amerika", "Arab Saudi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        setTitle(R.string.biodata_header);

        spnCountry = findViewById(R.id.spn_country);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,optCountry);
        spnCountry.setAdapter(adapter);
    }
}