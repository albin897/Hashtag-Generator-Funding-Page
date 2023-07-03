package abc.sadnoxx.hashtaggenerator.fragments.fonts

import abc.sadnoxx.hashtaggenerator.HapticUtils
import abc.sadnoxx.hashtaggenerator.HapticUtils.performHapticFeedback
import abc.sadnoxx.hashtaggenerator.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Vibrator
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText


class FontsFragment : Fragment() {


    private lateinit var contentTxt1: TextView
    private lateinit var contentTxt2: TextView
    private lateinit var contentTxt3: TextView
    private lateinit var contentTxt4: TextView
    private lateinit var contentTxt5: TextView
    private lateinit var contentTxt6: TextView
    private lateinit var contentTxt7: TextView
    private lateinit var contentTxt8: TextView
    private lateinit var contentTxt9: TextView
    private lateinit var contentTxt10: TextView
    private lateinit var contentTxt11: TextView
    private lateinit var contentTxt12: TextView
    private lateinit var contentTxt13: TextView
    private lateinit var contentTxt14: TextView
    private lateinit var contentTxt15: TextView
    private lateinit var contentTxt16: TextView
    private lateinit var contentTxt17: TextView

    private lateinit var inputTextBox: TextInputEditText


    private lateinit var copyButton1: ImageView
    private lateinit var copyButton2: ImageView
    private lateinit var copyButton3: ImageView
    private lateinit var copyButton4: ImageView
    private lateinit var copyButton5: ImageView
    private lateinit var copyButton6: ImageView
    private lateinit var copyButton7: ImageView
    private lateinit var copyButton8: ImageView
    private lateinit var copyButton9: ImageView
    private lateinit var copyButton10: ImageView
    private lateinit var copyButton11: ImageView
    private lateinit var copyButton12: ImageView
    private lateinit var copyButton13: ImageView
    private lateinit var copyButton14: ImageView
    private lateinit var copyButton15: ImageView
    private lateinit var copyButton16: ImageView
    private lateinit var copyButton17: ImageView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fonts, container, false)


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        contentTxt1 = rootView.findViewById(R.id.mainText1)
        contentTxt2 = rootView.findViewById(R.id.mainText2)
        contentTxt3 = rootView.findViewById(R.id.mainText3)
        contentTxt4 = rootView.findViewById(R.id.mainText4)
        contentTxt5 = rootView.findViewById(R.id.mainText5)
        contentTxt6 = rootView.findViewById(R.id.mainText6)
        contentTxt7 = rootView.findViewById(R.id.mainText7)
        contentTxt8 = rootView.findViewById(R.id.mainText8)
        contentTxt9 = rootView.findViewById(R.id.mainText9)
        contentTxt10 = rootView.findViewById(R.id.mainText10)
        contentTxt11 = rootView.findViewById(R.id.mainText11)
        contentTxt12 = rootView.findViewById(R.id.mainText12)
        contentTxt13 = rootView.findViewById(R.id.mainText13)
        contentTxt14 = rootView.findViewById(R.id.mainText14)
        contentTxt15 = rootView.findViewById(R.id.mainText15)
        contentTxt16 = rootView.findViewById(R.id.mainText16)
        contentTxt17 = rootView.findViewById(R.id.mainText17)

        inputTextBox = rootView.findViewById(R.id.edit_text1)

        copyButton1 = rootView.findViewById(R.id.copyButton1)
        copyButton2 = rootView.findViewById(R.id.copyButton2)
        copyButton3 = rootView.findViewById(R.id.copyButton3)
        copyButton4 = rootView.findViewById(R.id.copyButton4)
        copyButton5 = rootView.findViewById(R.id.copyButton5)
        copyButton6 = rootView.findViewById(R.id.copyButton6)
        copyButton7 = rootView.findViewById(R.id.copyButton7)
        copyButton8 = rootView.findViewById(R.id.copyButton8)
        copyButton9 = rootView.findViewById(R.id.copyButton9)
        copyButton10 = rootView.findViewById(R.id.copyButton10)
        copyButton11 = rootView.findViewById(R.id.copyButton11)
        copyButton12 = rootView.findViewById(R.id.copyButton12)
        copyButton13 = rootView.findViewById(R.id.copyButton13)
        copyButton14 = rootView.findViewById(R.id.copyButton14)
        copyButton15 = rootView.findViewById(R.id.copyButton15)
        copyButton16 = rootView.findViewById(R.id.copyButton16)
        copyButton17 = rootView.findViewById(R.id.copyButton17)



        inputTextBox.setText(R.string.fonts_input_box_hint)


        val styleSets = arrayOf(
            StyledTextConverter.Companion.StyleSet.MATHEMATICAL_BOLD_SCRIPT,
            StyledTextConverter.Companion.StyleSet.SANS,
            StyledTextConverter.Companion.StyleSet.SANS_BOLD,
            StyledTextConverter.Companion.StyleSet.SANS_ITALIC,
            StyledTextConverter.Companion.StyleSet.SANS_BOLD_ITALIC,
            StyledTextConverter.Companion.StyleSet.MONOSPACE,
            StyledTextConverter.Companion.StyleSet.FRAKTUR,
            StyledTextConverter.Companion.StyleSet.BOLD_FRAKTUR,
            StyledTextConverter.Companion.StyleSet.CIRCLED,
            StyledTextConverter.Companion.StyleSet.CIRCLED_NEGATIVE,
            StyledTextConverter.Companion.StyleSet.FULL_SQUARED,
            StyledTextConverter.Companion.StyleSet.SQUARED,
            StyledTextConverter.Companion.StyleSet.SCRIPT,
            StyledTextConverter.Companion.StyleSet.DOUBLE_STRUCK,
            StyledTextConverter.Companion.StyleSet.ASIAN,
            StyledTextConverter.Companion.StyleSet.PARADISE,
            StyledTextConverter.Companion.StyleSet.SUPERB
        )

        val copyButtons = arrayOf(
            copyButton1,
            copyButton2,
            copyButton3,
            copyButton4,
            copyButton5,
            copyButton6,
            copyButton7,
            copyButton8,
            copyButton9,
            copyButton10,
            copyButton11,
            copyButton12,
            copyButton13,
            copyButton14,
            copyButton15,
            copyButton16,
            copyButton17
        )


        val contentTextViews = arrayOf(
            contentTxt1,
            contentTxt2,
            contentTxt3,
            contentTxt4,
            contentTxt5,
            contentTxt6,
            contentTxt7,
            contentTxt8,
            contentTxt9,
            contentTxt10,
            contentTxt11,
            contentTxt12,
            contentTxt13,
            contentTxt14,
            contentTxt15,
            contentTxt16,
            contentTxt17
        )

        val welcomeText = inputTextBox.text

        for (i in styleSets.indices) {
            val styledText =
                StyledTextConverter.convertToStyledText(welcomeText.toString(), styleSets[i])
            contentTextViews[i].text = styledText
        }


        // Add a text change listener to the inputTextBox
        inputTextBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {


            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                for (i in styleSets.indices) {
                    val styledText =
                        StyledTextConverter.convertToStyledText(s.toString(), styleSets[i])
                    contentTextViews[i].text = styledText
                }

            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed
            }
        })

        for (i in copyButtons.indices) {
            copyButtons[i].setOnClickListener {

                performHapticFeedback(vibrator, sharedPreferences)
                copyTextToClipboard(contentTextViews[i].text.toString())
            }
        }



        return rootView
    }

    // Function to copy text to clipboard
    private fun copyTextToClipboard(text: String) {
        val clipboardManager =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("Styled Text", text)
        clipboardManager.setPrimaryClip(clipData)

    }
}
