package abc.sadnoxx.hashtaggenerator.fragments.tools.route.tophashtags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abc.sadnoxx.hashtaggenerator.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TopHashtags : Fragment(), TopCardAdapter.OnCopyClickListener {


    private lateinit var btnCopyAll: LinearLayout
    private lateinit var topCardAdapter: TopCardAdapter
    private val copiedStringsList: MutableList<String> = mutableListOf()
    private lateinit var copiedTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_top_hashtags, container, false)


        copiedTextView = rootView.findViewById(R.id.tagAllText)


        val top_recyclerView: RecyclerView = rootView.findViewById(R.id.top_recyclerView)
        topCardAdapter = TopCardAdapter(TopCardDataRepository.TopCardDataList)
        topCardAdapter.setOnCopyClickListener(this)
        top_recyclerView.adapter = topCardAdapter
        top_recyclerView.layoutManager = LinearLayoutManager(requireContext())
        btnCopyAll = rootView.findViewById(R.id.btnCopyAll)

        btnCopyAll.setOnClickListener(View.OnClickListener {
            val clipboardManager =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Copied Text", copiedTextView.text)
            clipboardManager.setPrimaryClip(clipData)
        })


        return rootView
    }

    override fun onCopyClick(mainText: String) {
        if (!copiedStringsList.contains(mainText)) {
            copiedStringsList.add(mainText)

            val concatenatedText = copiedStringsList.joinToString(" ")
            copiedTextView.text = concatenatedText
        }
    }


}