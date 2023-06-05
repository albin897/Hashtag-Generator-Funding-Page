package abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags

import abc.sadnoxx.hashtaggenerator.R
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {


    private lateinit var selectPlatformTab: LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var platformName: TextView

    private val KEY_PLATFORM = "platform"
    private val PLATFORM_INSTAGRAM = 0
    private val PLATFORM_INSTAGRAM_STORIES = 1
    private val PLATFORM_TIKTOK = 2
    private val PLATFORM_TWITTER = 3
    private val PLATFORM_YOUTUBE = 4
    private val PLATFORM_FACEBOOK = 5
    private val PLATFORM_LINKEDIN = 6
    private val PLATFORM_PINTEREST = 7
    private val PLATFORM_SNAPCHAT = 8

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)

        // Set the click listener to expand/collapse the bottom sheet
        view.setOnClickListener {
            if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = sharedPrefs.edit()

        platformName = view.findViewById(R.id.platformName)
        selectPlatformTab = view.findViewById(R.id.selectPlatformTab)


        selectPlatformTab.setOnClickListener {
            showThemeSelectionDialog()

        }


        return view
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog

        val view = View.inflate(context, R.layout.fragment_bottom_sheet, null)
        dialog.setContentView(view)

        val behavior = BottomSheetBehavior.from(view.parent as View)
        behavior.halfExpandedRatio = 0.5f
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED

        bottomSheetBehavior = behavior

        return dialog
    }


    private fun showThemeSelectionDialog() {
        val inflater = LayoutInflater.from(requireContext())
        val dialogView = inflater.inflate(R.layout.dialog_theme_selection, null)

        val radioInstagram = dialogView.findViewById<RadioButton>(R.id.radioInstagram)
        val radioInstagramStories = dialogView.findViewById<RadioButton>(R.id.radioInstagramStories)
        val radioTiktok = dialogView.findViewById<RadioButton>(R.id.radioTiktok)
        val radioTwitter = dialogView.findViewById<RadioButton>(R.id.radioTwitter)
        val radioYoutube = dialogView.findViewById<RadioButton>(R.id.radioYoutube)
        val radioFacebook = dialogView.findViewById<RadioButton>(R.id.radioFacebook)
        val radioLinkedin = dialogView.findViewById<RadioButton>(R.id.radioLinkedin)
        val radioPinterest = dialogView.findViewById<RadioButton>(R.id.radioPinterest)
        val radioSnapchat = dialogView.findViewById<RadioButton>(R.id.radioSnapchat)

        // Retrieve the saved theme from SharedPreferences
        val  sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val savedTheme = sharedPrefs.getInt(KEY_PLATFORM, PLATFORM_INSTAGRAM)

        // Set the appropriate radio button based on the saved theme
        when (savedTheme) {
            PLATFORM_INSTAGRAM -> radioInstagram.isChecked = true
            PLATFORM_INSTAGRAM_STORIES -> radioInstagramStories.isChecked = true
            PLATFORM_TIKTOK -> radioTiktok.isChecked = true
            PLATFORM_TWITTER -> radioTwitter.isChecked = true
            PLATFORM_YOUTUBE -> radioYoutube.isChecked = true
            PLATFORM_FACEBOOK -> radioFacebook.isChecked = true
            PLATFORM_LINKEDIN -> radioLinkedin.isChecked = true
            PLATFORM_PINTEREST -> radioPinterest.isChecked = true
            PLATFORM_SNAPCHAT -> radioSnapchat.isChecked = true
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
            R.id.radioInstagram -> PLATFORM_INSTAGRAM
            R.id.radioInstagramStories -> PLATFORM_INSTAGRAM_STORIES
            R.id.radioTiktok -> PLATFORM_TIKTOK
            R.id.radioTwitter -> PLATFORM_TWITTER
            R.id.radioYoutube -> PLATFORM_YOUTUBE
            R.id.radioFacebook -> PLATFORM_FACEBOOK
            R.id.radioLinkedin -> PLATFORM_LINKEDIN
            R.id.radioPinterest -> PLATFORM_PINTEREST
            else -> PLATFORM_SNAPCHAT
        }
    }

    private fun applyTheme(theme: Int) {
        // Apply the selected theme using AppCompatDelegate
        when (theme) {
            PLATFORM_INSTAGRAM -> platformName.setText(R.string.instagram)
            PLATFORM_INSTAGRAM_STORIES -> platformName.setText(R.string.instagram_stories)
            PLATFORM_TIKTOK -> platformName.setText(R.string.tikTok)
            PLATFORM_TWITTER -> platformName.setText(R.string.twitter)
            PLATFORM_YOUTUBE -> platformName.setText(R.string.youTube)
            PLATFORM_FACEBOOK -> platformName.setText(R.string.facebook)
            PLATFORM_LINKEDIN -> platformName.setText(R.string.linkedIn)
            PLATFORM_PINTEREST -> platformName.setText(R.string.pinterest)
            PLATFORM_SNAPCHAT -> platformName.setText(R.string.snapchat)
        }

        // Save the selected theme to SharedPreferences
        val  sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = sharedPrefs.edit()
        editor.putInt(KEY_PLATFORM, theme)
        editor.apply()
    }


}
