package abc.sadnoxx.hashtaggenerator1.fragments.hashtag.saved

import abc.sadnoxx.hashtaggenerator1.FilterCopiedText
import abc.sadnoxx.hashtaggenerator1.HapticUtils
import abc.sadnoxx.hashtaggenerator1.R
import abc.sadnoxx.hashtaggenerator1.fragments.hashtag.hashtags.Card
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Vibrator
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

private const val SAVED_CARDS_KEY = "savedCards"


class SavedHashtagsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var savedCardAdapter: SavedCardAdapter
    private val savedCards: MutableList<Card> = mutableListOf()
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_saved_hashtags, container, false)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())



        recyclerView = rootView.findViewById(R.id.savedRecyclerView)
        savedCardAdapter = SavedCardAdapter(savedCards, this::copyToClipboard, this::removeCard)
        recyclerView.adapter = savedCardAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        loadSavedCards()




        return rootView
    }

    override fun onResume() {
        super.onResume()
        loadSavedCards()

    }

    private fun loadSavedCards() {
        val savedCardsJson = sharedPreferences.getString(SAVED_CARDS_KEY, null)
        savedCardsJson?.let {
            val savedCardsArray = JSONArray(it)
            for (i in 0 until savedCardsArray.length()) {
                val cardJson = savedCardsArray.getJSONObject(i)
                val tags = cardJson.getInt("tags")
                val mainText = cardJson.getString("mainText")

                // Check if there is already a card with the same mainText
                val isDuplicateCard = savedCards.any { card -> card.mainText == mainText }

                if (!isDuplicateCard) {
                    val card = Card(mainText, tags)
                    savedCards.add(card)
                }
            }
            savedCardAdapter.notifyDataSetChanged()
        }

        // Save the updated savedCards list to SharedPreferences
        val updatedSavedCardsJson = JSONArray(savedCards.map { card ->
            val cardJson = JSONObject()
            cardJson.put("tags", card.tags)
            cardJson.put("mainText", card.mainText)
            cardJson
        }).toString()
        sharedPreferences.edit().putString(SAVED_CARDS_KEY, updatedSavedCardsJson).apply()
    }

    private fun copyToClipboard(text: Int) {
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        HapticUtils.performHapticFeedback(vibrator, sharedPreferences)


        val filterCopiedText = FilterCopiedText()

// Call the sentTheCardIn method with the required parameters
        filterCopiedText.sentTheCardInWithInt(requireContext(), text, resources)
    }


    private fun removeCard(position: Int) {
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        HapticUtils.performHapticFeedback(vibrator, sharedPreferences)
        savedCards.removeAt(position)
        savedCardAdapter.notifyItemRemoved(position)

        // Update the savedCards list in SharedPreferences after removing the card
        val updatedSavedCardsJson = JSONArray(savedCards.map { card ->
            val cardJson = JSONObject()
            cardJson.put("tags", card.tags)
            cardJson.put("mainText", card.mainText)
            cardJson
        }).toString()
        sharedPreferences.edit().putString(SAVED_CARDS_KEY, updatedSavedCardsJson).apply()

    }




}