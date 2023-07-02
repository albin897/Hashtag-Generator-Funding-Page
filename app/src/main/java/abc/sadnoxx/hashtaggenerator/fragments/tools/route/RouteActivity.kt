package abc.sadnoxx.hashtaggenerator.fragments.tools.route

import abc.sadnoxx.hashtaggenerator.AboutFragment
import abc.sadnoxx.hashtaggenerator.HapticUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.MyBottomSheetDialogFragment
import abc.sadnoxx.hashtaggenerator.fragments.tools.route.categories.CategoriesFragment
import abc.sadnoxx.hashtaggenerator.fragments.tools.route.tophashtags.TopHashtags
import android.os.Build
import android.preference.PreferenceManager
import android.view.WindowManager
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
                toolbar.title = "Top Hashtags"
            }
            "likes" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Likes"
            }
            "reels" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Reels"
            }
            "followers" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Followers"
            }
            "popular" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Popular"
            }
            "photography" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Photography"
            }
            "gaming" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Gaming"
            }
            "brands" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Brands"
            }
            "moods" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Moods"
            }
            "seasonal" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Seasonal"
            }
            "holidays" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Holidays"
            }
            "parenting" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Parenting"
            }
            "work" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Work"
            }
            "electronics" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Electronics"
            }
            "weather" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Weather"
            }
            "food" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Food"
            }
            "travel" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Travel"
            }
            "entertainment" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Entertainment"
            }
            "party" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Party"
            }
            "transportation" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Transportation"
            }
            "nature" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Nature"
            }
            "makeup" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Makeup"
            }
            "fashion" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Fashion"
            }
            "weekdays" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Weekdays"
            }
            "sports" -> {
                val fragment2 = CategoriesFragment()
                val args = Bundle()
                args.putString("category", fragmentName) // Pass the category name as an argument
                fragment2.arguments = args

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "Sports"
            }
            else  -> {
                val fragment2 = AboutFragment()

                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment2)
                    .commit()
                toolbar.title = "About"
            }

        }
        toolbar.menu.findItem(R.id.filterResult)?.isVisible = toolbar.title != "About"



    }


    }
