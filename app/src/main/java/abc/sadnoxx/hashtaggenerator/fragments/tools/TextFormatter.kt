package abc.sadnoxx.hashtaggenerator.fragments.tools

import abc.sadnoxx.hashtaggenerator.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText


class TextFormatter : Fragment() {


    private lateinit var edit_text1: TextInputEditText
    private lateinit var toUppercaseTextView: TextView
    private lateinit var toLowerCaseTextView: TextView
    private lateinit var toUpperCaseCopyButton: ImageView
    private lateinit var toLowerCaseCopyButton: ImageView
    private lateinit var summary: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_text_formatter, container, false)


        edit_text1 = rootView.findViewById(R.id.edit_text1)
        toUppercaseTextView = rootView.findViewById(R.id.toUppercaseTextView)
        toLowerCaseTextView = rootView.findViewById(R.id.toLowerCaseTextView)
        toUpperCaseCopyButton = rootView.findViewById(R.id.toUpperCaseCopyButton)
        toLowerCaseCopyButton = rootView.findViewById(R.id.toLowerCaseCopyButton)
        summary = rootView.findViewById(R.id.summary)

        edit_text1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val OrginalString = s.toString()
              val  UppercaseCharacters = s.toString().uppercase()
                val LowercaseCharacters = s.toString().lowercase()
                toUppercaseTextView.text = UppercaseCharacters
                toLowerCaseTextView.text = LowercaseCharacters
                getTextStatistics(OrginalString)
                if (s.isNullOrEmpty()){
                    summary.setText("0 Words \n\n 0 Characters \n\n 0 Characters (without spaces)")

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        toUpperCaseCopyButton.setOnClickListener {
            val textToCopy = toUppercaseTextView.text.toString()
            copyToClipboard(textToCopy)
        }

        toLowerCaseCopyButton.setOnClickListener {
            val textToCopy = toLowerCaseTextView.text.toString()
            copyToClipboard(textToCopy)
        }

        return rootView
    }

private fun copyToClipboard(text: String){
    val clipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    // Create a new ClipData with the text to copy
    val clipData = ClipData.newPlainText("copied_text", text)

    // Set the ClipData to the clipboard
    clipboardManager.setPrimaryClip(clipData)

    // Optionally, you can display a toast to indicate that the text has been copied
    Toast.makeText(activity, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
}

    fun getTextStatistics(passage: String){
        val wordCount = passage.trim().split(Regex("\\s+")).count()
        val characterCountWithSpaces = passage.length
        val characterCountWithoutSpaces = passage.replace("\\s".toRegex(), "").length

        summary.setText("$wordCount Words \n\n $characterCountWithSpaces Characters \n\n $characterCountWithoutSpaces Characters (without spaces)")
    }


}