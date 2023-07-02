@file:Suppress("DEPRECATION")

package abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags

import abc.sadnoxx.hashtaggenerator.FilterCopiedText
import abc.sadnoxx.hashtaggenerator.HapticUtils.performHapticFeedback
import abc.sadnoxx.hashtaggenerator.R
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.VibrationEffect
import android.os.Vibrator
import android.preference.PreferenceManager
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

private const val KEY_PLATFORM = "platform"
private const val PLATFORM_INSTAGRAM = 0
private const val PLATFORM_INSTAGRAM_STORIES = 1
private const val PLATFORM_TIKTOK = 2
private const val PLATFORM_TWITTER = 3
private const val PLATFORM_YOUTUBE = 4
private const val PLATFORM_FACEBOOK = 5
private const val PLATFORM_LINKEDIN = 6
private const val PLATFORM_PINTEREST = 7
private const val PLATFORM_SNAPCHAT = 8

private const val KEY_SEPERATOR = "separator"


class HashtagsFragment : Fragment(),
    CardAdapter.OnCopyClickListener {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var searchBar: TextInputEditText
    private lateinit var cardAdapter: CardAdapter
    private lateinit var searchBarTop: TextInputLayout
    private lateinit var generateHashSearchBtn: Button
    private lateinit var platformImage: ImageView
    private lateinit var platformName: TextView
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var selectPlatformTab: LinearLayout
    private lateinit var popularTags: LinearLayout
    private lateinit var love: LinearLayout
    private lateinit var noResultText: TextView
    private lateinit var photography: LinearLayout
    private lateinit var party: LinearLayout
    private lateinit var lonely: LinearLayout
    private lateinit var summer: LinearLayout
    private lateinit var pubg: LinearLayout
    private lateinit var birthday: LinearLayout
    private lateinit var snow: LinearLayout
    private lateinit var flights: LinearLayout
    private lateinit var engagement: LinearLayout
    private lateinit var chocolate: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_hashtags, container, false)

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        searchBarTop = rootView.findViewById(R.id.searchbartop)
        generateHashSearchBtn = rootView.findViewById(R.id.generateHashSearchBtn)
        platformName = rootView.findViewById(R.id.platformName)
        platformImage = rootView.findViewById(R.id.platformImage)
        selectPlatformTab = rootView.findViewById(R.id.selectPlatformTab)
        noResultText = rootView.findViewById(R.id.noResultText)
        popularTags = rootView.findViewById(R.id.popularTags)
        love = rootView.findViewById(R.id.love)
        chocolate = rootView.findViewById(R.id.chocolate)
        engagement = rootView.findViewById(R.id.engagement)
        flights = rootView.findViewById(R.id.flights)
        snow = rootView.findViewById(R.id.snow)
        birthday = rootView.findViewById(R.id.birthday)
        pubg = rootView.findViewById(R.id.pubg)
        summer = rootView.findViewById(R.id.summer)
        lonely = rootView.findViewById(R.id.lonely)
        party = rootView.findViewById(R.id.party)
        photography = rootView.findViewById(R.id.photography)

        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val savedPlatform = sharedPrefs.getInt(KEY_PLATFORM, PLATFORM_INSTAGRAM)

        setPlatformName(savedPlatform)

        sharedViewModel.dataChangedLiveData.observe(viewLifecycleOwner) {
            // Data has changed in the bottom sheet fragment
            // Perform appropriate changes in this fragment
            val savedPlatform = sharedPrefs.getInt(KEY_PLATFORM, PLATFORM_INSTAGRAM)

            setPlatformName(savedPlatform)
        }

        selectPlatformTab.setOnClickListener {
            val bottomSheetFragment = MyBottomSheetDialogFragment()

            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
        }


        val newdata: List<Card> = CardDataRepository.cardDataList.take(0)


        searchBar = rootView.findViewById(R.id.search_bar)
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerView)

        cardAdapter = CardAdapter(CardDataRepository.cardDataList, newdata, requireContext())



        cardAdapter.setOnCopyClickListener(this)
        recyclerView.adapter = cardAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        var query: String? = null

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (count == 0) {
                    popularTags.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    noResultText.visibility = View.GONE
                } else {
                    popularTags.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {
                query = s.toString().trim()
            }
        })

        searchBarTop.setEndIconOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            searchBar.setText("")
            cardAdapter.filterData("")
        }



        love.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("love")
            recyclerView.visibility = View.VISIBLE
        }
        chocolate.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("chocolate")
            recyclerView.visibility = View.VISIBLE
        }
        engagement.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("engagement")
            recyclerView.visibility = View.VISIBLE
        }
        flights.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("flights")
            recyclerView.visibility = View.VISIBLE
        }
        snow.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("snow")
            recyclerView.visibility = View.VISIBLE
        }
        birthday.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("birthday")
            recyclerView.visibility = View.VISIBLE
        }
        pubg.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("pubg")
            recyclerView.visibility = View.VISIBLE
        }
        summer.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("summer")
            recyclerView.visibility = View.VISIBLE
        }
        lonely.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("lonely")
            recyclerView.visibility = View.VISIBLE
        }
        party.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("party")
            recyclerView.visibility = View.VISIBLE
        }
        photography.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            setTagClickListener("photography")
            recyclerView.visibility = View.VISIBLE
        }



        fun performSearch() {
            recyclerView.visibility = View.VISIBLE
            val queryWithoutHash = query?.replace("#", "")
            queryWithoutHash?.let { it1 -> cardAdapter.filterData(it1) }
                ?.observe(viewLifecycleOwner) { isDataFiltered ->
                    val viewToToggle = noResultText

                    if (!isDataFiltered) {
                        val handler = Handler()
                        handler.postDelayed({
                            viewToToggle.visibility = View.VISIBLE
                        }, 500) // Delay of 0.5 second (500 milliseconds)
                    } else {
                        viewToToggle.visibility = View.GONE
                    }
                }
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
                performSearch()
                val inputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(searchBar.windowToken, 0)
                true
            } else {

                false
            }
        }




        generateHashSearchBtn.setOnClickListener {
            performHapticFeedback(vibrator, sharedPrefs)
            performSearch()
            val inputMethodManager =
                requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(searchBar.windowToken, 0)
        }




        return rootView
    }

    private fun setPlatformName(platform: Int) {
        val platformNameResId = when (platform) {
            PLATFORM_INSTAGRAM -> R.string.instagram
            PLATFORM_INSTAGRAM_STORIES -> R.string.instagram_stories
            PLATFORM_TIKTOK -> R.string.tikTok
            PLATFORM_TWITTER -> R.string.twitter
            PLATFORM_YOUTUBE -> R.string.youTube
            PLATFORM_FACEBOOK -> R.string.facebook
            PLATFORM_LINKEDIN -> R.string.linkedIn
            PLATFORM_PINTEREST -> R.string.pinterest
            PLATFORM_SNAPCHAT -> R.string.snapchat
            else -> R.string.instagram
        }
        platformName.setText(platformNameResId)


        val platformImageResId = when (platform) {
            PLATFORM_INSTAGRAM -> R.drawable.ig
            PLATFORM_INSTAGRAM_STORIES -> R.drawable.ig
            PLATFORM_TIKTOK -> R.drawable.tiktok
            PLATFORM_TWITTER -> R.drawable.twitter
            PLATFORM_YOUTUBE -> R.drawable.youtube
            PLATFORM_FACEBOOK -> R.drawable.facebook
            PLATFORM_LINKEDIN -> R.drawable.linkedin
            PLATFORM_PINTEREST -> R.drawable.pinterest
            PLATFORM_SNAPCHAT -> R.drawable.snapchat
            else -> R.drawable.ig
        }
        platformImage.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                platformImageResId
            )
        )
    }


    override fun onCopyClick(tagsText1: Card) {
// Create an instance of FilterCopiedText
        val filterCopiedText = FilterCopiedText()

// Call the sentTheCardIn method with the required parameters
        filterCopiedText.sentTheCardIn(requireContext(), tagsText1, resources)

    }



    private fun setTagClickListener(tag: String) {
        searchBar.setText(tag)
        cardAdapter.filterData(tag)
    }


    override fun onPause() {
        super.onPause()
        cardAdapter.clearSavedCards()
    }

}


