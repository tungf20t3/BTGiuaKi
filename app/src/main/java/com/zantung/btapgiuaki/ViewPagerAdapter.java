package com.zantung.btapgiuaki;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.zantung.btapgiuaki.fragment.HomeFragment;
import com.zantung.btapgiuaki.fragment.ProfileFragment;
import com.zantung.btapgiuaki.fragment.SettingFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();

            case 1:
                return new ProfileFragment();

            case 2:
                return new SettingFragment();

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}