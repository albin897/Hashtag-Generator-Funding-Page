package abc.sadnoxx.hashtaggenerator1

import abc.sadnoxx.hashtaggenerator1.fragments.hashtag.saved.SavedHashtagsFragment
import abc.sadnoxx.hashtaggenerator1.fragments.settings.AboutFragment
import abc.sadnoxx.hashtaggenerator1.fragments.tools.TextFormatter
import abc.sadnoxx.hashtaggenerator1.fragments.tools.route.tophashtags.TopHashtags
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class RouteModifiedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_modified)

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


        val searchQuery = intent.getStringExtra("searchQuery")
        // Now you have the searchQuery, you can use it as needed
        // For example, you can pass it to a fragment in this activity
        if (savedInstanceState == null) {
            val fragment = NewHashtagsFragment()
            val args = Bundle()
            args.putString("searchQuery", searchQuery)
            fragment.arguments = args
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_router, fragment)
                .commit()
        }

        when (intent.getStringExtra("fragment")) {
            "hashtags" -> {
                val fragment1 = NewHashtagsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment1)
                    .commit()

            }
            "textFormatter" -> {
                val fragment1 = TextFormatter()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment1)
                    .commit()

            }
            "fragment1" -> {
                val fragment1 = TopHashtags()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment1)
                    .commit()

            }
            "categories" -> {
            val fragment1 = NewCategoriesFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_router, fragment1)
                .commit()

        }
            "about" -> {
            val fragment1 = AboutFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_router, fragment1)
                .commit()

        }
            "saved" -> {
                val fragment1 = SavedHashtagsFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_router, fragment1)
                    .commit()

            }


        }
    }
}