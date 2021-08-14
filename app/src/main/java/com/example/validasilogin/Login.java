package com.example.validasilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Login extends AppCompatActivity {
    public static final  String FILENAME ="login";

    EditText edtUsername, edtPassword;
    Button bnLogin, bnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.editUsername);
        edtPassword = findViewById(R.id.editPassword);
        bnLogin = findViewById(R.id.action_login);
        bnRegister = findViewById(R.id.action_Register);

        bnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        bnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    void  simpanFileLogin(){
        String isiFile = edtUsername.getText().toString()
                +";"+edtPassword.getText().toString();
        File file = new File(getFilesDir(),FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream= new FileOutputStream(file,false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    private void login() {
        File sdcard = getFilesDir();
        File file = new File(sdcard,edtUsername.getText().toString());
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line!=null){
                    text.append(line);
                    line =br.readLine();
                }
                br.close();
            }catch (IOException e){
                System.out.println("Error" + e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");

            if (dataUser[1].equals(edtPassword.getText().toString())){

                simpanFileLogin();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "User Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }
    }


}