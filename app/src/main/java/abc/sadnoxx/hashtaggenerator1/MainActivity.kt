package abc.sadnoxx.hashtaggenerator1

import abc.sadnoxx.hashtaggenerator1.fragments.fonts.FontsFragment
import abc.sadnoxx.hashtaggenerator1.fragments.tools.ToolsFragment
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.pm.PackageInfoCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationBarView
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.ump.ConsentDebugSettings
import com.google.android.ump.ConsentForm
import com.google.android.ump.ConsentInformation
import com.google.android.ump.ConsentRequestParameters
import com.google.android.ump.UserMessagingPlatform
import java.util.Locale
import java.util.concurrent.atomic.AtomicBoolean

private const val KEY_THEME = "theme"
private const val THEME_LIGHT = 0
private const val THEME_DARK = 1
private const val THEME_SYSTEM = 2
private const val LAUNCH_COUNTER_KEY = "launch_counter"
private const val KEY_SCREEN = "screen"

private const val PREF_VERSION_CODE_KEY = "version_code"

private const val KEY_LANGUAGE = "language1"
private const val LANGUAGE_ENGLISH = "en"



class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var consentInformation: ConsentInformation
    // Use an atomic boolean to initialize the Google Mobile Ads SDK and load ads once.
    private var isMobileAdsInitializeCalled = AtomicBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        applyTheme()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        requestConcentForm()


        val savedLanguage = sharedPreferences.getString(KEY_LANGUAGE,LANGUAGE_ENGLISH)
        setAppLanguage(savedLanguage!!)


        try {
            val packageInfo = this.packageManager.getPackageInfo(
                this.packageName, 0
            )


            val versionCodeLong = PackageInfoCompat.getLongVersionCode(packageInfo)

            val savedVersionCode = sharedPreferences.getInt(PREF_VERSION_CODE_KEY, 0)
            val appVersionCode = versionCodeLong.toInt()

            if (appVersionCode > savedVersionCode) {
                showChangelogDialog()
                sharedPreferences.edit()
                    .putInt(PREF_VERSION_CODE_KEY, appVersionCode)
                    .apply()
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        val launchCount = sharedPreferences.getInt(LAUNCH_COUNTER_KEY, 0)
        if (launchCount >= 5) {
            showFeedbackDialog()        //review dialog
        }

        // Increment the launch count
        sharedPreferences.edit().putInt(LAUNCH_COUNTER_KEY, launchCount + 3).apply()




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

        bottomNavigationView.setOnItemSelectedListener { item ->
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

//        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//                // Not needed
//            }
//
//            override fun onPageSelected(position: Int) {
//                bottomNavigationView.menu.getItem(position).isChecked = true
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                // Not needed
//            }
//        })

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Not needed
            }

            override fun onPageSelected(position: Int) {
                // Update the theme when a new page is selected
                val currentFragment = pagerAdapter.instantiateItem(viewPager, position) as? Fragment
                currentFragment?.let {
                    applyFragmentTheme()
                }

                // Update the bottom navigation view
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(state: Int) {
                // Not needed
            }
        })


        //For setting initial page based on the user selection
        val initialSelection = sharedPreferences.getInt(KEY_SCREEN, 1)
        // Set initial selection
        viewPager.currentItem = initialSelection

        // for setting color to the status and nav bars
        // Set the status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.background_color)
        } else {
            window.javaClass.getDeclaredMethod("setStatusBarColor", Int::class.java)
                .invoke(window, ContextCompat.getColor(this, R.color.background_color))
        }

        // Set the navigation bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window.navigationBarColor = ContextCompat.getColor(this, R.color.background_color)
        } else {
            window.javaClass.getDeclaredMethod("setNavigationBarColor", Int::class.java)
                .invoke(window, ContextCompat.getColor(this, R.color.background_color))
        }
    }

    private fun requestConcentForm() {
        // Set tag for under age of consent. false means users are not under age
        // of consent.

        val debugSettings = ConsentDebugSettings.Builder(this)
            .addTestDeviceHashedId("ADF87CB907020E45C7F19B70E48B0AC6")
            .build()


        val params = ConsentRequestParameters
            .Builder()
            .setConsentDebugSettings(debugSettings)
            .setTagForUnderAgeOfConsent(false)
            .build()

        consentInformation = UserMessagingPlatform.getConsentInformation(this)
        consentInformation.requestConsentInfoUpdate(
            this,
            params,
            ConsentInformation.OnConsentInfoUpdateSuccessListener {
                UserMessagingPlatform.loadAndShowConsentFormIfRequired(
                    this@MainActivity,
                    ConsentForm.OnConsentFormDismissedListener {
                            loadAndShowError ->
                        // Consent gathering failed.
                        if (loadAndShowError != null) {
                            Log.w("TAG", String.format("%s: %s",
                                loadAndShowError.errorCode,
                                loadAndShowError.message
                            ))
                        }

                        // Consent has been gathered.
                        if (consentInformation.canRequestAds()) {
                            initializeMobileAdsSdk()
                        }
                    }
                )
            },
            ConsentInformation.OnConsentInfoUpdateFailureListener {
                    requestConsentError ->
                // Consent gathering failed.
                Log.w("TAG", String.format("%s: %s",
                    requestConsentError.errorCode,
                    requestConsentError.message
                ))
            })

        // Check if you can initialize the Google Mobile Ads SDK in parallel
        // while checking for new consent information. Consent obtained in
        // the previous session can be used to request ads.
        if (consentInformation.canRequestAds()) {
            initializeMobileAdsSdk()
        }
    }

    private fun initializeMobileAdsSdk() {
        if (isMobileAdsInitializeCalled.getAndSet(true)) {
            return
        }

        // Initialize the Google Mobile Ads SDK.
        MobileAds.initialize(this)

        // TODO: Request an ad.
        // InterstitialAd.load(...)
    }




