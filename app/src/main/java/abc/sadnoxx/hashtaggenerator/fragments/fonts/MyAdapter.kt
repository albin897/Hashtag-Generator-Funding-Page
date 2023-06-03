package abc.sadnoxx.hashtaggenerator.fragments.fonts

import abc.sadnoxx.hashtaggenerator.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val fontList: ArrayList<Font>, private val sampleText: String) :
    RecyclerView.Adapter<MyAdapter.FontViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FontViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FontViewHolder(itemView)
    }

    override fun getItemCount(): Int {

        return fontList.size
    }



    override fun onBindViewHolder(holder: FontViewHolder, position: Int) {
        val currentFont = fontList[position]

        holder.supText.text = currentFont.name
        holder.mainText.text = sampleText

        // Set font family
        val typeface = ResourcesCompat.getFont(holder.itemView.context, getResourceId(currentFont.fontFile))
        holder.mainText.typeface = typeface
    }

    private fun getResourceId(fontFileName: String): Int {
        return when (fontFileName) {
            "roboto_italic.ttf" -> R.font.roboto_italic
            "spacemono_regular.ttf" -> R.font.spacemono_regular
            "signikanegative_medium.ttf" -> R.font.signikanegative_medium
            // Add more font files here if needed
            else -> R.font.roboto_italic // Replace with your default font resource ID
        }
    }






    class FontViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val supText: TextView = itemView.findViewById(R.id.supText)
        val mainText: TextView = itemView.findViewById(R.id.mainText)
    }




}