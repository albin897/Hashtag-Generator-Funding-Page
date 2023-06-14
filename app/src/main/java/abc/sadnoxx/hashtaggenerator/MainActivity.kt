package abc.sadnoxx.hashtaggenerator

import abc.sadnoxx.hashtaggenerator.fragments.fonts.FontsFragment
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.HashtagMainFragment
import abc.sadnoxx.hashtaggenerator.fragments.settings.SettingsFragment
import abc.sadnoxx.hashtaggenerator.fragments.tools.ToolsFragment
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView


class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ViewPagerAdapter


    private val KEY_THEME = "theme"
    private val THEME_LIGHT = 0
    private val THEME_DARK = 1
    private val THEME_SYSTEM = 2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val savedTheme = sharedPreferences.getInt(KEY_THEME, THEME_SYSTEM)
        applyDeviceTheme(savedTheme)
        setContentView(R.layout.activity_main)




        val screenOnFlag = sharedPreferences.getBoolean("sleepChecked", true)
        if (screenOnFlag) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }


// Apply the saved theme
        applyDeviceTheme(savedTheme)


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

    private fun applyDeviceTheme(savedTheme: Int) {
        // Apply the selected theme using AppCompatDelegate
        when (savedTheme) {
            THEME_LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            THEME_DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            THEME_SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }

        // Save the selected theme to SharedPreferences
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_THEME, savedTheme)
        editor.apply()
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
