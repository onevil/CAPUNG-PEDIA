package site.ishaalim.capungpedia.UI.MengenalCapung.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import site.ishaalim.capungpedia.UI.MengenalCapung.ChildFragmentMengenalCapung;

public class MengenalCapungViewPagerAdapter extends FragmentStateAdapter {
    public MengenalCapungViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        int halaman = 1 + position;
        bundle.putInt("halaman", halaman);
        ChildFragmentMengenalCapung mengenalCapung = new ChildFragmentMengenalCapung();
        mengenalCapung.setArguments(bundle);
        return mengenalCapung;
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
