package abc.sadnoxx.hashtaggenerator1.fragments.tools.route.categories

import abc.sadnoxx.hashtaggenerator1.FilterCopiedText
import abc.sadnoxx.hashtaggenerator1.HapticUtils
import abc.sadnoxx.hashtaggenerator1.R
import abc.sadnoxx.hashtaggenerator1.fragments.hashtag.hashtags.CardDataRepository
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Vibrator
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesFragment : Fragment() {

    private lateinit var categoryCardAdapter: CategoryCardAdapter

//    private var mInterstitialAd: InterstitialAd? = null
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_categories, container, false)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)

//        loadInterAd()
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())


        val categoryMap = mapOf(
            "likes" to CategoryDataRepository.likesDataList,
            "reels" to CategoryDataRepository.reelsDataList,
            "followers" to CategoryDataRepository.followersDataList,
            "popular" to CardDataRepository.cardDataList.take(20),
            "photography" to CategoryDataRepository.photographyDataList,
            "gaming" to CategoryDataRepository.gamingDataList,
            "brands" to CategoryDataRepository.brandsDataList,
            "moods" to CategoryDataRepository.moodsDataList,
            "seasonal" to CategoryDataRepository.seasonalDataList,
            "holidays" to CategoryDataRepository.holidaysDataList,
            "parenting" to CategoryDataRepository.parentingDataList,
            "work" to CategoryDataRepository.workDataList,
            "electronics" to CategoryDataRepository.electronicsDataList,
            "weather" to CategoryDataRepository.weatherDataList,
            "food" to CategoryDataRepository.foodDataList,
            "travel" to CategoryDataRepository.travelDataList,
            "entertainment" to CategoryDataRepository.entertainmentDataList,
            "party" to CategoryDataRepository.partyDataList,
            "transportation" to CategoryDataRepository.transportationDataList,
            "nature" to CategoryDataRepository.natureDataList,
            "makeup" to CategoryDataRepository.makeupDataList,
            "fashion" to CategoryDataRepository.fashionDataList,
            "weekdays" to CategoryDataRepository.weekdaysDataList,
            "sports" to CategoryDataRepository.sportsDataList
        )

        val categoryName = arguments?.getString("category")

        if (categoryName != null && categoryMap.containsKey(categoryName)) {
            val categoryDataList = categoryMap[categoryName]
            categoryCardAdapter = categoryDataList?.let { CategoryCardAdapter(it,this::copyToClipboard) }!!
            recyclerView.adapter = categoryCardAdapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        return rootView

    }

//    private fun loadInterAd() {
//
//        var adRequest = AdRequest.Builder().build()
//
//        InterstitialAd.load(requireContext(),"ca-app-pub-5904433074528629/6490304035", adRequest, object : InterstitialAdLoadCallback() {
//            override fun onAdFailedToLoad(adError: LoadAdError) {
//
//                mInterstitialAd = null
//            }
//
//            override fun onAdLoaded(interstitialAd: InterstitialAd) {
//
//                mInterstitialAd = interstitialAd
//            }
//        }
//
//        )
//    }

    private fun copyToClipboard(text: Int) {
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        HapticUtils.performHapticFeedback(vibrator, sharedPreferences)


        val filterCopiedText = FilterCopiedText()

            filterCopiedText.sentTheCardInWithInt(requireContext(), text, resources)

    }

}