package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginSucessfully extends AppCompatActivity {

    LoginDataBaseAdapter loginDataBaseAdapter;

    DatabaseHelper myDB;

    ListView listView;
    ListUsersAdapter listUsersAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sucessfully);
        listView = findViewById(R.id.listView_Users);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();

        TextView txtName = (TextView)findViewById(R.id.textViewWelecome);
        Intent intent = getIntent();
        String loginName = intent.getStringExtra("Name");
        txtName.setText("Welcome, " + loginName);
    }

    public void logOutOK (View view){
        Intent intent = new Intent(LoginSucessfully.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void FetchRecords(View view) {
        ArrayList<User> usersDbList = loginDataBaseAdapter.getAllUsers();
        listUsersAdapter = new ListUsersAdapter(this, R.layout.row_users, usersDbList);
        listView.setAdapter(listUsersAdapter);
        listUsersAdapter.notifyDataSetChanged();
    }
}