package abc.sadnoxx.hashtaggenerator.fragments.hashtags

import abc.sadnoxx.hashtaggenerator.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class HashtagsFragment : Fragment() {

    private lateinit var searchBar: EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_hashtags, container, false)

        searchBar = rootView.findViewById(R.id.searchBar)
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)

        val cardAdapter = CardAdapter(CardDataRepository.cardDataList)
        recyclerView.adapter = cardAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        return rootView
    }
}