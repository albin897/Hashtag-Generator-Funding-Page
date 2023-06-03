package abc.sadnoxx.hashtaggenerator.fragments.hashtags

import abc.sadnoxx.hashtaggenerator.R
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class HashtagsFragment : Fragment() {

    private lateinit var searchBar: TextInputEditText
    private lateinit var cardAdapter: CardAdapter
    private lateinit var searchBarTop: TextInputLayout


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_hashtags, container, false)


        searchBarTop = rootView.findViewById(R.id.searchbartop)
        val topAppBar: MaterialToolbar = rootView.findViewById(R.id.topAppBar)

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.searchItem -> {
                    if (searchBarTop.visibility == View.VISIBLE) {
                        searchBarTop.visibility = View.GONE
                        menuItem.setIcon(R.drawable.zoom_in) // Set the search item icon when searchBarTop is hidden
                    } else {
                        searchBarTop.visibility = View.VISIBLE
                        menuItem.setIcon(R.drawable.zoom_out) // Set the close item icon when searchBarTop is visible
                    }
                    true
                }

                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }

                else -> false
            }
        }







        searchBar = rootView.findViewById(R.id.search_bar)
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)

        cardAdapter = CardAdapter(CardDataRepository.cardDataList)
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

}
