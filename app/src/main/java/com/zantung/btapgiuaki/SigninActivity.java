package com.zantung.btapgiuaki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class SigninActivity extends AppCompatActivity {
    Button btn_Signin;
    TextView txtCreateAcc;
    EditText edtEmail, edtPassword;
    CheckBox cbSavePass;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        anhXa();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        //lay gia tri
        edtEmail.setText(sharedPreferences.getString("taikhoan", ""));
        edtPassword.setText(sharedPreferences.getString("matkhau", ""));
        cbSavePass.setChecked(sharedPreferences.getBoolean("checked", false));

        txtCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        btn_Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (checkEmail(email) & checkPasswword(password)) {
                    Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                    startActivity(intent);
                    if (cbSavePass.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan", email);
                        editor.putString("matkhau", password);
                        editor.putBoolean("checked", true);
                        editor.commit();
                    }else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }
                }
            }
        });
    }

    private boolean checkEmail(String email) {
        if (!email.isEmpty() && email.equals("vantung.260902@gmail.com")) {
            edtEmail.setBackgroundResource(R.drawable.round_border);
            edtEmail.setError(null);
            return true;
        } else {
            edtEmail.setError("error");
            edtEmail.setBackgroundResource(R.drawable.errorbg);
        }
        return false;
    }

    private boolean checkPasswword(String pass) {
        if (!pass.isEmpty() && pass.equals("123")) {
            edtPassword.setBackgroundResource(R.drawable.round_border);
            edtPassword.setError(null);
            return true;
        } else {
            edtPassword.setError("error");
            edtPassword.setBackgroundResource(R.drawable.errorbg);
        }
        return false;
    }

    private void anhXa() {
        btn_Signin = findViewById(R.id.btn_login);
        txtCreateAcc = findViewById(R.id.tvCreateAccount);
        edtEmail = findViewById(R.id.inputEmail);
        edtPassword = findViewById(R.id.inputPassword);
        cbSavePass = findViewById(R.id.cbRemember);
    }
}