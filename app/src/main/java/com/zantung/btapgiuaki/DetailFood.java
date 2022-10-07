package com.zantung.btapgiuaki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailFood extends AppCompatActivity {
    ImageView imageViewChiTiet, btnPlus, btnMinus;
    TextView tvNameFoodCT, tvNumberOrder, tvPriceCT;
    private int numberOrder = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_food);

        anhxa();
        Intent intent = getIntent();

        if(intent != null){
            Food food = (Food) intent.getSerializableExtra("data");
            imageViewChiTiet.setImageResource(food.getHinh());
            tvNameFoodCT.setText(food.getTenFood());
            tvPriceCT.setText("$" + food.getGia());

            btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberOrder = numberOrder + 1;
                    tvNumberOrder.setText(String.valueOf(numberOrder));
                    tvPriceCT.setText(String.valueOf("$" + numberOrder * food.getGia()));
                }
            });
            btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (numberOrder > 1){
                        numberOrder = numberOrder - 1;
                    }
                    tvNumberOrder.setText(String.valueOf(numberOrder));
                    tvPriceCT.setText(String.valueOf("$" + numberOrder * food.getGia()));
                }
            });
        }
    }

    private void anhxa() {
        imageViewChiTiet = (ImageView) findViewById(R.id.imageViewDetail);
        tvNameFoodCT = (TextView) findViewById(R.id.tvNameFoodDetail);
        tvPriceCT = (TextView) findViewById(R.id.tvGia);
        tvNumberOrder = (TextView) findViewById(R.id.tvSoLuong);
        btnMinus = (ImageView) findViewById(R.id.imgMinus);
        btnPlus = (ImageView) findViewById(R.id.imgPlus);
    }
}