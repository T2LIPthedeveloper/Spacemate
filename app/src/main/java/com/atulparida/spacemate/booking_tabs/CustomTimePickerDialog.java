package com.atulparida.spacemate.booking_tabs;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.atulparida.spacemate.R;

import java.util.Calendar;


public class CustomTimePickerDialog extends DialogFragment {
    private TimePickerDialog.OnTimeSetListener mListener;
    private boolean mIs24HourView;

    public CustomTimePickerDialog(TimePickerDialog.OnTimeSetListener listener, boolean is24HourView) {
        mListener = listener;
        mIs24HourView = is24HourView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getActivity(), mListener, hour, minute, mIs24HourView);
        timePickerDialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);

        return timePickerDialog;
    }
}
