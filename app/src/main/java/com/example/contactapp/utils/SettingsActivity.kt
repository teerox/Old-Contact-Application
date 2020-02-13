package com.example.contactapp.utils

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.example.contactapp.R

class SettingsActivity : AppCompatActivity(),
    SharedPreferences.OnSharedPreferenceChangeListener {
        override fun onCreate(savedInstanceState: Bundle?) {
            setTheme(R.style.CustomTheme)
            super.onCreate(savedInstanceState)
            setContentView(R.layout.settings_activity)

            if (supportFragmentManager.findFragmentById(android.R.id.content) == null){
                supportFragmentManager
                    .beginTransaction()
                    .replace(android.R.id.content, SettingsFragment())
                    .commit()
            }

            val prefs = PreferenceManager.getDefaultSharedPreferences(this)

            prefs.registerOnSharedPreferenceChangeListener(this)
        }

        class SettingsFragment: PreferenceFragmentCompat(){
            override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
                setPreferencesFromResource(R.xml.preference, rootKey)
            }
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
            if (sharedPreferences?.getBoolean("night_mood", false)!!) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }




}