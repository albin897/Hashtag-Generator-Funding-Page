package abc.sadnoxx.hashtaggenerator.fragments.hashtag.saved

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.Card
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SavedCardAdapter(private val savedCards: List<Card>) :
    RecyclerView.Adapter<SavedCardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.saved_hash_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = savedCards[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return savedCards.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val savedMainTagText: TextView = itemView.findViewById(R.id.SavedmainTagText)
        private val savedTagTagText: TextView = itemView.findViewById(R.id.SavedtagTagText)

        fun bind(card: Card) {
            savedMainTagText.text = card.mainText
            savedTagTagText.text = itemView.context.getString(card.tags)
        }
    }

}
