package com.zantung.btapgiuaki.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import com.zantung.btapgiuaki.Food;
import com.zantung.btapgiuaki.FoodAdapter;
import com.zantung.btapgiuaki.HomeActivity;
import com.zantung.btapgiuaki.R;
import com.zantung.btapgiuaki.TruyenFood;

import java.util.ArrayList;

public class HomeFragment extends ListFragment {
    ArrayList<Food> arrayFood;
    FoodAdapter adapter;
    TruyenFood truyenFood;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        truyenFood = (TruyenFood) getActivity();
        anhxa();
        adapter = new FoodAdapter(getActivity(), R.layout.row_food, arrayFood);
        setListAdapter(adapter);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        truyenFood.DataFood(arrayFood.get(position));
    }

    private void anhxa(){
        arrayFood = new ArrayList<>();
        arrayFood.add(new Food("Bánh nướng chảo", R.drawable.banhnuongchao,5.2));
        arrayFood.add(new Food("Trứng ốp la", R.drawable.trungopla, 6.1));
        arrayFood.add(new Food("Salad rau dầm", R.drawable.saladraudam, 5.5));
        arrayFood.add(new Food("Trái cây tổng hợp", R.drawable.traicaytonghop,7.8));
        arrayFood.add(new Food("Spaghetti", R.drawable.spaghetti, 8.3));
        arrayFood.add(new Food("Bánh Donut", R.drawable.donut, 9.2));
        arrayFood.add(new Food("Hamburger", R.drawable.hamburger, 10.1));
        arrayFood.add(new Food("Bánh panini", R.drawable.panini, 12.1));
    }
}
