package com.zantung.btapgiuaki.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.zantung.btapgiuaki.HomeActivity;
import com.zantung.btapgiuaki.R;
import com.zantung.btapgiuaki.SigninActivity;
import com.zantung.btapgiuaki.SignupActivity;

public class SettingFragment extends Fragment {
    ImageView ivDangXuat;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_setting, container, false);
        ivDangXuat = (ImageView) view.findViewById(R.id.imgViewLogout);
        ivDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SigninActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
