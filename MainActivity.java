package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

    }

    public void signIn(View view){
        try{
            String username = ((EditText)findViewById(R.id.editTextUserName)).getText().toString();
            String password = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();

            String storedPassword = loginDataBaseAdapter.getSingleEntry(username);

            if(password.equals(storedPassword)){
                Toast.makeText(MainActivity.this, "Successfully Logged In", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, LoginSucessfully.class);
                intent.putExtra("Name", username);
                startActivity(intent);
            }else {
                Toast.makeText(MainActivity.this, "The given record is not available in the database, please sign up", Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Log.e("Error", "Error Login");
        }
    }

    public void AddData(String newEntry){
        boolean insertData = myDB.addData(newEntry);

        if(insertData == true){
            Toast.makeText(MainActivity.this, "Successfully logged in!",Toast.LENGTH_LONG).show();
        }if(insertData == false){
            Toast.makeText(MainActivity.this, "You have to complete all fields!", Toast.LENGTH_LONG).show();
        }
    }

    public void GoToSignUP(View view){
        Intent intent = new Intent(MainActivity.this, signUp.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        loginDataBaseAdapter.close();
    }
}