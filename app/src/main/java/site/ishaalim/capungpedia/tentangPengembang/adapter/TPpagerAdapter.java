package site.ishaalim.capungpedia.tentangPengembang.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import site.ishaalim.capungpedia.R;
import site.ishaalim.capungpedia.tentangPengembang.tentangAplikasiFragment;
import site.ishaalim.capungpedia.tentangPengembang.tentangPembimbingFragment;
import site.ishaalim.capungpedia.tentangPengembang.tentangPengembangFragment;

public class TPpagerAdapter extends FragmentStateAdapter {

    public TPpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        tentangAplikasiFragment tentangAplikasiFragment = new tentangAplikasiFragment();
        tentangPengembangFragment tentangPengembangFragment = new tentangPengembangFragment();
        tentangPembimbingFragment tentangPembimbingFragment = new tentangPembimbingFragment();

        switch (position){
            case 0:
                return tentangAplikasiFragment;
            case 1:
                return tentangPengembangFragment;
            case 2:
                return tentangPembimbingFragment;
            default:
                return tentangAplikasiFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
