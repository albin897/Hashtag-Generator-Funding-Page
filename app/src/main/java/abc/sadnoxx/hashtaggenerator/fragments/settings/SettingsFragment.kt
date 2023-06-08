package abc.sadnoxx.hashtaggenerator.fragments.settings

import abc.sadnoxx.hashtaggenerator.R
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class SettingsFragment : Fragment() {


    private lateinit var themeSelector: MaterialCardView;

    private val KEY_THEME = "theme"
    private val THEME_LIGHT = 0
    private val THEME_DARK = 1
    private val THEME_SYSTEM = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        // Inflate the layout for this fragment
        themeSelector = rootView.findViewById(R.id.card)

        themeSelector.setOnClickListener { v: View? -> showThemeSelectionDialog() }

        return rootView
    }

    private fun showThemeSelectionDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_themeorginal_selection, null)

        val lightRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_light)
        val darkRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_dark)
        val systemRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_system)

        // Retrieve the saved theme from SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val savedTheme = sharedPreferences.getInt(KEY_THEME, THEME_SYSTEM)
        val activity = requireActivity()


        // Set the appropriate radio button based on the saved theme
        when (savedTheme) {
            THEME_LIGHT -> lightRadioButton.isChecked = true
            THEME_DARK -> darkRadioButton.isChecked = true
            THEME_SYSTEM -> systemRadioButton.isChecked = true
        }

        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialog_title_theme_selection)
            .setView(dialogView)
            .setPositiveButton("OK") { dialogInterface, i ->
                val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                val selectedTheme = getThemeForRadioButtonId(selectedRadioButtonId)
                applyTheme(selectedTheme)
            }
            .setNegativeButton("cancel", null)
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
        // Apply the selected theme using AppCompatDelegate
        when (theme) {
            THEME_LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            THEME_DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            THEME_SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }

        // Save the selected theme to SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_THEME, theme)

        editor.apply()
        activity?.recreate()

    }

}