package abc.sadnoxx.hashtaggenerator.fragments.hashtag.saved

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.Card
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.CardAdapter
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.CardDataRepository
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SavedHashtagsFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var savedCardAdapter: SavedCardAdapter
    private val savedCards: MutableList<Card> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_saved_hashtags, container, false)

        recyclerView = rootView.findViewById(R.id.SavedrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        Log.d("TAGCHECKING", "onSaveClickinsaved: $savedCards ")
        savedCardAdapter = SavedCardAdapter(savedCards)
        recyclerView.adapter = savedCardAdapter



        return rootView

    }



}