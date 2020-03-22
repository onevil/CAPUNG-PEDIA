package site.ishaalim.capungpedia.UI.mengenalDesa.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import site.ishaalim.capungpedia.UI.mengenalDesa.ChildFragmentMengenalDesa;

public class MengenalDesaViewPagerAdapter extends FragmentStateAdapter {
    public MengenalDesaViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        int halaman = 1 + position;
        bundle.putInt("halaman", halaman);
        ChildFragmentMengenalDesa mengenalDesa = new ChildFragmentMengenalDesa();
        mengenalDesa.setArguments(bundle);
        return mengenalDesa;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
