package abc.sadnoxx.hashtaggenerator.fragments.tools.route.tophashtags

import abc.sadnoxx.hashtaggenerator.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback


class TopHashtags : Fragment(), TopCardAdapter.OnCopyClickListener {


    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var bottomSheet: LinearLayout
    private lateinit var btnCopyAll: LinearLayout
    private lateinit var btnRemoveOne: LinearLayout
    private lateinit var btnRemoveAll: LinearLayout
    private lateinit var topCardAdapter: TopCardAdapter
    private val copiedStringsList: MutableList<String> = mutableListOf()
    private lateinit var copiedTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_top_hashtags, container, false)

        loadInterAd()
        copiedTextView = rootView.findViewById(R.id.tagAllText)


        val topRecyclerView: RecyclerView = rootView.findViewById(R.id.top_recyclerView)
        topCardAdapter = TopCardAdapter(TopCardDataRepository.TopCardDataList)
        topCardAdapter.setOnCopyClickListener(this)
        topRecyclerView.adapter = topCardAdapter
        topRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        btnCopyAll = rootView.findViewById(R.id.btnCopyAll)
        btnRemoveOne = rootView.findViewById(R.id.btnRemoveOne)
        btnRemoveAll = rootView.findViewById(R.id.btnRemoveAll)
        bottomSheet = rootView.findViewById(R.id.bottomSheet)

        btnCopyAll.setOnClickListener {
            val clipboardManager =
                activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            if(mInterstitialAd != null){

                mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback(){
                    override fun onAdDismissedFullScreenContent() {
                        super.onAdDismissedFullScreenContent()
            val clipData = ClipData.newPlainText("Copied Text", copiedTextView.text)
            clipboardManager.setPrimaryClip(clipData)
                    }
                }
                mInterstitialAd?.show(requireActivity())
            }else{
                val clipData = ClipData.newPlainText("Copied Text", copiedTextView.text)
                clipboardManager.setPrimaryClip(clipData)
            
            }
        }

        btnRemoveOne.setOnClickListener {
            if (copiedStringsList.isNotEmpty()) {
                copiedStringsList.removeAt(copiedStringsList.size - 1)
                val concatenatedText = copiedStringsList.joinToString(" ")
                copiedTextView.text = concatenatedText

                if (copiedStringsList.isEmpty()) {
                    bottomSheet.visibility = View.GONE
                }
            } else {
                // Handle the case when the list is empty
                bottomSheet.visibility = View.GONE
            }
        }


        btnRemoveAll.setOnClickListener {
            copiedStringsList.clear()
            copiedTextView.text = ""

            bottomSheet.visibility = View.GONE

        }

        return rootView
    }

    private fun loadInterAd() {

        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(requireContext(),"ca-app-pub-5904433074528629/6490304035", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {

                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {

                mInterstitialAd = interstitialAd
            }
        }

        )
    }


    override fun onCopyClick(mainText: String) {
        bottomSheet.visibility = View.VISIBLE
        if (!copiedStringsList.contains(mainText)) {
            copiedStringsList.add(mainText)

            val concatenatedText = copiedStringsList.joinToString(" ")
            copiedTextView.text = concatenatedText
        }else{
            Toast.makeText(requireContext(),"Already Added",Toast.LENGTH_SHORT).show()
        }
    }


}