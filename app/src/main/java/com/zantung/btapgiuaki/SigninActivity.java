package com.zantung.btapgiuaki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class SigninActivity extends AppCompatActivity {
    Button btn_Signin;
    TextView txtCreateAcc;
    EditText edtEmail, edtPassword;
    CheckBox cbSavePass;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    User user = new User();
    String link_host = "https://zantung.000webhostapp.com/food/";
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
                    dangNhap();
                }
            }
        });
    }

    private boolean checkEmail(String email) {
        if (!email.isEmpty()){
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
        if (!pass.isEmpty()) {
            edtPassword.setBackgroundResource(R.drawable.round_border);
            edtPassword.setError(null);
            return true;
        } else {
            edtPassword.setError("error");
            edtPassword.setBackgroundResource(R.drawable.errorbg);
        }
        return false;
    }

    private void dangNhap(){
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("username", email);
        params.put("password", password);
        progressDialog.show();
        client.post(link_host+"login.php", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getString("trang_thai").equals("success")){
                        progressDialog.dismiss();
                        if (cbSavePass.isChecked()){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("taikhoan", email);
                            editor.putString("matkhau", password);
                            editor.putBoolean("checked", true);
                            editor.putInt("id", response.getInt("id"));
                            editor.commit();
                        }else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("taikhoan");
                            editor.remove("matkhau");
                            editor.remove("checked");
                            editor.remove("id");
                            editor.commit();
                        }
                        Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(SigninActivity.this, "Dang nhap that bai", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void anhXa() {
        btn_Signin = findViewById(R.id.btn_login);
        txtCreateAcc = findViewById(R.id.tvCreateAccount);
        edtEmail = findViewById(R.id.inputEmail);
        edtPassword = findViewById(R.id.inputPassword);
        cbSavePass = findViewById(R.id.cbRemember);
        progressDialog = new ProgressDialog(this);
    }
}