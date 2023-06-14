package abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags
import abc.sadnoxx.hashtaggenerator.R
import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

private const val SAVED_CARDS_KEY = "savedCards"
class CardAdapter(private var allCardDataList: List<Card>,private var cardDataList: List<Card>,
                  private val context: Context) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var dataSet: MutableList<Card> = cardDataList.toMutableList()
    private var onCopyClickListener: OnCopyClickListener? = null

    private val savedCards: MutableList<Card> = mutableListOf()
    // Initialize the CardDataManager in the constructor


    interface OnCopyClickListener {
        fun onCopyClick(tagsText1: Card)
    }


    fun setOnCopyClickListener(listener: OnCopyClickListener) {
        onCopyClickListener = listener
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainTextView: TextView = itemView.findViewById(R.id.mainTagText)
        val tagsTextView: TextView = itemView.findViewById(R.id.tagTagText)
        val copyButton: LinearLayout = itemView.findViewById(R.id.btnCopy)
        val saveButton: LinearLayout = itemView.findViewById(R.id.saveBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context)
            .inflate(R.layout.hash_item, parent, false)
        return CardViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cardData = dataSet[position]

        holder.mainTextView.text = cardData.mainText
        holder.tagsTextView.text = holder.itemView.context.getString(cardData.tags)

        holder.copyButton.setOnClickListener {
            onCopyClickListener?.onCopyClick(cardData)
        }



       holder.saveButton.setOnClickListener {
            savedCards.add(cardData)
            saveSavedCards()
            Log.d("TAG", "onSaveClick: $savedCards")
        }
    }

        private fun saveSavedCards() {
            val savedCardsArray = JSONArray()
            for (card in savedCards) {
                val cardJson = JSONObject()
                cardJson.put("mainText", card.mainText)
                cardJson.put("tags", card.tags)
                savedCardsArray.put(cardJson)
            }
            val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
            val editor = sharedPrefs.edit()
            editor.putString(SAVED_CARDS_KEY, savedCardsArray.toString())
            val logger = savedCardsArray.toString()
            Log.d("TAG", "jsonarray: $logger")
            editor.apply()
        }



    override fun getItemCount(): Int {
        return dataSet.size
    }



    fun filterData(query: String) {
        if (query.isEmpty()) {
            dataSet.clear()
            dataSet.addAll(cardDataList)
        } else {

            val matchingHeadings = allCardDataList.filter { cardData ->
                cardData.mainText.contains(query, ignoreCase = true)
            }

            val matchingTags = allCardDataList.filter { cardData ->
                context.getString(cardData.tags).contains(query, ignoreCase = true)
            }

            dataSet.clear()

            val filteredData = (matchingHeadings + matchingTags).distinctBy { cardData ->
                cardData.mainText
            }

            dataSet.addAll(filteredData)
        }
        notifyDataSetChanged()
    }

    }