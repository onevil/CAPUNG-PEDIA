package site.ishaalim.capungpedia.ayoPengamatan;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import site.ishaalim.capungpedia.R;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import java.util.Calendar;


public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getContext(), (DatePickerDialog.OnDateSetListener) getTargetFragment(), year, month, day);
    }
}
