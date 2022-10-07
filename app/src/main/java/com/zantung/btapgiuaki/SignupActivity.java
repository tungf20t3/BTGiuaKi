package com.zantung.btapgiuaki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {
    Button btn_SignUp;
    TextView txtLoginHere;
    EditText edtEmail, edtPassword, edtUserName, edtConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        anhXa();
        txtLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edtUserName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String pass = edtPassword.getText().toString().trim();
                String confirmPass = edtConfirmPassword.getText().toString().trim();

                if (checkUserName(userName) && checkEmail(email) && checkPassword(pass) && checkConfirmPass(confirmPass)){
                    Intent intent = new Intent(SignupActivity.this, SigninActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void anhXa() {
        btn_SignUp = findViewById(R.id.buttonSignUp);
        txtLoginHere = findViewById(R.id.tvLoginHere);
        edtUserName = findViewById(R.id.inputUserName);
        edtEmail = findViewById(R.id.inputEmail);
        edtPassword = findViewById(R.id.inputPassword);
        edtConfirmPassword = findViewById(R.id.inputConfirmPassword);
    }
    private boolean checkUserName(String userName){
        if (!userName.isEmpty()){
            edtUserName.setBackgroundResource(R.drawable.round_border);
            edtUserName.setError(null);
            return true;
        }else {
            edtUserName.setError("error");
            edtUserName.setBackgroundResource(R.drawable.errorbg);
        }
        return false;
    }
    private boolean checkEmail(String email){
        if (!email.isEmpty()){
            edtEmail.setBackgroundResource(R.drawable.round_border);
            edtEmail.setError(null);
            return true;
        }else {
            edtEmail.setError("error");
            edtEmail.setBackgroundResource(R.drawable.errorbg);
        }
        return false;
    }
    private boolean checkPassword(String password){
        if (!password.isEmpty()){
            edtPassword.setBackgroundResource(R.drawable.round_border);
            edtPassword.setError(null);
            return true;
        }else {
            edtPassword.setError("error");
            edtPassword.setBackgroundResource(R.drawable.errorbg);
        }
        return false;
    }
    private boolean checkConfirmPass(String confirmpassword){
        if (!confirmpassword.isEmpty()){
            edtConfirmPassword.setBackgroundResource(R.drawable.round_border);
            edtConfirmPassword.setError(null);
            return true;
        }else {
            edtConfirmPassword.setError("error");
            edtConfirmPassword.setBackgroundResource(R.drawable.errorbg);
        }
        return false;
    }
}