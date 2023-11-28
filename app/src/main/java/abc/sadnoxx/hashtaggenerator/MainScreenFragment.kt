package abc.sadnoxx.hashtaggenerator

import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.Card
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.CardDataRepository.cardDataList
import abc.sadnoxx.hashtaggenerator.fragments.tools.route.categories.CategoryDataRepository.allDataListCombined
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Vibrator
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputLayout

class MainScreenFragment : Fragment() {

    lateinit var categories : MaterialCardView
    private lateinit var topHashtags: MaterialCardView
    lateinit var saved: MaterialCardView
    private lateinit var mAdView : AdView
    private lateinit var searchBar: MaterialAutoCompleteTextView
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var searchBarTop: TextInputLayout
    private lateinit var generateHashSearchBtn: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(R.layout.fragment_main_screen, container, false)

        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator



        generateHashSearchBtn = rootView.findViewById(R.id.generateHashSearchBtn)
        searchBarTop = rootView.findViewById(R.id.searchbartop)
        searchBar = rootView.findViewById(R.id.search_bar)
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        categories = rootView.findViewById(R.id.card1)
        topHashtags = rootView.findViewById(R.id.card3)
        saved = rootView.findViewById(R.id.saved)
        categories.setOnClickListener{
            openRouteModifiedActivityWithCategoriesFragment()
        }
        topHashtags.setOnClickListener{
            openRouteModifiedActivityWithTopHashtagsFragment()
        }
        saved.setOnClickListener{
            openRouteModifiedActivityWithSavedHashtagsFragment()
        }
        MobileAds.initialize(requireContext()) {}

            mAdView = rootView.findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)


            mAdView.adListener = object: AdListener() {
                override fun onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                override fun onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }

                override fun onAdFailedToLoad(adError : LoadAdError) {
                    // Code to be executed when an ad request fails.
                }

                override fun onAdImpression() {
                    // Code to be executed when an impression is recorded
                    // for an ad.
                }

                override fun onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                override fun onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }
            }


        val newdata: List<Card> = cardDataList.take(0)
        val suggestionsList = mutableListOf<String>()

        for (card in cardDataList) {
            suggestionsList.add(card.mainText)
        }

//        for (card in allDataListCombined) {
//            suggestionsList.add(card.mainText)
//        }


        for (card in allDataListCombined) {
            if (!suggestionsList.contains(card.mainText)) {
                suggestionsList.add(card.mainText)
            }
        }


        suggestionsList.addAll(listOf(
//add extra names
        ))

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, suggestionsList)

        searchBar.setAdapter(adapter)

        var query: String? = null

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (count == 0) {
//                    popularTags.visibility = View.VISIBLE
//                    recyclerView.visibility = View.GONE
//                    noResultText.visibility = View.GONE
//                } else {
//                    popularTags.visibility = View.GONE
//                }
            }

            override fun afterTextChanged(s: Editable?) {
                query = s.toString().trim()
            }
        })

        searchBarTop.setEndIconOnClickListener {
            HapticUtils.performHapticFeedback(vibrator, sharedPrefs)
            searchBar.setText("")
//            cardAdapter.filterData("")
        }






        searchBar.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Show the keyboard
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(searchBar, 0)
            } else {
                // Hide the keyboard
                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(searchBar.windowToken, 0)
            }
        }


        searchBar.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // Call your function here
//                performSearch()
                val inputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(searchBar.windowToken, 0)
                true
            } else {

                false
            }
        }



        generateHashSearchBtn.setOnClickListener {
            HapticUtils.performHapticFeedback(vibrator, sharedPrefs)
            query?.let { it1 -> performSearch(it1) }
            val inputMethodManager =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(searchBar.windowToken, 0)
        }














        return rootView
    }

    private fun openRouteModifiedActivityWithCategoriesFragment() {
        val intent = Intent(activity, RouteModifiedActivity::class.java)
        intent.putExtra("fragment", "categories")
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
    }

    private fun openRouteModifiedActivityWithTopHashtagsFragment() {
        val intent = Intent(activity, RouteModifiedActivity::class.java)
        intent.putExtra("fragment", "fragment1")
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
    }

    private fun openRouteModifiedActivityWithSavedHashtagsFragment() {
        val intent = Intent(activity, RouteModifiedActivity::class.java)
        intent.putExtra("fragment", "saved")
        startActivity(intent)
        requireActivity().overridePendingTransition(R.anim.slide_up, R.anim.slide_down)
    }


    private fun performSearch(query:String) {
        val queryWithoutHash = query.replace("#", "")
        openModifiedRouteActivityAndHashtagFragmentWithGivenQueryAsArguments(queryWithoutHash)
    }

    private fun openModifiedRouteActivityAndHashtagFragmentWithGivenQueryAsArguments(query: String) {
        val intent = Intent(activity, RouteModifiedActivity::class.java)
        val bundle = Bundle()
        bundle.putString("searchQuery", query)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onPause() {
        super.onPause()
//        cardAdapter.clearSavedCards()
    }

}


