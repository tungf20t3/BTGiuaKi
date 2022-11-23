package com.zantung.btapgiuaki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zantung.btapgiuaki.fragment.CartFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailFood extends AppCompatActivity {
    ImageView imageViewChiTiet;
    TextView tvNameFoodCT;
    Button btn_addCart;
    SharedPreferences sharedPreferences;
    String link_host = "https://zantung.000webhostapp.com/food/";
    String id_sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        anhxa();

        Intent intent = getIntent();
        if(intent != null){
            Food food = (Food) intent.getSerializableExtra("data");
            id_sp = food.getId();
            tvNameFoodCT.setText(food.getTenFood());
            Glide
                    .with(this)
                    .load("https://zantung.000webhostapp.com/" + food.getHinh())
                    .fitCenter()
                    .into(imageViewChiTiet);
        }
        btn_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addSPCart();
            }
        });
    }
    private void anhxa() {
        imageViewChiTiet = (ImageView) findViewById(R.id.imageViewDetail);
        tvNameFoodCT = (TextView) findViewById(R.id.tvNameFoodDetail);
        btn_addCart = (Button) findViewById(R.id.buttonaddcart);
    }
    private void addSPCart(){
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        int id_user = sharedPreferences.getInt("id", 0);

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id_user", id_user);
        params.put("id_sp", id_sp);
        params.put("so_luong", 1);
        client.post(link_host+"addCart.php", params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.getString("trang_thai").equals("true")){
                        Toast.makeText(DetailFood.this, "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DetailFood.this, "Đã có sản phẩm này trong giỏ hàng!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(DetailFood.this, CartFragment.class);
            startActivity(intent);
        }
        return false;
    }
}