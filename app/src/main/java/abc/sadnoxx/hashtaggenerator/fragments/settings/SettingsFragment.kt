package abc.sadnoxx.hashtaggenerator.fragments.settings

import abc.sadnoxx.hashtaggenerator.R
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.materialswitch.MaterialSwitch


class SettingsFragment : Fragment() {

    private lateinit var themeSelector: MaterialCardView
    private lateinit var sleepSwitch: MaterialSwitch
    private lateinit var sharedPreferences: SharedPreferences

    private val KEY_THEME = "theme"
    private val THEME_LIGHT = 0
    private val THEME_DARK = 1
    private val THEME_SYSTEM = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)

        themeSelector = rootView.findViewById(R.id.card)
        sleepSwitch = rootView.findViewById(R.id.sleepingSwitch)

        themeSelector.setOnClickListener { showThemeSelectionDialog() }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        sleepSwitch.isChecked = sharedPreferences.getBoolean("sleepChecked", false)

        sleepSwitch.setOnCheckedChangeListener { _, isChecked ->
            with(sharedPreferences.edit()) {
                putBoolean("sleepChecked", isChecked)
                apply()
            }
            activity?.window?.run {
                if (isChecked) {
                    addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                } else {
                    clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
                }
            }
        }

        return rootView
    }

    private fun showThemeSelectionDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_themeorginal_selection, null)

        val lightRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_light)
        val darkRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_dark)
        val systemRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_system)

        val savedTheme = sharedPreferences.getInt(KEY_THEME, THEME_SYSTEM)

        when (savedTheme) {
            THEME_LIGHT -> lightRadioButton.isChecked = true
            THEME_DARK -> darkRadioButton.isChecked = true
            THEME_SYSTEM -> systemRadioButton.isChecked = true
        }

        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialog_title_theme_selection)
            .setView(dialogView)
            .setPositiveButton("OK") { _, _ ->
                val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                val selectedTheme = getThemeForRadioButtonId(selectedRadioButtonId)
                applyTheme(selectedTheme)
            }
            .setNegativeButton("Cancel", null)
            .create()

        dialog.show()
    }

    private fun getThemeForRadioButtonId(radioButtonId: Int): Int {
        return when (radioButtonId) {
            R.id.radio_light -> THEME_LIGHT
            R.id.radio_dark -> THEME_DARK
            else -> THEME_SYSTEM
        }
    }

    private fun applyTheme(theme: Int) {
        when (theme) {
            THEME_LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            THEME_DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            THEME_SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }

        with(sharedPreferences.edit()) {
            putInt(KEY_THEME, theme)
            apply()
        }
        activity?.recreate()
    }
}
