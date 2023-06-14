package abc.sadnoxx.hashtaggenerator.fragments.hashtag

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.MyBottomSheetDialogFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout

class HashtagMainFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: HashtagPageAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_hashtag_main, container, false)



        val toolbar: MaterialToolbar = rootView.findViewById(R.id.topAppBar)
        tabLayout = rootView.findViewById(R.id.tabLayout)
        viewPager2 = rootView.findViewById(R.id.viewPager2)



            adapter = HashtagPageAdapter(childFragmentManager, lifecycle)

            tabLayout.addTab(tabLayout.newTab().setText(R.string.Hashtags))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.Saved))

            viewPager2.adapter = adapter


        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.filterResult -> {
                    val bottomSheetFragment = MyBottomSheetDialogFragment()

                    bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
                    true
                }

                else -> false
            }
        }

            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab != null) {
                        viewPager2.currentItem = tab.position
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

            viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    tabLayout.selectTab(tabLayout.getTabAt(position))
                }
            })


            return rootView
        }

    }