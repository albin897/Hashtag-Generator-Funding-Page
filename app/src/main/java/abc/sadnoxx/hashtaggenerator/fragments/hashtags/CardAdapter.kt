package abc.sadnoxx.hashtaggenerator.fragments.hashtags

import abc.sadnoxx.hashtaggenerator.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private var cardDataList: List<Card>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var dataSet: MutableList<Card> = cardDataList.toMutableList()

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define references to the views in the card item layout
        val mainTextView: TextView = itemView.findViewById(R.id.mainTagText)
        val tagsTextView: TextView = itemView.findViewById(R.id.tagTagText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.hash_item, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cardData = dataSet[position]

        // Bind the data to the views in the card item
        holder.mainTextView.text = cardData.mainText
        holder.tagsTextView.text = cardData.tags.joinToString(" ")
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun filterData(query: String) {
        if (query.isEmpty()) {
            dataSet.clear()
            dataSet.addAll(cardDataList)
        } else {
            dataSet.clear()
            dataSet.addAll(cardDataList.filter { cardData ->
                cardData.mainText.contains(query, ignoreCase = true) ||
                        cardData.tags.any { tag -> tag.contains(query, ignoreCase = true) }
            })
        }
        notifyDataSetChanged()
    }
}
