package site.ishaalim.capungpedia.UI.petunjukPenggunaan.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import site.ishaalim.capungpedia.UI.petunjukPenggunaan.ChildFragmentPetunjukPenggunaan;

public class PentunjukPengunaanViewPagerAdapter extends FragmentStateAdapter {

    public PentunjukPengunaanViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        int halaman = 1 + position;
        bundle.putInt("halaman", halaman);
        ChildFragmentPetunjukPenggunaan petunjukPenggunaan = new ChildFragmentPetunjukPenggunaan();
        petunjukPenggunaan.setArguments(bundle);
        return petunjukPenggunaan;
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
