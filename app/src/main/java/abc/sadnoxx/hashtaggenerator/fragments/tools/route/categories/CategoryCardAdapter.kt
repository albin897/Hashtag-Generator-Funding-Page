package abc.sadnoxx.hashtaggenerator.fragments.tools.route.categories


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.Card
import android.widget.LinearLayout


class CategoryCardAdapter(
    cardDataList: List<Card>,
    private val onCopyClickListener: (Int) -> Unit
) :
    RecyclerView.Adapter<CategoryCardAdapter.ViewHolder>() {

    private var dataSet: MutableList<Card> = cardDataList.toMutableList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hash_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val cardData = dataSet[position]

        holder.mainTagText.text = cardData.mainText
        holder.tagTagText.text = holder.itemView.context.getString(cardData.tags)


        if (cardData.mainText == "20") {
            holder.mainTagText.visibility = View.GONE
        } else {
            holder.mainTagText.visibility = View.VISIBLE
        }

        holder.btnCopy.setOnClickListener {
            onCopyClickListener.invoke(cardData.tags)
        }
    }


    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mainTagText: TextView = itemView.findViewById(R.id.mainTagText)
        val tagTagText: TextView = itemView.findViewById(R.id.tagTagText)
        val btnCopy: LinearLayout = itemView.findViewById(R.id.btnCopy)
        private val saveBtn: LinearLayout = itemView.findViewById(R.id.saveBtn)

        init {
            saveBtn.visibility = View.INVISIBLE
        }

    }
}
