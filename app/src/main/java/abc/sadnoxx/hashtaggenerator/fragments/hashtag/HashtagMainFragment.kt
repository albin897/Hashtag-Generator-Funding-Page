package abc.sadnoxx.hashtaggenerator.fragments.hashtag

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.MyBottomSheetDialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.tabs.TabLayout

class HashtagMainFragment : Fragment() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: HashtagPageAdapter
    private lateinit var floating_action_button0: ExtendedFloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_hashtag_main, container, false)



        val toolbar: MaterialToolbar = rootView.findViewById(R.id.topAppBar)
        tabLayout = rootView.findViewById(R.id.tabLayout)
        viewPager2 = rootView.findViewById(R.id.viewPager2)
        floating_action_button0 = rootView.findViewById(R.id.floating_action_button0)


            adapter = HashtagPageAdapter(childFragmentManager, lifecycle)

            tabLayout.addTab(tabLayout.newTab().setText(R.string.Hashtag))
            tabLayout.addTab(tabLayout.newTab().setText(R.string.Saved))

            viewPager2.adapter = adapter


        floating_action_button0.setOnClickListener{
            val bottomSheetFragment = MyBottomSheetDialogFragment()
            bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
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