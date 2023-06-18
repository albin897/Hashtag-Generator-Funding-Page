package abc.sadnoxx.hashtaggenerator.fragments.settings

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.UpdateDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability

private const val KEY_THEME = "theme"
private const val THEME_LIGHT = 0
private const val THEME_DARK = 1
private const val THEME_SYSTEM = 2

private const val KEY_SCREEN = "screen"
private const val SCREEN_TOOLS = 0
private const val SCREEN_HASHTAGS = 1
private const val SCREEN_FONTS = 2
class SettingsFragment : Fragment() {


    private lateinit var themeSelector: MaterialCardView
    private lateinit var sleepSwitch: MaterialSwitch
    private lateinit var vibrationsSwitch: MaterialSwitch
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var refreshBtn: ImageView
    private lateinit var themeNotifier: TextView
    private lateinit var  reportBugsCard: MaterialCardView
    private lateinit var   startingScreen: MaterialCardView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)
        // Inflate the layout for this fragment
        themeSelector = rootView.findViewById(R.id.card)
        sleepSwitch = rootView.findViewById(R.id.sleepingSwitch)
        vibrationsSwitch = rootView.findViewById(R.id.vibrationsSwitch)
        refreshBtn = rootView.findViewById(R.id.refreshBtn)
        themeNotifier = rootView.findViewById(R.id.themeNotifier)
        reportBugsCard= rootView.findViewById(R.id.reportBugsCard)
        startingScreen= rootView.findViewById(R.id.startingScreen)

        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator



        reportBugsCard.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:sadnoxx.dev@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Bug Report / Suggestion")
                putExtra(Intent.EXTRA_TEXT, "Please describe the bug you encountered:")
            }

            if (emailIntent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(emailIntent)
            } else {
                Toast.makeText(requireContext(), "Gmail app not found.", Toast.LENGTH_SHORT).show()
            }
        }

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        //To set the appropriate text for thme card based on the selected theme
        when (sharedPreferences.getInt(KEY_THEME, THEME_SYSTEM)) {
            THEME_LIGHT -> themeNotifier.text = resources.getString(R.string.light)
            THEME_DARK -> themeNotifier.text = resources.getString(R.string.dark)
            THEME_SYSTEM ->themeNotifier.text = resources.getString(R.string.system_default)
        }



        startingScreen.setOnClickListener {
            performHapticFeedback(vibrator)
            showScreenSelectionDialog()
        }


        themeSelector.setOnClickListener {
            performHapticFeedback(vibrator)
            showThemeSelectionDialog() }

        sleepSwitch.isChecked = sharedPreferences.getBoolean("sleepChecked", true)
        vibrationsSwitch.isChecked = sharedPreferences.getBoolean("vibrationSwitch", true)


        vibrationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Respond to switch being checked/unchecked
            if (isChecked) {
                // Switch is checked
                performHapticFeedback(vibrator)
                with(sharedPreferences.edit()) {
                    putBoolean("vibrationSwitch", true)
                    apply()
                }

            } else {
                performHapticFeedback(vibrator)
                // Switch is unchecked
                with(sharedPreferences.edit()) {
                    putBoolean("vibrationSwitch", false)
                    apply()
                }
            }
        }

        sleepSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Respond to switch being checked/unchecked
            if (isChecked) {
                // Switch is checked
                performHapticFeedback(vibrator)
                with(sharedPreferences.edit()) {
                    putBoolean("sleepChecked", true)
                    apply()
                }
                activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            } else {
                performHapticFeedback(vibrator)
                // Switch is unchecked
                with(sharedPreferences.edit()) {
                    putBoolean("sleepChecked", false)
                    apply()
                }
                activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
        }



        refreshBtn.setOnClickListener {
            checkForAppUpdate()
            performHapticFeedback(vibrator)
        }


        return rootView
    }





    private fun showScreenSelectionDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_screenselection, null)

        val toolsRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_tools)
        val hashtagsRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_hashtags)
        val fontsRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_fonts)

        // Retrieve the saved theme from SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        //        val activity = requireActivity()


        // Set the appropriate radio button based on the saved theme
        when (sharedPreferences.getInt(KEY_SCREEN, SCREEN_HASHTAGS)) {
            SCREEN_TOOLS -> toolsRadioButton.isChecked = true
            SCREEN_HASHTAGS -> hashtagsRadioButton.isChecked = true
            SCREEN_FONTS -> fontsRadioButton.isChecked = true
        }

        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialog_title_screen_selection)
            .setView(dialogView)
            .setPositiveButton(R.string.ok) { _, _ ->
                val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                val selectedScreen = getScreenForRadioButtonId(selectedRadioButtonId)
                applyScreen(selectedScreen)
            }
            .setNegativeButton(R.string.cancel, null)
            .create()

        dialog.show()
    }





    private fun showThemeSelectionDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_themeorginal_selection, null)

        val lightRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_light)
        val darkRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_dark)
        val systemRadioButton = dialogView.findViewById<RadioButton>(R.id.radio_system)

        // Retrieve the saved theme from SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        //        val activity = requireActivity()


        // Set the appropriate radio button based on the saved theme
        when (sharedPreferences.getInt(KEY_THEME, THEME_SYSTEM)) {
            THEME_LIGHT -> lightRadioButton.isChecked = true
            THEME_DARK -> darkRadioButton.isChecked = true
            THEME_SYSTEM -> systemRadioButton.isChecked = true
        }

        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialog_title_theme_selection)
            .setView(dialogView)
            .setPositiveButton(R.string.ok) { _, _ ->
                val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                val selectedTheme = getThemeForRadioButtonId(selectedRadioButtonId)
                applyTheme(selectedTheme)
            }
            .setNegativeButton(R.string.cancel, null)
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


    private fun getScreenForRadioButtonId(selectedRadioButtonId: Int): Int {
        return when (selectedRadioButtonId) {
            R.id.radio_fonts -> SCREEN_FONTS
            R.id.radio_hashtags -> SCREEN_HASHTAGS
            else -> SCREEN_TOOLS
        }
    }

    private fun applyTheme(theme: Int) {
        // Apply the selected theme using AppCompatDelegate
        when (theme) {
            THEME_LIGHT -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            THEME_DARK -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            THEME_SYSTEM -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }

        // Save the selected theme to SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_THEME, theme)

        editor.apply()
        activity?.recreate()

    }



    private fun applyScreen(selectedScreen: Int) {
//        when(selectedScreen){
//            SCREEN_TOOLS -> {}
//            SCREEN_HASHTAGS -> {}
//            SCREEN_FONTS -> {}
//        }

        // Save the selected theme to SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_SCREEN, selectedScreen)

        editor.apply()
    }



    private fun performHapticFeedback(vibrator: Vibrator) {

        val vibrationEnabled = sharedPreferences.getBoolean("vibrationSwitch", true)

        if (vibrationEnabled) {
        // Trigger haptic feedback for a short duration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            // Deprecated in API 26
            vibrator.vibrate(30)
        }}
    }


    private fun checkForAppUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(requireContext())

// Returns an intent object that you use to check for an update.
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo

// Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                // This example applies an immediate update. To apply a flexible update
                || appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
//                Toast.makeText(requireContext(), "Update available", Toast.LENGTH_SHORT).show()
//                newVersion.visibility = View.VISIBLE
//                latestTxt.visibility = View.GONE
                context?.let { it1 -> UpdateDialog(it1) }?.showNewVersionDialog()
            } else {
//                Toast.makeText(requireContext(), "No update available", Toast.LENGTH_SHORT).show()
//                latestTxt.visibility = View.VISIBLE
//                newVersion.visibility = View.GONE
                context?.let { it1 -> UpdateDialog(it1) }?.showNoUpdatesDialog()
            }
        }

    }


}