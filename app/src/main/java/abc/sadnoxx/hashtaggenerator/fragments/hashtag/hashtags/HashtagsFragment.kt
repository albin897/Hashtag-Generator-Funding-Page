package abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.saved.SavedHashtagsFragment
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONArray
import org.json.JSONObject

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

private val KEY_SEPERATOR = "separator"
private val SEPERATOR_SPACE = " "
private val SEPERATOR_NOTHING = ""
private val SEPERATOR_COMMA = ","
private val SEPERATOR_BULLET = "•"
private val SEPERATOR_ASTERISK = "*"
private val SEPERATOR_TILDE = "~"



class HashtagsFragment : Fragment(),
    CardAdapter.OnCopyClickListener {

    private lateinit var searchBar: TextInputEditText
    private lateinit var cardAdapter: CardAdapter
    private lateinit var searchBarTop: TextInputLayout
    private lateinit var fab: ExtendedFloatingActionButton
    private lateinit var fab0: ExtendedFloatingActionButton
    private lateinit var  generateHashSearchBtn: Button
    private lateinit var platformImage: ImageView
    private lateinit var platformName: TextView
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_hashtags, container, false)

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        searchBarTop = rootView.findViewById(R.id.searchbartop)
        fab = rootView.findViewById(R.id.floating_action_button)
        fab0 = rootView.findViewById(R.id.floating_action_button0)
        generateHashSearchBtn = rootView.findViewById(R.id.generateHashSearchBtn)
        platformName = rootView.findViewById(R.id.platformName)
        platformImage = rootView.findViewById(R.id.platformImage)
