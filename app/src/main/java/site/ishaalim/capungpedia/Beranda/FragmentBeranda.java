package site.ishaalim.capungpedia.Beranda;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

import androidx.fragment.app.FragmentManager;
import site.ishaalim.capungpedia.Beranda.adapter.SliderAdapter;
import site.ishaalim.capungpedia.MainActivity;
import site.ishaalim.capungpedia.R;


public class FragmentBeranda extends Fragment {

    private Switch DarkModeswitch;
    private ImageButton buttonNav;

    SliderAdapter sliderAdapter;

    ArrayList<String> links = new ArrayList<>();

    public FragmentBeranda() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beranda, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        setUpSliderView();

        buttonNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).openDrawer();
            }
        });
        /*if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            DarkModeswitch.setChecked(true);
        }
        DarkModeswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    recreate();
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    recreate();
                }
            }
        });*/

    }

    private void setUpSliderView() {
        SliderView sliderView = getView().findViewById(R.id.imageSlider_Header);
        sliderAdapter = new  SliderAdapter(getContext(), links);
        sliderView.setSliderAdapter(sliderAdapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);

        sliderView.setScrollTimeInSec(5);
        sliderView.startAutoCycle();
    }

    private void initUI() {
        DarkModeswitch = getView().findViewById(R.id.myswitch);
        buttonNav = getView().findViewById(R.id.btn_drawer);
    }


}

