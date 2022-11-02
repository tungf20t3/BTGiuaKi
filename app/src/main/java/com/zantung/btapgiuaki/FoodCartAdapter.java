package com.zantung.btapgiuaki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FoodCartAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Food> foodList;

    public FoodCartAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgFoodCart;
        TextView txtTenfoodCart, txtGiaSP;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FoodCartAdapter.ViewHolder holder;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder = new FoodCartAdapter.ViewHolder();
            //anh xa
            holder.txtTenfoodCart = view.findViewById(R.id.tvItemFoodCart);
            holder.imgFoodCart = view.findViewById(R.id.itemImgCart);
            holder.txtGiaSP = view.findViewById(R.id.tvPriceCart);
            view.setTag(holder);
        }else {
            holder = (FoodCartAdapter.ViewHolder) view.getTag();
        }
        //gán giá trị
        Food food = foodList.get(i);
        holder.txtTenfoodCart.setText(food.getTenFood());
        holder.txtGiaSP.setText("$" + food.getGia());
        Glide
                .with(context)
                .load("https://zantung.000webhostapp.com/" + food.getHinh())
                .fitCenter()
                .into(holder.imgFoodCart);
        return view;
    }
}
