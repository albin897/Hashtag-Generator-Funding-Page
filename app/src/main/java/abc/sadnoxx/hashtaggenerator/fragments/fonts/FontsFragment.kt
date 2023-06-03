package abc.sadnoxx.hashtaggenerator.fragments.fonts

import abc.sadnoxx.hashtaggenerator.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.TypefaceSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi


class FontsFragment : Fragment() {

//    private lateinit var fontAdapter: MyAdapter
//    private lateinit var editText:TextInputEditText

    private lateinit var mainText: TextView
//    private val fontList: ArrayList<Font> = ArrayList()



    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fonts, container, false)


//        editText = rootView.findViewById(R.id.edit_text1)
        mainText = rootView.findViewById(R.id.mainText)
//
//        val inputText = editText.text.toString()
//
//        // Initialize your fontList with the font names and file paths
//        fontList.add(Font("Roboto Italic", "roboto_italic.ttf"))
//        fontList.add(Font("Monospace", "spacemono_regular.ttf"))
//        fontList.add(Font("Signika", "signikanegative_medium.ttf"))
//
//        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
//        fontAdapter = MyAdapter(fontList,inputText)
//        recyclerView.adapter = fontAdapter
//        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val copyButtonClickListener = View.OnClickListener { view ->
            // Get the text associated with the clicked card
            val cardIndex = view.tag as Int
            val cardText = getTextForCard(cardIndex)

            // Create a SpannableStringBuilder to preserve font information
            val spannableBuilder = SpannableStringBuilder(cardText)

            // Apply the desired font family to the entire text
            val typeface = Typeface.create("spacemono_regular.ttf", Typeface.NORMAL)
            spannableBuilder.setSpan(
                TypefaceSpan(typeface),
                0,
                cardText.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            // Copy the text to the clipboard
            val clipboard =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Copied Text", cardText)
            clipboard.setPrimaryClip(clip)

            // Show a toast message or perform any other desired action
            Toast.makeText(activity, "Text copied", Toast.LENGTH_SHORT).show()
        }


// Find the copy buttons and set the click listener
        rootView.findViewById<ImageView>(R.id.copyButton1).apply {
            tag = 0 // Set the tag to identify the card index
            setOnClickListener(copyButtonClickListener)
        }
//
//        findViewById<Button>(R.id.copyButton2).apply {
//            tag = 1 // Set the tag to identify the card index
//            setOnClickListener(copyButtonClickListener)
//        }
//
//        findViewById<Button>(R.id.copyButton3).apply {
//            tag = 2 // Set the tag to identify the card index
//            setOnClickListener(copyButtonClickListener)
//        }


        return rootView
    }


    private fun getTextForCard(index: Int): String {
        // Retrieve the text for the given card index
        // Replace this with your own logic to get the text for each card
        return when (index) {
            0 -> mainText.text.toString()
//                1 -> "Text for Card 2"
//                2 -> "Text for Card 3"
            else -> ""
        }
    }
}
