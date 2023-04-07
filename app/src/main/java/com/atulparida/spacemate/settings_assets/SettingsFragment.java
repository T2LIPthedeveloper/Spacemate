package com.atulparida.spacemate.settings_assets;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;

import com.atulparida.spacemate.R;
import com.atulparida.spacemate.login_assets.LoginActivity;
import com.atulparida.spacemate.login_assets.SplashActivity;

public class SettingsFragment extends PreferenceFragment {

    private Preference masterNotifPreference, upcomingBookings, newSpaces, passwordChangeButton, signOutButton, deleteAccountButton, needHelpButton, aboutButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // below line is used to add preference
        // fragment from our xml folder.
        addPreferencesFromResource(R.xml.preferences);

        initPreferences();
        initNotificationToggles();
        initPreferenceClickListeners();


    }

    private void initNotificationToggles() {
        masterNotifPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {
                Boolean bool = (Boolean) o;
                if (bool == false) {
                    //visual logic
                    upcomingBookings.setEnabled(false);
                    upcomingBookings.setShouldDisableView(true);

                    newSpaces.setEnabled(false);
                    newSpaces.setShouldDisableView(true);

                    //disable notification
                }
                else if (bool == true) {
                    //visual logic
                    upcomingBookings.setEnabled(true);
                    newSpaces.setEnabled(true);

                    //enable notification
                }
                return true;
            }
        });
    }

    private void initPreferenceClickListeners() {
        passwordChangeButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //TODO: add dialog box
                return true;
            }
        });

        signOutButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Intent intent = new Intent(preference.getContext(), LoginActivity.class);
                startActivity(intent);
                return true;
            }
        });

        needHelpButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //TODO: Create help page and set intent
                return true;
            }
        });

        aboutButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                //TODO: Create about page and set intent
                return true;
            }
        });
    }

    private void initPreferences() {
        //toggles
        masterNotifPreference = findPreference(getString(R.string.enable_notifications));
        upcomingBookings = findPreference(getString(R.string.upcoming_booking_notifications));
        newSpaces = findPreference(getString(R.string.new_spaces_notifications));

        //buttons
        passwordChangeButton = findPreference(getString(R.string.change_password_key));
        signOutButton = findPreference(getString(R.string.sign_out_key));
        deleteAccountButton = findPreference(getString(R.string.del_acc_key));
        needHelpButton = findPreference(getString(R.string.help_page_key));
        aboutButton = findPreference(getString(R.string.about_key));
    }
}