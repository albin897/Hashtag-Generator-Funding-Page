package abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags

import abc.sadnoxx.hashtaggenerator.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
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
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

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

class HashtagsFragment : Fragment(), CardAdapter.OnSaveClickListener,
    CardAdapter.OnCopyClickListener {

    private lateinit var searchBar: TextInputEditText
    private lateinit var cardAdapter: CardAdapter
    private lateinit var searchBarTop: TextInputLayout
    private lateinit var fab: ExtendedFloatingActionButton
    private lateinit var fab0: ExtendedFloatingActionButton

    private val savedCards: MutableList<Card> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_hashtags, container, false)


        searchBarTop = rootView.findViewById(R.id.searchbartop)
        fab = rootView.findViewById(R.id.floating_action_button)
        fab0 = rootView.findViewById(R.id.floating_action_button0)



        fab0.setOnClickListener {
            val bottomSheetFragment = MyBottomSheetDialogFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }


        fab.setOnClickListener {
            if (searchBarTop.visibility == View.VISIBLE) {
                searchBarTop.visibility = View.GONE
            } else {
                searchBarTop.visibility = View.VISIBLE
            }
        }







        searchBar = rootView.findViewById(R.id.search_bar)
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)

        cardAdapter = CardAdapter(CardDataRepository.cardDataList,requireContext())
        cardAdapter.setOnSaveClickListener(this)
        cardAdapter.setOnCopyClickListener(this)
        recyclerView.adapter = cardAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                cardAdapter.filterData(query)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })


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

    override fun onSaveClick(card: Card) {
        savedCards.add(card)
        Log.d("TAGCHECKING", "onSaveClick: $savedCards ")
//        savedCardAdapter?.notifyDataSetChanged()

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


