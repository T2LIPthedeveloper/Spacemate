package com.atulparida.spacemate.booking_tabs;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.atulparida.spacemate.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class booking_fragment extends DialogFragment {

    private View view;
    private List<String> memberNames = new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_booking, container, false);

        EditText dateInput = view.findViewById(R.id.date_input);
        EditText startTimeInput = view.findViewById(R.id.start_time_input);
        EditText endTimeInput = view.findViewById(R.id.end_time_input);

        dateInput.setOnClickListener(v -> showDatePickerDialog(dateInput));
        startTimeInput.setOnClickListener(v -> showTimePickerDialog(startTimeInput, false));
        endTimeInput.setOnClickListener(v -> showTimePickerDialog(endTimeInput, false));

        Button submitButton = view.findViewById(R.id.book_now_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle booking submission here
                // Do something with the memberNames list
                dismiss();
            }
        });

        CheckBox groupSubmissionCheckbox = view.findViewById(R.id.group_submission_checkbox);
        LinearLayout membersList = view.findViewById(R.id.members_list);
        ImageButton addMemberButton = view.findViewById(R.id.add_member_button);

        groupSubmissionCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                addMemberButton.setVisibility(View.VISIBLE);
            } else {
                addMemberButton.setVisibility(View.GONE);
                membersList.removeAllViews();
            }
        });

        addMemberButton.setOnClickListener(v -> {
            if (groupSubmissionCheckbox.isChecked()) {
                addMemberRow(membersList);
            }
        });

        addMemberButton.setVisibility(View.GONE); // Hide the button initially

        return view;
    }

    private void addMemberRow(LinearLayout membersList) {
        LinearLayout memberRow = new LinearLayout(getContext());
        memberRow.setOrientation(LinearLayout.HORIZONTAL);

        EditText memberEditText = new EditText(getContext());
        memberEditText.setHint("Enter member name");
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
        editParams.setMargins(0, 8, 0, 0);
        memberEditText.setLayoutParams(editParams);

        Button saveButton = new Button(getContext());
        saveButton.setText("Save");
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(8, 8, 0, 0);
        saveButton.setLayoutParams(buttonParams);

        saveButton.setOnClickListener(v -> {
            saveMemberName(memberEditText, saveButton);
        });

        memberRow.addView(memberEditText);
        memberRow.addView(saveButton);
        membersList.addView(memberRow);
    }

    private void saveMemberName(EditText memberEditText, Button saveButton) {
        String memberName = memberEditText.getText().toString().trim();
        if (!memberName.isEmpty()) {
            memberNames.add(memberName);
            saveButton.setVisibility(View.GONE);
        }
    }
    private void showDatePickerDialog(final EditText dateInput) {
        CustomDatePickerDialog datePickerDialog = new CustomDatePickerDialog((view, selectedYear, selectedMonth, selectedDay) -> {
            String dateString = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            dateInput.setText(dateString);
        });
        datePickerDialog.show(getChildFragmentManager(),"datePicker");
    }
    private void showTimePickerDialog(final EditText timeInput, boolean is24HourView) {
        CustomTimePickerDialog timePickerDialog = new CustomTimePickerDialog((view, selectedHour, selectedMinute) -> {
            String timeString = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
            timeInput.setText(timeString);
        }, is24HourView);
        timePickerDialog.show(getChildFragmentManager(), "timePicker");
    }
}
