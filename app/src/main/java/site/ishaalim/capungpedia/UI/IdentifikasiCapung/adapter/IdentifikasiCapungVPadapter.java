package site.ishaalim.capungpedia.UI.IdentifikasiCapung.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import site.ishaalim.capungpedia.UI.IdentifikasiCapung.AnisopteraFragment;
import site.ishaalim.capungpedia.UI.IdentifikasiCapung.ZygopteraFragment;

public class IdentifikasiCapungVPadapter extends FragmentStateAdapter {
    public IdentifikasiCapungVPadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        AnisopteraFragment anisopteraFragment = new AnisopteraFragment();
        ZygopteraFragment zygopteraFragment = new ZygopteraFragment();

        switch (position){
            case 0:
                return anisopteraFragment;
            case 1:
                return zygopteraFragment;
            default:
                return anisopteraFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
