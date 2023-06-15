package abc.sadnoxx.hashtaggenerator

import abc.sadnoxx.hashtaggenerator.fragments.fonts.FontsFragment
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.HashtagMainFragment
import abc.sadnoxx.hashtaggenerator.fragments.settings.SettingsFragment
import abc.sadnoxx.hashtaggenerator.fragments.tools.ToolsFragment
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
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

private const val KEY_THEME = "theme"
private const val THEME_LIGHT = 0
private const val THEME_DARK = 1
private const val THEME_SYSTEM = 2
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val savedTheme = sharedPreferences.getInt(KEY_THEME, THEME_SYSTEM)
        applyDeviceTheme(savedTheme)
        setContentView(R.layout.activity_main)

        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator


        viewPager = findViewById(R.id.viewPager)
        bottomNavigationView = findViewById(R.id.bottom_navigation)



        //To check and set the screen on feature
        val screenOnFlag = sharedPreferences.getBoolean("sleepChecked", true)
        if (screenOnFlag) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }




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
                performHapticFeedback(vibrator)
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
    private fun performHapticFeedback(vibrator: Vibrator,) {

        val vibrationEnabled = sharedPreferences.getBoolean("vibrationSwitch", true)

        if (vibrationEnabled) {
            // Trigger haptic feedback for a short duration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(30, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                // Deprecated in API 26
                vibrator.vibrate(30)
            }}
    }

}
