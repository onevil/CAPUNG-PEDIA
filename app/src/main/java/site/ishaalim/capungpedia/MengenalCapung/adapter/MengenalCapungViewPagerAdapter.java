package site.ishaalim.capungpedia.MengenalCapung.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MengenalCapungViewPagerAdapter extends FragmentStatePagerAdapter {

    private final ArrayList<Fragment> FragmentList = new ArrayList<>();
    private final ArrayList<String> FragmentTitleList = new ArrayList<>();
    Context context;
    ViewPager viewPager;
    TabLayout tabLayout;

    public MengenalCapungViewPagerAdapter(FragmentManager fm, Context context, ViewPager viewPager, TabLayout tabLayout) {
        super(fm);
        this.context = context;
        this.viewPager = viewPager;
        this.tabLayout = tabLayout;
    }

    public void addPage(Fragment fragment){
        FragmentList.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
