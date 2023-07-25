package abc.sadnoxx.hashtaggenerator.fragments.tools.route

import abc.sadnoxx.hashtaggenerator.AboutFragment
import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.MyBottomSheetDialogFragment
import abc.sadnoxx.hashtaggenerator.fragments.tools.route.categories.CategoriesFragment
import abc.sadnoxx.hashtaggenerator.fragments.tools.route.tophashtags.TopHashtags
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.appbar.MaterialToolbar

class RouteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route)

        // Inside your activity or fragment
        val toolbar: MaterialToolbar = findViewById(R.id.topAppBar) // Replace R.id.toolbar with your AppBar's ID
        // Set the desired title

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)


        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.filterResult -> {
                    val bottomSheetFragment = MyBottomSheetDialogFragment()
                    bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
                    true
                }

                else -> false
            }
        }

        val screenOnFlag = sharedPreferences.getBoolean("sleepChecked", true)
        if (screenOnFlag) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }


        // for setting color to the status and nav bars
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

        toolbar.setNavigationOnClickListener {
            finish()
        }



        when (val fragmentName = intent.getStringExtra("fragment")) {
            "fragment1" -> {
                val fragment1 = TopHashtags()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment1)
                    .commit()
                  toolbar.title = resources.getString(R.string.top_hashtags)
            }
            "likes" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.clikes)
            }
            "reels" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.creels)
            }
            "followers" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cfollowers)
            }
            "popular" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cpopular)
            }
            "photography" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cphotography)
            }
            "gaming" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cgaming)
            }
            "brands" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cbrands)
            }
            "moods" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cmoods)
            }
            "seasonal" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cseasonal)
            }
            "holidays" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cholidays)
            }
            "parenting" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cparenting)
            }
            "work" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cwork)
            }
            "electronics" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.celectronics)
            }
            "weather" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cweather)
            }
            "food" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cfood)
            }
            "travel" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.ctravel)
            }
            "entertainment" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.centertainment)
            }
            "party" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cparty)
            }
            "transportation" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.ctransportation)
            }
            "nature" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cnature)
            }
            "makeup" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cmakeup)
            }
            "fashion" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cfashion)
            }
            "weekdays" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.cweek_days)
            }
            "sports" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.csports)
            }
            else  -> {
                val fragment2 = AboutFragment()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = resources.getString(R.string.about)
            }

        }
        toolbar.menu.findItem(R.id.filterResult)?.isVisible = toolbar.title != resources.getString(R.string.about)



    }


    }
