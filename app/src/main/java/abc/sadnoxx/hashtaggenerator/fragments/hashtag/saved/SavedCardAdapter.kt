package abc.sadnoxx.hashtaggenerator.fragments.hashtag.saved

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.Card
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SavedCardAdapter(
    private val savedCards: List<Card>, private val onCopyClickListener: (Int) -> Unit,
    private val onRemoveClickListener: (Int) -> Unit
) :
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
        private val btnCopy: LinearLayout = itemView.findViewById(R.id.btnCopy)
        private val btnRemove: LinearLayout = itemView.findViewById(R.id.btnRemove)
        fun bind(card: Card) {
            savedMainTagText.text = card.mainText
            savedTagTagText.text = itemView.context.getString(card.tags)

            btnCopy.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val tags = savedCards[position].tags
                    onCopyClickListener.invoke(tags)

                }
            }

            btnRemove.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onRemoveClickListener.invoke(position)
                }
            }
        }
    }

}
