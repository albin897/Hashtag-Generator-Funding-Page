package abc.sadnoxx.hashtaggenerator.fragments.hashtags

import abc.sadnoxx.hashtaggenerator.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class HashtagsFragment : Fragment() {

    private lateinit var searchBar: EditText
    private lateinit var cardAdapter: CardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_hashtags, container, false)

        searchBar = rootView.findViewById(R.id.searchBar)
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

        return rootView
    }
}
