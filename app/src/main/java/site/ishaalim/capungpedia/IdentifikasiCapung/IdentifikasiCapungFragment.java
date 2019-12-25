package site.ishaalim.capungpedia.IdentifikasiCapung;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import site.ishaalim.capungpedia.R;


public class IdentifikasiCapungFragment extends Fragment {

    private Toolbar toolbar;

    private TabLayout tabLayout;

    private android.widget.SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;


    public IdentifikasiCapungFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_identifikasi_capung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initUI();
        toolbar.inflateMenu(R.menu.menu_toolbar_idcapung);
        setupTabLayout();
        setHasOptionsMenu(true);


        if (savedInstanceState == null){
            getFragmentManager().beginTransaction().replace(R.id.fl_identifikasi_capung,
                    new  AnisopteraFragment()).commit();
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    getFragmentManager().beginTransaction().replace(R.id.fl_identifikasi_capung,
                            new  AnisopteraFragment()).commit();
                }else if (tab.getPosition() == 1){
                    getFragmentManager().beginTransaction().replace(R.id.fl_identifikasi_capung,
                            new ZygopteraFragment()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_toolbar_idcapung, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getContext().getSystemService(Context.SEARCH_SERVICE);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        if (searchItem != null) {
            searchView = (android.widget.SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onOptionsItemSelected(item);
    }

    private void setupTabLayout() {
    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_identifikasi_capung);
        tabLayout = getView().findViewById(R.id.tl_materi_identifikasi_capung);
        
        
    }


}
