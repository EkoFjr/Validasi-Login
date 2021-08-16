package com.example.validasilogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    EditText edtUsername,edtPassword,edtEmail,edtNamaLengkap,edtAsalSekolah,edtAlamat;
    Button bnSimpan;

    public static final String FILENAME="login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("Halaman Depan");


        edtUsername = findViewById(R.id.Username);
        edtPassword = findViewById(R.id.editPassword);
        edtEmail =findViewById(R.id.editEmail);
        edtNamaLengkap = findViewById(R.id.editNamaLengkap);
        edtAsalSekolah = findViewById(R.id.editAsalSekolah);


        bnSimpan.setVisibility(View.GONE);
        edtUsername.setEnabled(false);
        edtPassword.setVisibility(View.GONE);
        edtEmail.setEnabled(false);
        edtNamaLengkap.setEnabled(false);
        edtAsalSekolah.setEnabled(false);
        edtAlamat.setEnabled(false);
        
        bacaFileLogin();
        
    }

    void bacaFileLogin() {
        File sdcard = getFilesDir();
        File file = new File(sdcard,FILENAME);
        if (file.exists()){
            StringBuilder text =new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line!=null){
                    text.append(line);
                    line=br.readLine();
                }
                br.close();
            }catch (IOException e){
                System.out.println("Error" +e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");
            bacaDataUser(dataUser[0]);
        }

    }

    void bacaDataUser(String fileName) {
        File sdcard = getFilesDir();
        File file = new File(sdcard, fileName);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line!=null){
                   text.append(line);
                   line = br.readLine();
                }
            br.close();
            } catch (IOException e) {
                System.out.println("Error"+ e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");

            edtUsername.setText(dataUser[0]);
            edtEmail.setText(dataUser[2]);
            edtNamaLengkap.setText(dataUser[3]);
            edtAsalSekolah.setText(dataUser[4]);
            edtAlamat.setText(dataUser[5]);
        }else {
            Toast.makeText(this, "User Tidak Ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home1:
                tampilkanDialogKonfirmasiLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void hapusFile(){
        File file = new File(getFilesDir(),FILENAME);
        if (file.exists()){
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin Logout ?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int whic) {
                        hapusFile();
                        Intent intent = new Intent(MainActivity.this,Login.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton(android.R.string.no,null).show();
    }

}