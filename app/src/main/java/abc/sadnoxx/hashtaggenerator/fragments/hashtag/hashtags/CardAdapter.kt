package abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags
import abc.sadnoxx.hashtaggenerator.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private var cardDataList: List<Card>) :
    RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    private var dataSet: MutableList<Card> = cardDataList.toMutableList()
    private var onSaveClickListener: OnSaveClickListener? = null

    private var onCopyClickListener: OnCopyClickListener? = null

    interface OnSaveClickListener {
        fun onSaveClick(card: Card)
    }


    interface OnCopyClickListener {
        fun onCopyClick(tagsText1: Card)

    }

    fun setOnSaveClickListener(listener: OnSaveClickListener) {
        onSaveClickListener = listener
    }

    fun setOnCopyClickListener(listener: OnCopyClickListener) {
        onCopyClickListener = listener
    }

    class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define references to the views in the card item layout
        val mainTextView: TextView = itemView.findViewById(R.id.mainTagText)
        val tagsTextView: TextView = itemView.findViewById(R.id.tagTagText)
        val copyButton: LinearLayout = itemView.findViewById(R.id.btnCopy)
        val saveButton: LinearLayout = itemView.findViewById(R.id.saveBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.hash_item, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val cardData = dataSet[position]

        // Bind the data to the views in the card item
        holder.mainTextView.text = cardData.mainText
        holder.tagsTextView.text = cardData.tags.joinToString(" ")

//        holder.copyButton.setOnClickListener {
//            val tagsText = cardData.tags.joinToString(" ")
//
//            // Copy tags text to clipboard
//            val clipboardManager =
//                holder.itemView.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            val clipData = ClipData.newPlainText("Tags", tagsText)
//            clipboardManager.setPrimaryClip(clipData)
//
//            // Show a toast message indicating that the text has been copied
//            Toast.makeText(holder.itemView.context, "Tags copied", Toast.LENGTH_SHORT).show()
//        }

        holder.copyButton.setOnClickListener {
//            val tagsText = cardData.tags.joinToString(" ")
            onCopyClickListener?.onCopyClick(cardData)
        }

        holder.saveButton.setOnClickListener {
            onSaveClickListener?.onSaveClick(cardData)
        }
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
