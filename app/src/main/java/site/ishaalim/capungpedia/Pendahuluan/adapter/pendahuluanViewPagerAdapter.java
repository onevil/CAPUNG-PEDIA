package site.ishaalim.capungpedia.Pendahuluan.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

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
import site.ishaalim.capungpedia.Pendahuluan.ChildFragmentPendahuluan;
import site.ishaalim.capungpedia.panduanPengamatan.ChildFragmentPanduanPengamatan;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class pendahuluanViewPagerAdapter extends FragmentStateAdapter {

    public pendahuluanViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        int halaman = 1 + position;
        bundle.putInt("halaman", halaman);
        ChildFragmentPendahuluan pendahuluan = new ChildFragmentPendahuluan();
        pendahuluan.setArguments(bundle);
        return pendahuluan;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