//        fab0.setOnClickListener {
//            val bottomSheetFragment = MyBottomSheetDialogFragment()
//            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
//        }

        val savedPlatform = sharedPrefs.getInt(KEY_PLATFORM, PLATFORM_INSTAGRAM)

        setPlatformName(savedPlatform)

        fab.setOnClickListener {
            if (searchBarTop.visibility == View.VISIBLE) {
                searchBarTop.visibility = View.GONE
            } else {
                searchBarTop.visibility = View.VISIBLE
            }
        }






        var newdata: List<Card> = CardDataRepository.cardDataList.take(5)


        searchBar = rootView.findViewById(R.id.search_bar)
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)

        cardAdapter = CardAdapter(CardDataRepository.cardDataList,newdata,requireContext())

        cardAdapter.setOnCopyClickListener(this)
        recyclerView.adapter = cardAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        var query : String? = null

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                val query = s.toString().trim()
//                cardAdapter.filterData(query)
            }

            override fun afterTextChanged(s: Editable?) {
                 query = s.toString().trim()

//                if (query.isNullOrEmpty()) {
//                    query = " " // Set query to empty string if it's null or empty
//                    cardAdapter.filterData(query ?: "")
//                }


            }
        })


        generateHashSearchBtn.setOnClickListener {
            query?.let { it1 -> cardAdapter.filterData(it1) }
            val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(searchBar.windowToken, 0)
        }


        searchBar.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Show the keyboard
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(searchBar, 0)
            } else {
                // Hide the keyboard
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchBar.windowToken, 0)
            }
        }

        return rootView
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
    }


    override fun onCopyClick(tagsText1: Card) {

        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val separatorUsed = sharedPrefs.getString(KEY_SEPERATOR, " ")
        val separatorCharSequence: CharSequence = SpannableStringBuilder(separatorUsed)


        when (sharedPrefs.getInt(KEY_PLATFORM, 0)) {
            PLATFORM_INSTAGRAM -> {
                // Call the function for Instagram platform with modified tagsText
                val modifiedTagsText = modifyTagsForInstagram(tagsText1, separatorCharSequence)
                copyToClipboard(modifiedTagsText)
            }

            PLATFORM_INSTAGRAM_STORIES -> {
                // Call the function for Instagram Stories platform with modified tagsText
                val modifiedTagsText =
                    modifyTagsForInstagramStories(tagsText1, separatorCharSequence)
                copyToClipboard(modifiedTagsText)
            }

            PLATFORM_TIKTOK -> {
                // Call the function for TikTok platform with modified tagsText
                val modifiedTagsText = modifyTagsForTikTok(tagsText1, separatorCharSequence)
                copyToClipboard(modifiedTagsText)
            }

            PLATFORM_TWITTER -> {
                // Call the function for Twitter platform with modified tagsText
                val modifiedTagsText = modifyTagsForTwitter(tagsText1, separatorCharSequence)
                copyToClipboard(modifiedTagsText)
            }

            PLATFORM_YOUTUBE -> {
                // Call the function for YouTube platform with modified tagsText
                val modifiedTagsText = modifyTagsForYouTube(tagsText1, separatorCharSequence)
                copyToClipboard(modifiedTagsText)
            }

            PLATFORM_FACEBOOK -> {
                // Call the function for Facebook platform with modified tagsText
                val modifiedTagsText = modifyTagsForFacebook(tagsText1, separatorCharSequence)
                copyToClipboard(modifiedTagsText)
            }

            PLATFORM_LINKEDIN -> {
                // Call the function for LinkedIn platform with modified tagsText
                val modifiedTagsText = modifyTagsForLinkedIn(tagsText1, separatorCharSequence)
                copyToClipboard(modifiedTagsText)
            }

            PLATFORM_PINTEREST -> {
                // Call the function for Pinterest platform with modified tagsText
                val modifiedTagsText = modifyTagsForPinterest(tagsText1, separatorCharSequence)
                copyToClipboard(modifiedTagsText)
            }

            PLATFORM_SNAPCHAT -> {
                // Call the function for Snapchat platform with modified tagsText
                val modifiedTagsText = modifyTagsForSnapchat(tagsText1, separatorCharSequence)
                copyToClipboard(modifiedTagsText)
            }

            else -> {
                // Platform value not recognized
                Toast.makeText(requireContext(), "Unknown platform", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun modifyTagsForInstagram(
        tagsText1: Card,
        separatorCharSequence: CharSequence
    ): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 10)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString = resources.getString(tagsText1.tags) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        val newTagsText = "$dots$tagsText"
        return newTagsText
    }

    private fun modifyTagsForInstagramStories(
        tagsText1: Card,
        separatorCharSequence: CharSequence
    ): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString = resources.getString(tagsText1.tags) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        return tagsText
    }

    private fun modifyTagsForTikTok(
        tagsText1: Card,
        separatorCharSequence: CharSequence
    ): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val maxCharactersToCopy = sharedPrefs.getInt("sliderCharecterCopyValue", 150) // Retrieve the maximum number of characters to copy from shared preferences
        val tagsString = resources.getString(tagsText1.tags) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsText = tagsList.joinToString(separatorCharSequence)
        val truncatedTagsText = if (tagsText.length > maxCharactersToCopy) {
            val truncatedText = tagsText.substring(0, maxCharactersToCopy) // Truncate the tags text to the maximum character limit

            // Find the last complete tag within the truncated text
            val lastTagIndex = truncatedText.lastIndexOf(separatorCharSequence.toString())
            if (lastTagIndex >= maxCharactersToCopy - separatorCharSequence.length) { // Check if the last tag is half-cropped     not workking
                truncatedText.substring(0, lastTagIndex)
            } else {
                truncatedText
            }
        } else {
            tagsText
        }

        return truncatedTagsText
    }


    private fun modifyTagsForTwitter(
        tagsText1: Card,
        separatorCharSequence: CharSequence
    ): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 10)
        val maxCharactersToCopy = sharedPrefs.getInt("sliderCharecterCopyValue", 240)
        val tagsString = resources.getString(tagsText1.tags) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsText = if (maxCharactersToCopy > 0) {

            val truncatedTagsText = tagsList.joinToString(separatorCharSequence)
                .take(maxCharactersToCopy) // Limit the tags text to the maximum character count
            truncatedTagsText
        } else {
            tagsList.joinToString(separatorCharSequence)
        }
        val dots = ".\n".repeat(dotCount)
        val newTagsText = "$dots$tagsText"
        return newTagsText
    }


    private fun modifyTagsForYouTube(
        tagsText1: Card,
        separatorCharSequence: CharSequence
    ): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 15)
        val tagsString = resources.getString(tagsText1.tags) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        return tagsText
    }

    private fun modifyTagsForFacebook(
        tagsText1: Card,
        separatorCharSequence: CharSequence
    ): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 10)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString = resources.getString(tagsText1.tags) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        val newTagsText = "$dots$tagsText"
        return newTagsText
    }

    private fun modifyTagsForLinkedIn(
        tagsText1: Card,
        separatorCharSequence: CharSequence
    ): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 10)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString = resources.getString(tagsText1.tags) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        val newTagsText = "$dots$tagsText"
        return newTagsText
    }

    private fun modifyTagsForPinterest(
        tagsText1: Card,
        separatorCharSequence: CharSequence
    ): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 10)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 20)
        val tagsString = resources.getString(tagsText1.tags) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        val newTagsText = "$dots$tagsText"
        return newTagsText
    }

    private fun modifyTagsForSnapchat(
        tagsText1: Card,
        separatorCharSequence: CharSequence
    ): String {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 10)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString = resources.getString(tagsText1.tags) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        val newTagsText = "$dots$tagsText"
        return newTagsText
    }

// Implement the modifyTagsForTikTok, modifyTagsForTwitter, and other platform-specific functions similarly

    private fun copyToClipboard(tagsText: String) {
        // Copy tags text to clipboard
        val clipboardManager =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clipData = ClipData.newPlainText("Tags", tagsText)
        clipboardManager.setPrimaryClip(clipData)

        // Show a toast message indicating that the text has been copied
        Toast.makeText(requireContext(), "Tags copied", Toast.LENGTH_SHORT).show()
    }





}


