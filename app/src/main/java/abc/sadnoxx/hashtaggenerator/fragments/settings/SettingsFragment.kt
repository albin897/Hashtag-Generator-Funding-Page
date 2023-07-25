package abc.sadnoxx.hashtaggenerator.fragments.settings

import abc.sadnoxx.hashtaggenerator.HapticUtils.performHapticFeedback
import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.UpdateDialog
import abc.sadnoxx.hashtaggenerator.fragments.tools.route.RouteActivity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Vibrator
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import java.util.Locale

private const val KEY_THEME = "theme"
private const val THEME_LIGHT = 0
private const val THEME_DARK = 1
private const val THEME_SYSTEM = 2

private const val KEY_SCREEN = "screen"
private const val SCREEN_TOOLS = 0
private const val SCREEN_HASHTAGS = 1
private const val SCREEN_FONTS = 2

private const val KEY_LANGUAGE = "language1"
private const val LANGUAGE_ENGLISH = "en"
private const val LANGUAGE_HINDI = "hi"
private const val LANGUAGE_PUNJABI = "pn"


class SettingsFragment : Fragment() {


    private lateinit var themeSelector: MaterialCardView
    private lateinit var sleepSwitch: MaterialSwitch
    private lateinit var vibrationsSwitch: MaterialSwitch
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var refreshBtn: ImageView
    private lateinit var themeNotifier: TextView
    private lateinit var languageDisplay: TextView
    private lateinit var startingScreen: MaterialCardView
    private lateinit var aboutApp: MaterialCardView
    private lateinit var language: MaterialCardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)



        themeSelector = rootView.findViewById(R.id.card)
        sleepSwitch = rootView.findViewById(R.id.sleepingSwitch)
        vibrationsSwitch = rootView.findViewById(R.id.vibrationsSwitch)
        refreshBtn = rootView.findViewById(R.id.refreshBtn)
        themeNotifier = rootView.findViewById(R.id.themeNotifier)
        startingScreen = rootView.findViewById(R.id.startingScreen)
        aboutApp = rootView.findViewById(R.id.aboutApp)
        language = rootView.findViewById(R.id.language)
        languageDisplay = rootView.findViewById(R.id.languageDisplay)


        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())


        //To set the appropriate text for theme card based on the selected theme
        when (sharedPreferences.getInt(KEY_THEME, THEME_LIGHT)) {
            THEME_LIGHT -> themeNotifier.text = resources.getString(R.string.light)
            THEME_DARK -> themeNotifier.text = resources.getString(R.string.dark)
            THEME_SYSTEM -> themeNotifier.text = resources.getString(R.string.system_default)
        }

        when (sharedPreferences.getString(KEY_LANGUAGE, LANGUAGE_ENGLISH)) {
            LANGUAGE_ENGLISH -> {
                languageDisplay.text = resources.getString(R.string.english)
                setAppLanguage("en")
            }

            LANGUAGE_HINDI -> {
                languageDisplay.text = resources.getString(R.string.hindi)
                setAppLanguage("hi")
            }

            LANGUAGE_PUNJABI -> {
                languageDisplay.text = resources.getString(R.string.punjabi)
                setAppLanguage("pn")
            }
        }



        startingScreen.setOnClickListener {
            performHapticFeedback(vibrator, sharedPreferences)
            showScreenSelectionDialog()
        }


        themeSelector.setOnClickListener {
            performHapticFeedback(vibrator, sharedPreferences)
            showThemeSelectionDialog()
        }

        language.setOnClickListener {
            performHapticFeedback(vibrator, sharedPreferences)
            showLanguageSelectionDialog()
        }


        sleepSwitch.isChecked = sharedPreferences.getBoolean("sleepChecked", true)
        vibrationsSwitch.isChecked = sharedPreferences.getBoolean("vibrationSwitch", true)


        vibrationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Respond to switch being checked/unchecked
            if (isChecked) {
                // Switch is checked
                performHapticFeedback(vibrator, sharedPreferences)
                with(sharedPreferences.edit()) {
                    putBoolean("vibrationSwitch", true)
                    apply()
                }

            } else {
                performHapticFeedback(vibrator, sharedPreferences)
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
                performHapticFeedback(vibrator, sharedPreferences)
                with(sharedPreferences.edit()) {
                    putBoolean("sleepChecked", true)
                    apply()
                }
                activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            } else {
                performHapticFeedback(vibrator, sharedPreferences)
                // Switch is unchecked
                with(sharedPreferences.edit()) {
                    putBoolean("sleepChecked", false)
                    apply()
                }
                activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
        }



        refreshBtn.setOnClickListener {
            val connectivityManager =
                requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo

            if ((networkInfo != null) && networkInfo.isConnected) {
                checkForAppUpdate()
            } else {
                showNoInternetConnectionDialog()
            }
            performHapticFeedback(vibrator, sharedPreferences)
        }

        aboutApp.setOnClickListener {
            val intent = Intent(activity, RouteActivity::class.java)
            intent.putExtra("fragment", "about")
            startActivity(intent)
            performHapticFeedback(vibrator, sharedPreferences)

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
        when (sharedPreferences.getInt(KEY_THEME, THEME_LIGHT)) {
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

    private fun showLanguageSelectionDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_language_orginal_selection, null)

        val english = dialogView.findViewById<RadioButton>(R.id.english)
        val hindi = dialogView.findViewById<RadioButton>(R.id.hindi)
//        val punjabi = dialogView.findViewById<RadioButton>(R.id.punjabi)

        // Retrieve the saved theme from SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        //        val activity = requireActivity()


        // Set the appropriate radio button based on the saved theme
        when (sharedPreferences.getString(KEY_LANGUAGE, LANGUAGE_ENGLISH)) {
            LANGUAGE_ENGLISH -> english.isChecked = true
            LANGUAGE_HINDI -> hindi.isChecked = true
//            LANGUAGE_PUNJABI -> punjabi.isChecked = true
        }

        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.radio_group)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.dialog_title_language_selection)
            .setView(dialogView)
            .setPositiveButton(R.string.ok) { _, _ ->
                val languageRadioButtonId = radioGroup.checkedRadioButtonId
                val selectedLanguage = getLanguageForRadioButtonId(languageRadioButtonId)
                applyLanguage(selectedLanguage)
                activity?.recreate()
            }
            .setNegativeButton(R.string.cancel, null)
            .create()

        dialog.show()
    }


    private fun getLanguageForRadioButtonId(LanguageRadioButtonId: Int): String {
        return when (LanguageRadioButtonId) {
            R.id.hindi -> LANGUAGE_HINDI
//            R.id.punjabi -> LANGUAGE_PUNJABI
            else -> LANGUAGE_ENGLISH
        }
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

    private fun applyLanguage(selectedLanguage: String) {
        when (selectedLanguage) {
            LANGUAGE_ENGLISH -> {
                setAppLanguage("en")
                languageDisplay.text = resources.getString(R.string.english)
            }

            LANGUAGE_HINDI -> {
                setAppLanguage("hi")
                languageDisplay.text = resources.getString(R.string.hindi)
            }

//            LANGUAGE_PUNJABI -> {
//                languageDisplay.text = resources.getString(R.string.punjabi)
//            }
        }

        // Save the selected theme to SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = sharedPreferences.edit()
        editor.putString(KEY_LANGUAGE, selectedLanguage)

        editor.apply()
    }

    private fun setAppLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale

        // Update the app's configuration with the new locale
        resources.updateConfiguration(config, resources.displayMetrics)


    }

    private lateinit var loadingDialog: AlertDialog
    private fun checkForAppUpdate() {
        val appUpdateManager = AppUpdateManagerFactory.create(requireContext())

        val appUpdateInfoTask = appUpdateManager.appUpdateInfo


        showLoadingDialog() // Show loading dialog

        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                || appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
            ) {
                dismissLoadingDialog()
                context?.let { it1 -> UpdateDialog(it1) }?.showNewVersionDialog()
            } else {
                dismissLoadingDialog()
                context?.let { it1 -> UpdateDialog(it1) }?.showNoUpdatesDialog()
            }
        }

    }

    private fun showLoadingDialog() {
        val dialogBuilder = MaterialAlertDialogBuilder(requireContext())
            .setMessage(R.string.loading)
            .setCancelable(false)

        loadingDialog = dialogBuilder.create()
        loadingDialog.show()
    }

    private fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }

    private fun showNoInternetConnectionDialog() {
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setMessage("\n\tNetwork Connection Error")
            .setPositiveButton(R.string.ok) { dialog, _ ->
                // Positive button click action
                dialog.dismiss()
            }
            .setCancelable(true)
            .create()

        dialog.show()
    }


}
