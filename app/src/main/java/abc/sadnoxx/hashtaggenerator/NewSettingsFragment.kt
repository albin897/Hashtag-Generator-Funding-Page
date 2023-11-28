package abc.sadnoxx.hashtaggenerator

import abc.sadnoxx.hashtaggenerator.fragments.settings.UpdateDialog
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
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
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
private const val LANGUAGE_RUSSIAN = "ru"
private const val LANGUAGE_GERMAN = "de"

private const val LANGUAGE_FRENCH = "fr"
private const val LANGUAGE_INDONESIAN = "in"

private const val LANGUAGE_JAPANESE = "ja"
private const val LANGUAGE_KOREAN = "ko"
private const val LANGUAGE_CHINESE = "zh"
private const val LANGUAGE_ITALIAN = "it"

private const val LANGUAGE_PORTUGUESE = "pt"
private const val LANGUAGE_SPANISH = "es"



class NewSettingsFragment : Fragment() {


    private lateinit var themeSelector: LinearLayout
    private lateinit var sleepSwitch: MaterialSwitch
    private lateinit var vibrationsSwitch: MaterialSwitch
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var refreshBtn: LinearLayout
    private lateinit var themeNotifier: TextView
    private lateinit var languageDisplay: TextView
    private lateinit var startingScreen: LinearLayout
    private lateinit var aboutApp: LinearLayout
    private lateinit var language: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_new_settings, container, false)


        themeSelector = rootView.findViewById(R.id.themeSelector)
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
        when (sharedPreferences.getInt(
            KEY_THEME,
            THEME_LIGHT
        )) {
            THEME_LIGHT -> themeNotifier.text = resources.getString(R.string.light)
            THEME_DARK -> themeNotifier.text = resources.getString(R.string.dark)
            THEME_SYSTEM -> themeNotifier.text = resources.getString(R.string.system_default)
        }

        when (sharedPreferences.getString(
           KEY_LANGUAGE,
           LANGUAGE_ENGLISH
        )) {
            LANGUAGE_ENGLISH -> {
                languageDisplay.text = resources.getString(R.string.english)
                setAppLanguage("en")
            }

            LANGUAGE_HINDI -> {
                languageDisplay.text = resources.getString(R.string.hindi)
                setAppLanguage("hi")
            }

            LANGUAGE_RUSSIAN -> {
                languageDisplay.text = resources.getString(R.string.russian)
                setAppLanguage("ru")
            }

            LANGUAGE_GERMAN -> {
                languageDisplay.text = resources.getString(R.string.german)
                setAppLanguage("de")
            }

            LANGUAGE_FRENCH -> {
                languageDisplay.text = resources.getString(R.string.french)
                setAppLanguage("fr")
            }

            LANGUAGE_INDONESIAN -> {
                languageDisplay.text = resources.getString(R.string.indonesian)
                setAppLanguage("in")
            }

            LANGUAGE_JAPANESE -> {
                languageDisplay.text = resources.getString(R.string.japanese)
                setAppLanguage("ja")
            }

            LANGUAGE_KOREAN -> {
                languageDisplay.text = resources.getString(R.string.korean)
                setAppLanguage("ko")
            }

            LANGUAGE_ITALIAN -> {
                languageDisplay.text = resources.getString(R.string.italian)
                setAppLanguage("it")
            }

           LANGUAGE_PORTUGUESE -> {
                languageDisplay.text = resources.getString(R.string.portuguese)
                setAppLanguage("pt")
            }

            LANGUAGE_SPANISH -> {
                languageDisplay.text = resources.getString(R.string.spanish)
                setAppLanguage("es")
            }

            LANGUAGE_CHINESE -> {
                languageDisplay.text = resources.getString(R.string.chinese)
                setAppLanguage("zh")
            }

        }



        startingScreen.setOnClickListener {
            HapticUtils.performHapticFeedback(vibrator, sharedPreferences)
            showScreenSelectionDialog()
        }


        themeSelector.setOnClickListener {
            HapticUtils.performHapticFeedback(vibrator, sharedPreferences)
            showThemeSelectionDialog()
        }

        language.setOnClickListener {
            HapticUtils.performHapticFeedback(vibrator, sharedPreferences)
            showLanguageSelectionDialog()
        }


        sleepSwitch.isChecked = sharedPreferences.getBoolean("sleepChecked", true)
        vibrationsSwitch.isChecked = sharedPreferences.getBoolean("vibrationSwitch", true)


        vibrationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Respond to switch being checked/unchecked
            if (isChecked) {
                // Switch is checked
                HapticUtils.performHapticFeedback(vibrator, sharedPreferences)
                with(sharedPreferences.edit()) {
                    putBoolean("vibrationSwitch", true)
                    apply()
                }

            } else {
                HapticUtils.performHapticFeedback(vibrator, sharedPreferences)
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
                HapticUtils.performHapticFeedback(vibrator, sharedPreferences)
                with(sharedPreferences.edit()) {
                    putBoolean("sleepChecked", true)
                    apply()
                }
                activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            } else {
                HapticUtils.performHapticFeedback(vibrator, sharedPreferences)
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
            HapticUtils.performHapticFeedback(vibrator, sharedPreferences)
        }

        aboutApp.setOnClickListener {
            val intent = Intent(activity, RouteModifiedActivity::class.java)
            intent.putExtra("fragment", "about")
            startActivity(intent)
            HapticUtils.performHapticFeedback(vibrator, sharedPreferences)

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
        when (sharedPreferences.getInt(
            KEY_SCREEN,
            SCREEN_HASHTAGS
        )) {
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
        when (sharedPreferences.getInt(
            KEY_THEME,
            THEME_LIGHT
        )) {
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
        val russian = dialogView.findViewById<RadioButton>(R.id.russian)
        val german = dialogView.findViewById<RadioButton>(R.id.german)
        val french = dialogView.findViewById<RadioButton>(R.id.french)
        val indonesian = dialogView.findViewById<RadioButton>(R.id.indonesian)
        val japanese = dialogView.findViewById<RadioButton>(R.id.japanese)
        val korean = dialogView.findViewById<RadioButton>(R.id.korean)
        val italian = dialogView.findViewById<RadioButton>(R.id.italian)
        val portuguese = dialogView.findViewById<RadioButton>(R.id.portuguese)
        val spanish = dialogView.findViewById<RadioButton>(R.id.spanish)
        val chinese = dialogView.findViewById<RadioButton>(R.id.chinese)


        // Retrieve the saved theme from SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        //        val activity = requireActivity()


        // Set the appropriate radio button based on the saved theme
        when (sharedPreferences.getString(
            KEY_LANGUAGE,
            LANGUAGE_ENGLISH
        )) {
           LANGUAGE_ENGLISH -> english.isChecked = true
           LANGUAGE_HINDI -> hindi.isChecked = true
           LANGUAGE_RUSSIAN -> russian.isChecked = true
           LANGUAGE_GERMAN -> german.isChecked = true
           LANGUAGE_FRENCH -> french.isChecked = true
           LANGUAGE_INDONESIAN -> indonesian.isChecked = true
           LANGUAGE_JAPANESE -> japanese.isChecked = true
           LANGUAGE_KOREAN -> korean.isChecked = true
           LANGUAGE_ITALIAN -> italian.isChecked = true
           LANGUAGE_PORTUGUESE -> portuguese.isChecked = true
           LANGUAGE_SPANISH -> spanish.isChecked = true
           LANGUAGE_CHINESE -> chinese.isChecked = true
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
            R.id.russian -> LANGUAGE_RUSSIAN
            R.id.german -> LANGUAGE_GERMAN
            R.id.french -> LANGUAGE_FRENCH
            R.id.indonesian -> LANGUAGE_INDONESIAN
            R.id.japanese -> LANGUAGE_JAPANESE
            R.id.korean -> LANGUAGE_KOREAN
            R.id.italian -> LANGUAGE_ITALIAN
            R.id.portuguese -> LANGUAGE_PORTUGUESE
            R.id.spanish -> LANGUAGE_SPANISH
            R.id.chinese -> LANGUAGE_CHINESE
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
            LANGUAGE_RUSSIAN -> {
                setAppLanguage("ru")
                languageDisplay.text = resources.getString(R.string.russian)
            }

            LANGUAGE_GERMAN -> {
                setAppLanguage("de")
                languageDisplay.text = resources.getString(R.string.german)
            }

            LANGUAGE_FRENCH -> {
                languageDisplay.text = resources.getString(R.string.french)
                setAppLanguage("fr")
            }

            LANGUAGE_INDONESIAN -> {
                languageDisplay.text = resources.getString(R.string.indonesian)
                setAppLanguage("in")
            }

         LANGUAGE_JAPANESE -> {
                languageDisplay.text = resources.getString(R.string.japanese)
                setAppLanguage("ja")
            }

            LANGUAGE_KOREAN -> {
                languageDisplay.text = resources.getString(R.string.korean)
                setAppLanguage("ko")
            }

            LANGUAGE_ITALIAN -> {
                languageDisplay.text = resources.getString(R.string.italian)
                setAppLanguage("it")
            }

            LANGUAGE_PORTUGUESE -> {
                languageDisplay.text = resources.getString(R.string.portuguese)
                setAppLanguage("pt")
            }

            LANGUAGE_SPANISH -> {
                languageDisplay.text = resources.getString(R.string.spanish)
                setAppLanguage("es")
            }

            LANGUAGE_CHINESE -> {
                languageDisplay.text = resources.getString(R.string.chinese)
                setAppLanguage("zh")
            }

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
