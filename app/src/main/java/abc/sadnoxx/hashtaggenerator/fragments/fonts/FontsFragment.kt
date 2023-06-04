package abc.sadnoxx.hashtaggenerator.fragments.fonts

import abc.sadnoxx.hashtaggenerator.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fonts, container, false)


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


// Add a text change listener to the inputTextBox
        inputTextBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val styledText1 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.MATHEMATICAL_BOLD_SCRIPT
                )
                val styledText2 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.SANS
                )
                val styledText3 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.SANS_BOLD
                )
                val styledText4 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.SANS_ITALIC
                )
                val styledText5 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.SANS_BOLD_ITALIC
                )
                val styledText6 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.MONOSPACE
                )
                val styledText7 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.FRAKTUR
                )
                val styledText8 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.BOLD_FRAKTUR
                )
                val styledText9 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.CIRCLED
                )
                val styledText10 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.CIRCLED_NEGATIVE
                )
                val styledText11 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.SUBSCRIPT
                )
                val styledText12 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.SUPERSCRIPT
                )
                val styledText13 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.SCRIPT
                )
                val styledText14 = StyledTextConverter.convertToStyledText(
                    s.toString(),
                    StyledTextConverter.Companion.StyleSet.DOUBLE_STRUCK
                )




                contentTxt1.text = styledText1 // Update the contentTxt with the styled text
                contentTxt2.text = styledText2
                contentTxt3.text = styledText3
                contentTxt4.text = styledText4
                contentTxt5.text = styledText5
                contentTxt6.text = styledText6
                contentTxt7.text = styledText7
                contentTxt8.text = styledText8
                contentTxt9.text = styledText9
                contentTxt10.text = styledText10
                contentTxt11.text = styledText11
                contentTxt12.text = styledText12
                contentTxt13.text = styledText13
                contentTxt14.text = styledText14
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed
            }
        })

        // Add click listeners to the copy buttons
        copyButton1.setOnClickListener {
            copyTextToClipboard(contentTxt1.text.toString())
        }

        // Add click listeners to the copy buttons
        copyButton2.setOnClickListener {
            copyTextToClipboard(contentTxt2.text.toString())
        }

        copyButton3.setOnClickListener {
            copyTextToClipboard(contentTxt3.text.toString())
        }

        copyButton4.setOnClickListener {
            copyTextToClipboard(contentTxt4.text.toString())
        }

        copyButton5.setOnClickListener {
            copyTextToClipboard(contentTxt5.text.toString())
        }

        copyButton6.setOnClickListener {
            copyTextToClipboard(contentTxt6.text.toString())
        }

        copyButton7.setOnClickListener {
            copyTextToClipboard(contentTxt7.text.toString())
        }

        copyButton8.setOnClickListener {
            copyTextToClipboard(contentTxt8.text.toString())
        }

        copyButton9.setOnClickListener {
            copyTextToClipboard(contentTxt9.text.toString())
        }

        copyButton10.setOnClickListener {
            copyTextToClipboard(contentTxt10.text.toString())
        }

        copyButton11.setOnClickListener {
            copyTextToClipboard(contentTxt11.text.toString())
        }

        copyButton12.setOnClickListener {
            copyTextToClipboard(contentTxt12.text.toString())
        }

        copyButton13.setOnClickListener {
            copyTextToClipboard(contentTxt13.text.toString())
        }

        copyButton14.setOnClickListener {
            copyTextToClipboard(contentTxt14.text.toString())
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
