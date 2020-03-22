package site.ishaalim.capungpedia.UI.panduanIdentifikasi.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import site.ishaalim.capungpedia.UI.panduanIdentifikasi.ChildFragmentPanduanIdentifikasi;

public class PanduanIdentifikasiViewPagerAdapter extends FragmentStateAdapter {

    public PanduanIdentifikasiViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        int halaman = 1 + position;
        bundle.putInt("halaman", halaman);
        ChildFragmentPanduanIdentifikasi panduanIdentifikasi = new ChildFragmentPanduanIdentifikasi();
        panduanIdentifikasi.setArguments(bundle);
        return panduanIdentifikasi;
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
