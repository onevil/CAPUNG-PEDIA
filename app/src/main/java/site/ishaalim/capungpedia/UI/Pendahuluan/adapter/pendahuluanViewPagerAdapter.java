package site.ishaalim.capungpedia.UI.Pendahuluan.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import site.ishaalim.capungpedia.UI.Pendahuluan.ChildFragmentPendahuluan;

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
