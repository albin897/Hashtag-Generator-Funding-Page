package abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags

import abc.sadnoxx.hashtaggenerator.R
import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {


    private lateinit var selectPlatformTab: LinearLayout
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var platformName: TextView
    private lateinit var platformName1: TextView
    private lateinit var itemContentDescription: TextView
    private lateinit var platformImage: ImageView
    private lateinit var platformImage1: ImageView

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
        platformName = view.findViewById(R.id.platformName)
        platformName1 = view.findViewById(R.id.platformName1)
        itemContentDescription = view.findViewById(R.id.itemContentDescription)
        platformImage = view.findViewById(R.id.platformImage)
        platformImage1 = view.findViewById(R.id.platformImage1)


        // Retrieve the saved platform from SharedPreferences
        val savedPlatform = sharedPrefs.getInt(KEY_PLATFORM, PLATFORM_INSTAGRAM)

        // Set the platform name based on the saved platform
        setPlatformName(savedPlatform)

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
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
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
            PLATFORM_INSTAGRAM -> {
                platformName.setText(R.string.instagram)
                platformName1.setText(R.string.instagram)
                itemContentDescription.setText(R.string.instagram_content_description)
                platformImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ig
                    )
                )
                platformImage1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ig
                    )
                )
            }

            PLATFORM_INSTAGRAM_STORIES -> {
                platformName.setText(R.string.instagram_stories)
                platformName1.setText(R.string.instagram_stories)
                itemContentDescription.setText(R.string.instagram_stories_content_description)
                platformImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ig
                    )
                )
                platformImage1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ig
                    )
                )
            }

            PLATFORM_TIKTOK -> {
                platformName.setText(R.string.tikTok)
                platformName1.setText(R.string.tikTok)
                itemContentDescription.setText(R.string.tiktok_content_description)
                platformImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.tiktok
                    )
                )
                platformImage1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.tiktok
                    )
                )
            }

            PLATFORM_TWITTER -> {
                platformName.setText(R.string.twitter)
                platformName1.setText(R.string.twitter)
                itemContentDescription.setText(R.string.twitter_content_description)
                platformImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.twitter
                    )
                )
                platformImage1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.twitter
                    )
                )
            }

            PLATFORM_YOUTUBE -> {
                platformName.setText(R.string.youTube)
                platformName1.setText(R.string.youTube)
                itemContentDescription.setText(R.string.youtube_content_description)
                platformImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.youtube
                    )
                )
                platformImage1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.youtube
                    )
                )
            }

            PLATFORM_FACEBOOK -> {
                platformName.setText(R.string.facebook)
                platformName1.setText(R.string.facebook)
                itemContentDescription.setText(R.string.facebook_content_description)
                platformImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.facebook
                    )
                )
                platformImage1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.facebook
                    )
                )
            }

            PLATFORM_LINKEDIN -> {
                platformName.setText(R.string.linkedIn)
                platformName1.setText(R.string.linkedIn)
                itemContentDescription.setText(R.string.linkedin_content_description)
                platformImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.linkedin
                    )
                )
                platformImage1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.linkedin
                    )
                )
            }

            PLATFORM_PINTEREST -> {
                platformName.setText(R.string.pinterest)
                platformName1.setText(R.string.pinterest)
                itemContentDescription.setText(R.string.pinterest_content_description)
                platformImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.pinterest
                    )
                )
                platformImage1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.pinterest
                    )
                )
            }

            PLATFORM_SNAPCHAT -> {
                platformName.setText(R.string.snapchat)
                platformName1.setText(R.string.snapchat)
                itemContentDescription.setText(R.string.snapchat_content_description)
                platformImage.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.snapchat
                    )
                )
                platformImage1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.snapchat
                    )
                )
            }
        }

        // Save the selected theme to SharedPreferences
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = sharedPrefs.edit()
        editor.putInt(KEY_PLATFORM, theme)
        editor.apply()
    }

    private fun setPlatformName(platform: Int) {
        val platformNameResId = when (platform) {
            PLATFORM_INSTAGRAM -> R.string.instagram
            PLATFORM_INSTAGRAM_STORIES -> R.string.instagram_stories
            PLATFORM_TIKTOK -> R.string.tikTok
            PLATFORM_TWITTER -> R.string.twitter
            PLATFORM_YOUTUBE -> R.string.youTube
            PLATFORM_FACEBOOK -> R.string.facebook
            PLATFORM_LINKEDIN -> R.string.linkedIn
            PLATFORM_PINTEREST -> R.string.pinterest
            PLATFORM_SNAPCHAT -> R.string.snapchat
            else -> R.string.instagram
        }
        platformName.setText(platformNameResId)
        platformName1.setText(platformNameResId)

        val platformNameContentResId = when (platform) {
            PLATFORM_INSTAGRAM -> R.string.instagram_content_description
            PLATFORM_INSTAGRAM_STORIES -> R.string.instagram_stories_content_description
            PLATFORM_TIKTOK -> R.string.tiktok_content_description
            PLATFORM_TWITTER -> R.string.twitter_content_description
            PLATFORM_YOUTUBE -> R.string.youtube_content_description
            PLATFORM_FACEBOOK -> R.string.facebook_content_description
            PLATFORM_LINKEDIN -> R.string.linkedin_content_description
            PLATFORM_PINTEREST -> R.string.pinterest_content_description
            PLATFORM_SNAPCHAT -> R.string.snapchat_content_description
            else -> R.string.instagram_content_description
        }
        itemContentDescription.setText(platformNameContentResId)


        val platformImageResId = when (platform) {
            PLATFORM_INSTAGRAM -> R.drawable.ig
            PLATFORM_INSTAGRAM_STORIES -> R.drawable.ig
            PLATFORM_TIKTOK -> R.drawable.tiktok
            PLATFORM_TWITTER -> R.drawable.twitter
            PLATFORM_YOUTUBE -> R.drawable.youtube
            PLATFORM_FACEBOOK -> R.drawable.facebook
            PLATFORM_LINKEDIN -> R.drawable.linkedin
            PLATFORM_PINTEREST -> R.drawable.pinterest
            PLATFORM_SNAPCHAT -> R.drawable.snapchat
            else -> R.drawable.ig
        }
        platformImage.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                platformImageResId
            )
        )
        platformImage1.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                platformImageResId
            )
        )
    }

}
