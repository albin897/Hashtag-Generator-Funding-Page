package abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.saved.SavedCardAdapter
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.text.Editable
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


class HashtagsFragment : Fragment(), CardAdapter.OnSaveClickListener, CardAdapter.OnCopyClickListener  {

    private lateinit var searchBar: TextInputEditText
    private lateinit var cardAdapter: CardAdapter
    private lateinit var searchBarTop: TextInputLayout
    private lateinit var fab: ExtendedFloatingActionButton
    private lateinit var fab0: ExtendedFloatingActionButton
    private var savedCardAdapter: SavedCardAdapter? = null

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

        cardAdapter = CardAdapter(CardDataRepository.cardDataList)
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
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(searchBar, 0)
            } else {
                // Hide the keyboard
                val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchBar.windowToken, 0)
            }
        }

        return rootView
    }

    override fun onSaveClick(card: Card) {
        savedCards.add(card)
        Log.d("TAGCHECKING", "onSaveClick: $savedCards ")
        savedCardAdapter?.notifyDataSetChanged()

    }

    override fun onCopyClick(tagsText: String) {

            // Copy tags text to clipboard
            val clipboardManager =
                requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Tags", tagsText)
            clipboardManager.setPrimaryClip(clipData)

            // Show a toast message indicating that the text has been copied
            Toast.makeText(requireContext(), "Tags copied", Toast.LENGTH_SHORT).show()
        }



    }


