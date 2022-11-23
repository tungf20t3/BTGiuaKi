package com.zantung.btapgiuaki.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.zantung.btapgiuaki.Food;
import com.zantung.btapgiuaki.FoodAdapter;
import com.zantung.btapgiuaki.HomeActivity;
import com.zantung.btapgiuaki.R;
import com.zantung.btapgiuaki.TruyenFood;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends ListFragment {
    ArrayList<Food> arrayFood;
    FoodAdapter adapter;
    TruyenFood truyenFood;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        truyenFood = (TruyenFood) getActivity();
        arrayFood = new ArrayList<>();
        adapter = new FoodAdapter(getActivity(), R.layout.row_food, arrayFood);
        setListAdapter(adapter);
        getData();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        truyenFood.DataFood(arrayFood.get(position));
    }


    private void getData(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://zantung.000webhostapp.com/food/getAll.php ";
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                for (int i=0; i<response.length(); i++){
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Food food = new Food();
                        food.setId(object.getString("id_food"));
                        food.setTenFood(object.getString("name"));
                        food.setHinh(object.getString("image_food"));
                        food.setGia(object.getDouble("price"));
                        arrayFood.add(food);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
