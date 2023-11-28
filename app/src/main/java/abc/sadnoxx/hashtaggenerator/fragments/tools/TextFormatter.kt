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
    private lateinit var wordsCnt: TextView
    private lateinit var CharectersCnt: TextView
    private lateinit var CharecWithoutSpaceCnt: TextView


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
        wordsCnt = rootView.findViewById(R.id.wordsCnt)
        CharectersCnt = rootView.findViewById(R.id.CharectersCnt)
        CharecWithoutSpaceCnt = rootView.findViewById(R.id.CharecWithoutSpaceCnt)

        try {
            edit_text1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val orginalString = s.toString()
              val  uppercaseCharacters = s.toString().uppercase()
                val lowercaseCharacters = s.toString().lowercase()
                toUppercaseTextView.text = uppercaseCharacters
                toLowerCaseTextView.text = lowercaseCharacters
                getTextStatistics(orginalString)
                if (s.isNullOrEmpty()){
                    wordsCnt.text = "0"
                    CharectersCnt.text = "0"
                    CharecWithoutSpaceCnt.text = "0"

                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })}catch ( e: Exception ){
            Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
        }

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

        wordsCnt.text = "$wordCount"
        CharectersCnt.text = "$characterCountWithSpaces"
        CharecWithoutSpaceCnt.text = "$characterCountWithoutSpaces"
    }


}