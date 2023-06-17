package abc.sadnoxx.hashtaggenerator.fragments.tools.route.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.CardAdapter
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.CardDataRepository
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoriesFragment : Fragment() {

    private lateinit var categoryCardAdapter: CategoryCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_categories, container, false)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)




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
    private fun copyToClipboard(text: Int) {
        val textString = resources.getString(text)
        val clipboardManager = requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", textString)
        clipboardManager.setPrimaryClip(clip)
        Toast.makeText(requireContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }

}