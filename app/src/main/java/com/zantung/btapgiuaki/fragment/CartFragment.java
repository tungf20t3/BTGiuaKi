package com.zantung.btapgiuaki.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.zantung.btapgiuaki.DetailFood;
import com.zantung.btapgiuaki.Food;
import com.zantung.btapgiuaki.FoodAdapter;
import com.zantung.btapgiuaki.FoodCartAdapter;
import com.zantung.btapgiuaki.R;
import com.zantung.btapgiuaki.TruyenFood;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CartFragment extends ListFragment {
    ImageView btnPlus, btnMinus, btnDelete;
    TextView  tvNumberOrder, tvGiaGH;
    private int numberOrder = 1;
    ArrayList<Food> arrayFood;
    FoodCartAdapter adapter;
    TruyenFood truyenFood;
    SharedPreferences sharedPreferences;
    String id_sp;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        truyenFood = (TruyenFood) getActivity();
        arrayFood = new ArrayList<>();
        adapter = new FoodCartAdapter(getActivity(), R.layout.row_cart, arrayFood);
        setListAdapter(adapter);
        getData();
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);
//        Food food = new Food();
//        tvGiaGH = view.findViewById(R.id.tvPriceCart);
//        tvNumberOrder = view.findViewById(R.id.tvSoLuong);
//        btnMinus = view.findViewById(R.id.imgMinus);
//        btnPlus = view.findViewById(R.id.imgPlus);
//        btnDelete = view.findViewById(R.id.btn_delete);
//
//        btnPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                numberOrder = numberOrder + 1;
//                tvNumberOrder.setText(String.valueOf(numberOrder));
//                tvGiaGH.setText(String.valueOf("$" + numberOrder * food.getGia()));
//            }
//        });
//        btnMinus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (numberOrder > 1){
//                    numberOrder = numberOrder - 1;
//                }
//                tvNumberOrder.setText(String.valueOf(numberOrder));
//                tvGiaGH.setText(String.valueOf("$" + numberOrder * food.getGia()));
//            }
//        });
        return view;
    }

    private void getData() {
        sharedPreferences = getActivity().getSharedPreferences("dataLogin", getActivity().MODE_PRIVATE);
        int id_user = sharedPreferences.getInt("id", 0);
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id_user", id_user);
        String url = "https://zantung.000webhostapp.com/food/getCart.php";
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    try {
                        if (response.getBoolean("trang_thai")){
                            JSONArray data = response.getJSONArray("data");
                            for (int i = 0; i < data.length(); i++){
                                JSONObject object = data.getJSONObject(i);
                                Food food = new Food();
                                food.setId(object.getString("id_food"));
                                food.setTenFood(object.getString("name"));
                                food.setHinh(object.getString("image_food"));
                                food.setGia(object.getDouble("price"));
                                arrayFood.add(food);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void deleteSP() {
//        sharedPreferences = getActivity().getSharedPreferences("dataLogin", getActivity().MODE_PRIVATE);
//        int id_user = sharedPreferences.getInt("id", 0);
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        params.put("id_user", id_user);
//        params.put("id_sp", id_sp);
//        String url = "https://zantung.000webhostapp.com/food/deleteSP.php";
//        client.get(url, params, new JsonHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
//                try {
//                    if (response.getString("trang_thai").equals("true")){
//                        Toast.makeText(getActivity(), "Đã xóa khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(getActivity(), "Xóa thất bại!", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                adapter.notifyDataSetChanged();
//            }
//        });
//    }
}
