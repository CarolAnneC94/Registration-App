package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class signUp extends AppCompatActivity {
    LoginDataBaseAdapter loginDataBaseAdapter;

    private RadioGroup radioGroup;
    String Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
    }

    public void getSelectedRadioButton(View view){
        final RadioGroup radGroup = (RadioGroup) findViewById(R.id.radioGroup_Sex);

        int radioID = radGroup.getCheckedRadioButtonId();

        RadioButton singleButton = (RadioButton) findViewById(radioID);
    }

    public void SignUP_OK (View view){
        String username = ((EditText)findViewById(R.id.editTextUsernameReg)).getText().toString();
        String email = ((EditText)findViewById(R.id.editTextEmail)).getText().toString();
        String password = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();
        String confirmPassword = ((EditText)findViewById(R.id.editTextPasswordConfirm)).getText().toString();

        if(!password.equals(confirmPassword)){
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_LONG).show();
        } else {
            loginDataBaseAdapter.insertEntry(username, email, password, Gender);
            Toast.makeText(getApplicationContext(), "Your account has been created successfully.You can sign in now. ", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(signUp.this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        loginDataBaseAdapter.close();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        Gender = ((RadioButton) view).getText().toString();
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButton_Female:
                    if (checked)
                    //Female Selcted
                    break;
            case R.id.radioButton_Male:
                    if (checked)
                        //Male Selcted
                    break;
        }
    }

}