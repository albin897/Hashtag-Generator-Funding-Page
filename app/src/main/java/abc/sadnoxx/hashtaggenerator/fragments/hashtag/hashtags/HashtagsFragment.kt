package abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags

import abc.sadnoxx.hashtaggenerator.MyBottomSheetDialogFragment
import abc.sadnoxx.hashtaggenerator.R
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class HashtagsFragment : Fragment() {

    private lateinit var searchBar: TextInputEditText
    private lateinit var cardAdapter: CardAdapter
    private lateinit var searchBarTop: TextInputLayout
    private lateinit var fab: ExtendedFloatingActionButton
    private lateinit var fab0: ExtendedFloatingActionButton



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
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
