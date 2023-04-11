package com.atulparida.spacemate.booking_tabs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.atulparida.spacemate.R;

import java.util.Calendar;

public class CustomDatePickerDialog extends DialogFragment {
    private DatePickerDialog.OnDateSetListener mListener;

    public CustomDatePickerDialog(DatePickerDialog.OnDateSetListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(), mListener, year, month, day);
        datePickerDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);

        return datePickerDialog;
    }
}
