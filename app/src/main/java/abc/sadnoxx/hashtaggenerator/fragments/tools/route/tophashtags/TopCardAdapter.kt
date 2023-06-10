package abc.sadnoxx.hashtaggenerator.fragments.tools.route.tophashtags

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import abc.sadnoxx.hashtaggenerator.R
import android.widget.LinearLayout


class TopCardAdapter(private val topCards: List<TopCard>) :
    RecyclerView.Adapter<TopCardAdapter.ViewHolder>() {

    private var onCopyClickListener: OnCopyClickListener? = null

    interface OnCopyClickListener {
        fun onCopyClick(mainText: String)

    }

    fun setOnCopyClickListener(listener: OnCopyClickListener) {
        onCopyClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_hash_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topCard = topCards[position]
        holder.tagTagText.text = topCard.mainText
        val likesText = "(${topCard.likes})"
        holder.tagTagTextNumber.text = likesText



        holder.btnCopy.setOnClickListener {
            onCopyClickListener?.onCopyClick(topCard.mainText)
        }
    }


    override fun getItemCount(): Int {
        return topCards.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tagTagText: TextView = itemView.findViewById(R.id.tagTagText)
        val tagTagTextNumber: TextView = itemView.findViewById(R.id.tagTagTextNumber)
            val btnCopy: LinearLayout = itemView.findViewById(R.id.btnCopy)
    }
}
