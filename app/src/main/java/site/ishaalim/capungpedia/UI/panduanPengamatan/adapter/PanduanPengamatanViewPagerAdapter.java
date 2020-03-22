package site.ishaalim.capungpedia.UI.panduanPengamatan.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import site.ishaalim.capungpedia.UI.panduanPengamatan.ChildFragmentPanduanPengamatan;

public class PanduanPengamatanViewPagerAdapter extends FragmentStateAdapter {

    public PanduanPengamatanViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        int halaman = 1 + position;
        bundle.putInt("halaman", halaman);
        ChildFragmentPanduanPengamatan panduanPengamatan = new ChildFragmentPanduanPengamatan();
        panduanPengamatan.setArguments(bundle);
        return panduanPengamatan;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
