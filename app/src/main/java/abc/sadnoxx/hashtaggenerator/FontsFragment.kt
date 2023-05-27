package abc.sadnoxx.hashtaggenerator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText


class FontsFragment : Fragment() {

    private lateinit var fontAdapter: MyAdapter
private lateinit var textField: TextInputEditText


    private val fontList: ArrayList<Font> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_fonts, container, false)


        textField = rootView.findViewById(R.id.edit_text1)

        // Initialize your fontList with the font names and file paths
        fontList.add(Font("Roboto Italic", "roboto_italic.ttf"))
        fontList.add(Font("Monospace", "spacemono_regular.ttf"))
        fontList.add(Font("Signika", "signikanegative_medium.ttf"))

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        fontAdapter = MyAdapter(fontList)
        recyclerView.adapter = fontAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())






        textField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is being changed
                val newText = s.toString()
                // Update the text for the first item in your dataset
                fontList[0].text= newText
                // Notify the adapter that the data has changed for that item
                fontAdapter.notifyItemChanged(0)

            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed
            }
        })




        return rootView
    }
}
