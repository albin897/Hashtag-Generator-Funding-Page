package abc.sadnoxx.hashtaggenerator1


import abc.sadnoxx.hashtaggenerator1.fragments.hashtag.hashtags.Card
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Vibrator
import android.preference.PreferenceManager
import android.text.SpannableStringBuilder
import android.widget.Toast


private const val KEY_PLATFORM = "platform"
private const val PLATFORM_INSTAGRAM = 0
private const val PLATFORM_INSTAGRAM_STORIES = 1
private const val PLATFORM_TIKTOK = 2
private const val PLATFORM_TWITTER = 3
private const val PLATFORM_YOUTUBE = 4
private const val PLATFORM_FACEBOOK = 5
private const val PLATFORM_LINKEDIN = 6
private const val PLATFORM_PINTEREST = 7
private const val PLATFORM_SNAPCHAT = 8

private const val KEY_SEPERATOR = "separator"

private lateinit var sharedPrefs: SharedPreferences

class FilterCopiedText {


    fun sentTheCardIn(context: Context,tagsText1: Card,resources: Resources){
            val tagsString = tagsText1.tags
        sentTheCardInWithInt(context,tagsString,resources)
    }

    fun sentTheCardInWithInt(context: Context,tagsText1: Int,resources: Resources) {


        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)

        val separatorUsed = sharedPrefs.getString(KEY_SEPERATOR, " ")
        val separatorCharSequence: CharSequence = SpannableStringBuilder(separatorUsed)


        when (sharedPrefs.getInt(KEY_PLATFORM, 0)) {
            PLATFORM_INSTAGRAM -> {
                // Call the function for Instagram platform with modified tagsText
                val modifiedTagsText = modifyTagsForInstagram(tagsText1,resources ,separatorCharSequence)
                copyToClipboard(modifiedTagsText,context)
            }

           PLATFORM_INSTAGRAM_STORIES -> {
                // Call the function for Instagram Stories platform with modified tagsText
                val modifiedTagsText =
                    modifyTagsForInstagramStories(tagsText1,resources , separatorCharSequence)
                copyToClipboard(modifiedTagsText,context)
            }

            PLATFORM_TIKTOK -> {
                // Call the function for TikTok platform with modified tagsText
                val modifiedTagsText = modifyTagsForTikTok(tagsText1,resources , separatorCharSequence)
                copyToClipboard(modifiedTagsText,context)
            }

            PLATFORM_TWITTER -> {
                // Call the function for Twitter platform with modified tagsText
                val modifiedTagsText = modifyTagsForTwitter(tagsText1, resources ,separatorCharSequence)
                copyToClipboard(modifiedTagsText,context)
            }

            PLATFORM_YOUTUBE -> {
                // Call the function for YouTube platform with modified tagsText
                val modifiedTagsText = modifyTagsForYouTube(tagsText1,resources , separatorCharSequence)
                copyToClipboard(modifiedTagsText,context)
            }

            PLATFORM_FACEBOOK -> {
                // Call the function for Facebook platform with modified tagsText
                val modifiedTagsText = modifyTagsForFacebook(tagsText1,resources , separatorCharSequence)
                copyToClipboard(modifiedTagsText,context)
            }

            PLATFORM_LINKEDIN -> {
                // Call the function for LinkedIn platform with modified tagsText
                val modifiedTagsText = modifyTagsForLinkedIn(tagsText1,resources , separatorCharSequence)
                copyToClipboard(modifiedTagsText,context)
            }

            PLATFORM_PINTEREST -> {
                // Call the function for Pinterest platform with modified tagsText
                val modifiedTagsText = modifyTagsForPinterest(tagsText1,resources , separatorCharSequence)
                copyToClipboard(modifiedTagsText,context)
            }

            PLATFORM_SNAPCHAT -> {
                // Call the function for Snapchat platform with modified tagsText
                val modifiedTagsText = modifyTagsForSnapchat(tagsText1,resources , separatorCharSequence)
                copyToClipboard(modifiedTagsText,context)
            }

            else -> {
                // Platform value not recognized
//                Toast.makeText(requireContext(), "Unknown platform", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun modifyTagsForInstagram(
        tagsText1: Int,
        resources: Resources,
        separatorCharSequence: CharSequence
    ): String {
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 0)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString =
            resources.getString(tagsText1) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        return "$dots$tagsText"
    }