// Inside your MainActivity
    private fun applyTheme() {
        val themeMode = when (sharedPreferences.getInt(KEY_THEME, THEME_LIGHT)) {
            THEME_LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            THEME_DARK -> AppCompatDelegate.MODE_NIGHT_YES
            THEME_SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            else -> AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
        }

        if (themeMode != AppCompatDelegate.getDefaultNightMode()) {
            AppCompatDelegate.setDefaultNightMode(themeMode)
            recreate()
        }
    }


    private fun applyFragmentTheme() {
        applyTheme()
        // Handle fragment-specific theme changes if needed
    }




    private fun showChangelogDialog() {
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_changelog, null)
        val dialog = MaterialAlertDialogBuilder(this)
//            .setTitle("Changelogs")
            .setView(dialogView)
            .setPositiveButton(R.string.ok) { dialog, _ ->
                // Positive button click action
                dialog.dismiss()
            }
            .setCancelable(true)
            .create()

        dialog.show()
    }

    private fun applyDeviceTheme(savedTheme: Int) {
        // Apply the selected theme using AppCompatDelegate
        when (savedTheme) {
            THEME_LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            THEME_DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            THEME_SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

//    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//        override fun getCount(): Int {
//            return 4 // Number of fragments in your ViewPager
//        }
//
//        override fun getItem(position: Int): Fragment {
//            return when (position) {
//                0 -> ToolsFragment()
//                1 -> MainScreenFragment()
//                2 -> FontsFragment()
//                3 -> NewSettingsFragment()
//                else -> throw IllegalArgumentException("Invalid position")
//            }
//        }
//    }


    inner class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_SET_USER_VISIBLE_HINT) {

        override fun getCount(): Int {
            return 4 // Number of fragments in your ViewPager
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ToolsFragment()
                1 -> MainScreenFragment()
                2 -> FontsFragment()
                3 -> NewSettingsFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }

    private fun showFeedbackDialog(){
        val reviewManager = ReviewManagerFactory.create(applicationContext)
        reviewManager.requestReviewFlow().addOnCompleteListener{
            if(it.isSuccessful){
                reviewManager.launchReviewFlow(this, it.result)
            }
        }
    }
    private fun setAppLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration()
        config.locale = locale

        // Update the app's configuration with the new locale
        resources.updateConfiguration(config, resources.displayMetrics)
       }



}
