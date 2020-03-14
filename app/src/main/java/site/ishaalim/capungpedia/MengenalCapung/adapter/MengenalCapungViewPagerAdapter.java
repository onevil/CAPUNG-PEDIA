package site.ishaalim.capungpedia.MengenalCapung.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import site.ishaalim.capungpedia.Beranda.FragmentBeranda;
import site.ishaalim.capungpedia.MengenalCapung.ChildFragmentMengenalCapung;
import site.ishaalim.capungpedia.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

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
