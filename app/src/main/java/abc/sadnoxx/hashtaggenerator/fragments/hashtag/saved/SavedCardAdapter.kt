package abc.sadnoxx.hashtaggenerator.fragments.hashtag.saved

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.Card
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.CardAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SavedCardAdapter(private val savedCards: List<Card>) :
    RecyclerView.Adapter<SavedCardAdapter.SavedCardViewHolder>() {

    class SavedCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val SavedmainTagText: TextView = itemView.findViewById(R.id.SavedmainTagText)
        val SavedtagTagText: TextView = itemView.findViewById(R.id.SavedtagTagText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedCardViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.saved_hash_item, parent, false)
        return SavedCardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SavedCardViewHolder, position: Int) {
        val card = savedCards[position]
        holder.SavedmainTagText.text = card.mainText
        holder.SavedtagTagText.text = card.tags.joinToString(" ")
    }

    override fun getItemCount(): Int {
        return savedCards.size
    }
}
