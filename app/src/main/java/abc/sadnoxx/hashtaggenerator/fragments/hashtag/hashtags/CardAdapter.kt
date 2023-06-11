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

class CardAdapter(private var cardDataList: List<Card>,
                  private val context: Context) :
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
            val matchingHeadings = cardDataList.filter { cardData ->
                cardData.mainText.contains(query, ignoreCase = true)
            }

            val matchingTags = cardDataList.filter { cardData ->
                context.getString(cardData.tags).contains(query, ignoreCase = true)
            }

            dataSet.clear()
            dataSet.addAll(matchingHeadings)
            dataSet.addAll(matchingTags)
        }
        notifyDataSetChanged()
    }
}