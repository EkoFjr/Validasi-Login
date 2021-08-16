package com.example.validasilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Objects;


public class RegisterActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword, edtEmail, edtNamaLengkap, edtAsalSekolah, edtAlamat;
    Button bnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Register");
        edtUsername = findViewById(R.id.Username);
        edtPassword = findViewById(R.id.editPassword);
        edtEmail =findViewById(R.id.editEmail);
        edtNamaLengkap = findViewById(R.id.editNamaLengkap);
        edtAsalSekolah = findViewById(R.id.editAsalSekolah);
        bnSimpan = findViewById(R.id.btnSimpan);

        bnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidation()) {
                    simpanFileData();
                }else {
                    Toast.makeText(RegisterActivity.this, "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isValidation() {
        if(edtUsername.getText().toString().equals("") ||
                edtPassword.getText().toString().equals("") ||
                edtEmail.getText().toString().equals("") ||
                edtNamaLengkap.getText().toString().equals("") ||
                edtAsalSekolah.getText().toString().equals("") ||
                edtAlamat.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    void simpanFileData() {
        String isiFile = edtUsername.getText().toString() +";"+
                edtPassword.getText().toString() +";"+
                edtEmail.getText().toString() +";"+
                edtNamaLengkap.getText().toString() +";"+
                edtAsalSekolah.getText().toString() +";"+
                edtAlamat.getText().toString();
        File file = new File(getFilesDir(), edtUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

}