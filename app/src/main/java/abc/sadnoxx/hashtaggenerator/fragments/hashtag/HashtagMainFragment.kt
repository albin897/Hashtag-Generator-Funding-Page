package abc.sadnoxx.hashtaggenerator.fragments.hashtag

import abc.sadnoxx.hashtaggenerator.R
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

        var isDarkModeEnabled = false

        val toolbar: MaterialToolbar = rootView.findViewById(R.id.topAppBar)
        tabLayout = rootView.findViewById(R.id.tabLayout)
        viewPager2 = rootView.findViewById(R.id.viewPager2)

        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.themeToggle -> {
                    isDarkModeEnabled = !isDarkModeEnabled

                    if (isDarkModeEnabled) {
                        menuItem.setIcon(R.drawable.dark_mode)
                    } else {
                        menuItem.setIcon(R.drawable.light_mode)
                    }

                    true
                }
                R.id.more -> {
                    // Handle more item (inside overflow menu) press
                    true
                }
                else -> false
            }
        }

            adapter = HashtagPageAdapter(childFragmentManager, lifecycle)

            tabLayout.addTab(tabLayout.newTab().setText("Hashtags"))
            tabLayout.addTab(tabLayout.newTab().setText("Saved"))

            viewPager2.adapter = adapter



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