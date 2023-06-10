package abc.sadnoxx.hashtaggenerator.fragments.tools.route

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import abc.sadnoxx.hashtaggenerator.R
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


        val screenOnFlag = sharedPreferences.getBoolean("sleepChecked", true)
        if (screenOnFlag) {
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }else{
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        }


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



        val fragmentName = intent.getStringExtra("fragment")

        if (fragmentName == "fragment1") {
            val fragment1 = TopHashtags()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_router, fragment1)
                .commit()
            toolbar.title = "Top Hashtags"
//        } else if (fragmentName == "fragment2") {
//            val fragment2 = Fragment2()
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, fragment2)
//                .commit()
        }



    }


    }
