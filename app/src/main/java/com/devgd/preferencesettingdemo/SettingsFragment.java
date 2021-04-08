package com.devgd.preferencesettingdemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    SharedPreferences.OnSharedPreferenceChangeListener preflistentr=new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
            switch (s){
                case "reply":
                    Toast.makeText(getContext(), findPreference(s).getSummary(), Toast.LENGTH_SHORT).show();
                    break;
                case "sync":
                    Toast.makeText(getContext(), "Sync Value "+String.valueOf(sharedPreferences.getBoolean(s,true)), Toast.LENGTH_SHORT).show();
                    break;
                case "attachment":
                    Toast.makeText(getContext(), "Attachment Value "+String.valueOf(sharedPreferences.getBoolean(s,false)), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(preflistentr);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preflistentr);
    }
}
