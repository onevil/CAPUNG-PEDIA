package site.ishaalim.capungpedia.MengenalCapung.adapter;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import site.ishaalim.capungpedia.Beranda.FragmentBeranda;
import site.ishaalim.capungpedia.MengenalCapung.ChildFragmentMengenalCapung;
import site.ishaalim.capungpedia.R;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MengenalCapungViewPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> FragmentList = new ArrayList<>();
    private final ArrayList<String> FragmentTitleList = new ArrayList<>();
    

    private  final ChildFragmentMengenalCapung cf1 = new ChildFragmentMengenalCapung();
    private  final FragmentBeranda cf2 = new FragmentBeranda();
    private  final ChildFragmentMengenalCapung cf3 = new ChildFragmentMengenalCapung();

    Context context;
    ViewPager viewPager;
    TabLayout tabLayout;
    String nama = "nama";

    public MengenalCapungViewPagerAdapter(FragmentManager fm, Context context, ViewPager viewPager, TabLayout tabLayout) {
        super(fm);
        this.context = context;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;
    }

    public void addPage(Fragment fragment) {
        FragmentList.add(fragment);
        String sizee = Integer.toString(FragmentList.size());
        Log.d(TAG, "size array :" + sizee);
    }

    @Override
    public Fragment getItem(int position) {
//        return FragmentList.get(position);
        if (position == 0) {
            return cf1;
        } else if (position == 1) {
            return cf2;
        } else if (position == 2) {
            return cf3;
        } else {
            return cf2;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return nama;
            case 1:
                return nama;
            case 2:
                return nama;
            default:
                return null;
        }

        //    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return POSITION_NONE;
//    }
    }


}
