package abc.sadnoxx.hashtaggenerator


import abc.sadnoxx.hashtaggenerator.fragments.fonts.FontsFragment
import abc.sadnoxx.hashtaggenerator.fragments.hashtags.HashtagsFragment
import abc.sadnoxx.hashtaggenerator.fragments.tools.ToolsFragment
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HashtagsFragment())


// for setting color to the status and nav bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Set the status bar color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = ContextCompat.getColor(this, R.color.material_statusbar)
            } else {
                window.javaClass.getDeclaredMethod("setStatusBarColor", Int::class.java)
                    .invoke(window, ContextCompat.getColor(this, R.color.material_statusbar))
            }

            // Set the navigation bar color
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                window.navigationBarColor = ContextCompat.getColor(this, R.color.material_navbar)
            } else {
                window.javaClass.getDeclaredMethod("setNavigationBarColor", Int::class.java)
                    .invoke(window, ContextCompat.getColor(this, R.color.material_navbar))
            }
        }




        bottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.selectedItemId = R.id.item_2

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {
                    replaceFragment(ToolsFragment())
                    true
                }

                R.id.item_2 -> {
                    replaceFragment(HashtagsFragment())
                    true
                }

                R.id.item_3 -> {
                    replaceFragment(FontsFragment())
                    true
                }

                else -> false
            }
        }


    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}