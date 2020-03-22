package site.ishaalim.capungpedia.UI.ayoPengamatan.adapter;

import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import site.ishaalim.capungpedia.UI.ayoPengamatan.ListPengamatanFragment;
import site.ishaalim.capungpedia.UI.ayoPengamatan.ProfileFragment;

public class PengamatanViewPagerAdapter extends FragmentStateAdapter {
    public PengamatanViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        ListPengamatanFragment listPengamatanFragment = new ListPengamatanFragment();
        ProfileFragment profileFragment = new ProfileFragment();

        switch (position){
            case 0:
                return listPengamatanFragment;
            case 1:
                return profileFragment;
            default:
                return listPengamatanFragment;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
