package abc.sadnoxx.hashtaggenerator1

import abc.sadnoxx.hashtaggenerator1.fragments.hashtag.hashtags.Card
import abc.sadnoxx.hashtaggenerator1.fragments.hashtag.hashtags.CardAdapter
import abc.sadnoxx.hashtaggenerator1.fragments.hashtag.hashtags.CardDataRepository
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class NewHashtagsFragment : Fragment(),
    CardAdapter.OnCopyClickListener  {


    private lateinit var cardAdapter: CardAdapter

    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var queryText : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_new_hashtags, container, false)

        val newdata: List<Card> = CardDataRepository.cardDataList.take(0)
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val searchQuery = arguments?.getString("searchQuery")
        queryText = rootView.findViewById(R.id.queryText)
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)
        cardAdapter = CardAdapter(CardDataRepository.cardDataList, newdata, requireContext())
        queryText.text = "Showing results for $searchQuery"
        cardAdapter.setOnCopyClickListener(this)
        recyclerView.adapter = cardAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        if (searchQuery != null) {
            cardAdapter.filterData(searchQuery)
        }


        return rootView
    }

    override fun onCopyClick(tagsText1: Card) {
        val filterCopiedText = FilterCopiedText()

        filterCopiedText.sentTheCardIn(requireContext(), tagsText1, resources)
    }

//    override fun onPause() {
//        super.onPause()
//        cardAdapter.clearSavedCards()
//    }

}