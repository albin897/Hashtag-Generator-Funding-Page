package abc.sadnoxx.hashtaggenerator

import abc.sadnoxx.hashtaggenerator.fragments.fonts.FontsFragment
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.HashtagMainFragment
import abc.sadnoxx.hashtaggenerator.fragments.settings.SettingsFragment
import abc.sadnoxx.hashtaggenerator.fragments.tools.ToolsFragment
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottom_navigation)

        pagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

        bottomNavigationView.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.item_2 -> {
                    viewPager.currentItem = 1
                    true
                }
                R.id.item_3 -> {
                    viewPager.currentItem = 2
                    true
                }
                R.id.item_4 -> {
                    viewPager.currentItem = 3
                    true
                }
                else -> false
            }
        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Not needed
            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Not needed
            }
        })

        // Set initial selection
        viewPager.currentItem = 1

        // for setting color to the status and nav bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = ContextCompat.getColor(this, R.color.material_navbar)
            } else {
                window.javaClass.getDeclaredMethod("setStatusBarColor", Int::class.java)
                    .invoke(window, ContextCompat.getColor(this, R.color.material_navbar))
            }

            // Set the navigation bar color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                window.navigationBarColor = ContextCompat.getColor(this, R.color.material_navbar)
            } else {
                window.javaClass.getDeclaredMethod("setNavigationBarColor", Int::class.java)
                    .invoke(window, ContextCompat.getColor(this, R.color.material_navbar))
            }
        }
    }

    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int {
            return 4 // Number of fragments in your ViewPager
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ToolsFragment()
                1 -> HashtagMainFragment()
                2 -> FontsFragment()
                3 -> SettingsFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }
}