    private fun modifyTagsForInstagramStories(
        tagsText1: Int,
        resources: Resources,
        separatorCharSequence: CharSequence
    ): String {
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString =
            resources.getString(tagsText1) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        return tagsToCopy.joinToString(separatorCharSequence)
    }

    private fun modifyTagsForTikTok(
        tagsText1: Int,
        resources: Resources,
        separatorCharSequence: CharSequence
    ): String {
        val maxCharactersToCopy = sharedPrefs.getInt(
            "sliderCharecterCopyValue",
            150
        ) // Retrieve the maximum number of characters to copy from shared preferences
        val tagsString =
            resources.getString(tagsText1) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsText = tagsList.joinToString(separatorCharSequence)
        val truncatedTagsText = if (tagsText.length > maxCharactersToCopy) {
            val truncatedText = tagsText.substring(
                0,
                maxCharactersToCopy
            ) // Truncate the tags text to the maximum character limit

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
        tagsText1: Int,
        resources: Resources,
        separatorCharSequence: CharSequence
    ): String {
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 0)
        val maxCharactersToCopy = sharedPrefs.getInt("sliderCharecterCopyValue", 240)
        val tagsString =
            resources.getString(tagsText1) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsText = if (maxCharactersToCopy > 0) {

            val truncatedTagsText = tagsList.joinToString(separatorCharSequence)
                .take(maxCharactersToCopy) // Limit the tags text to the maximum character count
            truncatedTagsText
        } else {
            tagsList.joinToString(separatorCharSequence)
        }
        val dots = ".\n".repeat(dotCount)
        return "$dots$tagsText"
    }


    private fun modifyTagsForYouTube(
        tagsText1: Int,
        resources: Resources,
        separatorCharSequence: CharSequence
    ): String {
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 15)
        val tagsString =
            resources.getString(tagsText1) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        return tagsToCopy.joinToString(separatorCharSequence)
    }

    private fun modifyTagsForFacebook(
        tagsText1: Int,
        resources: Resources,
        separatorCharSequence: CharSequence
    ): String {
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 0)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString =
            resources.getString(tagsText1) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        return "$dots$tagsText"
    }

    private fun modifyTagsForLinkedIn(
        tagsText1: Int,
        resources: Resources,
        separatorCharSequence: CharSequence
    ): String {
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 0)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString =
            resources.getString(tagsText1) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        return "$dots$tagsText"
    }

    private fun modifyTagsForPinterest(
        tagsText1: Int,
        resources: Resources,
        separatorCharSequence: CharSequence
    ): String {
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 0)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 20)
        val tagsString =
            resources.getString(tagsText1) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        return "$dots$tagsText"
    }

    private fun modifyTagsForSnapchat(
        tagsText1: Int,
        resources: Resources,
        separatorCharSequence: CharSequence
    ): String {
        val dotCount = sharedPrefs.getInt("sliderDotAboveValue", 0)
        val maxTagsToCopy = sharedPrefs.getInt("sliderCopyValue", 30)
        val tagsString =
            resources.getString(tagsText1) // Assuming you have access to the resources object
        val tagsList = tagsString.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val tagsToCopy = tagsList.take(maxTagsToCopy)
        val tagsText = tagsToCopy.joinToString(separatorCharSequence)
        val dots = ".\n".repeat(dotCount)
        return "$dots$tagsText"
    }


    private fun copyToClipboard(tagsText: String, context: Context) {

        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        HapticUtils.performHapticFeedback(vibrator, sharedPrefs)
        // Copy tags text to clipboard
        val clipboardManager =
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clipData = ClipData.newPlainText("Tags", tagsText)
        clipboardManager.setPrimaryClip(clipData)

        // Show a toast message indicating that the text has been copied
        Toast.makeText(context, "Tags copied", Toast.LENGTH_SHORT).show()
    }
}