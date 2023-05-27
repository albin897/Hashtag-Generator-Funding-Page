package abc.sadnoxx.hashtaggenerator


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(HashtagsFragment())

//        setting status and navigation bar color
//        window.statusBarColor = ContextCompat.getColor(this,R.color.white)
//        window.navigationBarColor = ContextCompat.getColor(this,R.)


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.selectedItemId = R.id.item_2


        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_1 -> {
                    replaceFragment(ToolsFragment())
                    Toast.makeText(applicationContext, "Toast message for item 1", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_2 -> {
                    replaceFragment(HashtagsFragment())
                    Toast.makeText(applicationContext, "Toast message for item 2", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.item_3 -> {
                    replaceFragment(FontsFragment())
                    Toast.makeText(applicationContext, "Toast message for item 3", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }




    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction =fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,fragment)
        fragmentTransaction.commit()
    }
}