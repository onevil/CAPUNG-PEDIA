package site.ishaalim.capungpedia.ayoPengamatan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import site.ishaalim.capungpedia.R;

import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN;
import static android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailPengamatanFragment extends Fragment {
    Toolbar toolbar;


    public DetailPengamatanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_pengamatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();
        setHasOptionsMenu(true);
        setupToolbar();
//        getActivity().getWindow().setSoftInputMode(SOFT_INPUT_ADJUST_PAN);

    }

    private void setupToolbar() {
    }

    private void initUI() {
        toolbar = getView().findViewById(R.id.toolbar_detail_pengamatan);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    private void back() {
        getFragmentManager().beginTransaction().remove(this).commit();
    }
}
