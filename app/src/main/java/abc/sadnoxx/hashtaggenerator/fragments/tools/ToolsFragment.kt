package abc.sadnoxx.hashtaggenerator.fragments.tools

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.RouteModifiedActivity
import abc.sadnoxx.hashtaggenerator.fragments.tools.route.RouteActivity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

private const val KEY_THEME = "theme"
private const val THEME_LIGHT = 0
private const val THEME_DARK = 1
private const val THEME_SYSTEM = 2
class ToolsFragment : Fragment() {

  private lateinit var topHashtags: MaterialCardView
    private lateinit var likes: LinearLayout
    private lateinit var reels: LinearLayout
    private lateinit var followers: LinearLayout
    private lateinit var popular: LinearLayout
    private lateinit var photography: LinearLayout
    private lateinit var gaming: LinearLayout
    private lateinit var brands: LinearLayout
    private lateinit var moods: LinearLayout
    private lateinit var seasonal: LinearLayout
    private lateinit var holidays: LinearLayout
    private lateinit var parenting: LinearLayout
    private lateinit var work: LinearLayout
    private lateinit var electronics: LinearLayout
    private lateinit var weather: LinearLayout
    private lateinit var food: LinearLayout
    private lateinit var travel: LinearLayout
    private lateinit var entertainment: LinearLayout
    private lateinit var party: LinearLayout
    private lateinit var transportation: LinearLayout
    private lateinit var nature: LinearLayout
    private lateinit var makeup: LinearLayout
    private lateinit var fashion: LinearLayout
    private lateinit var weekdays: LinearLayout
    private lateinit var sports: LinearLayout
    private lateinit var textFormatter: MaterialCardView

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_tools, container, false)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        applyFragmentTheme()

        topHashtags = rootView.findViewById(R.id.topHashtags)
        likes = rootView.findViewById(R.id.likes)
        reels = rootView.findViewById(R.id.reels)
        followers = rootView.findViewById(R.id.followers)
        popular = rootView.findViewById(R.id.popular)
        photography = rootView.findViewById(R.id.photography)
        gaming = rootView.findViewById(R.id.gaming)
        brands = rootView.findViewById(R.id.brands)
        moods = rootView.findViewById(R.id.moods)
        seasonal = rootView.findViewById(R.id.seasonal)
        holidays = rootView.findViewById(R.id.holidays)
        parenting = rootView.findViewById(R.id.parenting)
        work = rootView.findViewById(R.id.work)
        electronics = rootView.findViewById(R.id.electronics)
        weather = rootView.findViewById(R.id.weather)
        food = rootView.findViewById(R.id.food)
        travel = rootView.findViewById(R.id.travel)
        entertainment = rootView.findViewById(R.id.entertainment)
        party = rootView.findViewById(R.id.party)
        transportation = rootView.findViewById(R.id.transportation)
        nature = rootView.findViewById(R.id.nature)
        makeup = rootView.findViewById(R.id.makeup)
        fashion = rootView.findViewById(R.id.fashion)
        weekdays = rootView.findViewById(R.id.weekdays)
        sports = rootView.findViewById(R.id.sports)
        textFormatter = rootView.findViewById(R.id.textFormatter)



        textFormatter.setOnClickListener {
            openRouteActivityWithFragmentTextFormatter()
        }

        topHashtags.setOnClickListener {
            openRouteActivityWithFragment1()
        }

        likes.setOnClickListener {
            openRouteActivityWithLikes()
        }

        reels.setOnClickListener {
            openRouteActivityWithReels()
        }

        followers.setOnClickListener {
            openRouteActivityWithFollowers()
        }

        popular.setOnClickListener {
            openRouteActivityWithPopular()
        }

        photography.setOnClickListener {
            openRouteActivityWithPhotography()
        }

        gaming.setOnClickListener {
            openRouteActivityWithGaming()
        }

        brands.setOnClickListener {
            openRouteActivityWithBrands()
        }

        moods.setOnClickListener {
            openRouteActivityWithMoods()
        }

        seasonal.setOnClickListener {
            openRouteActivityWitSeasonal()
        }

        holidays.setOnClickListener {
            openRouteActivityWithHolidays()
        }

        parenting.setOnClickListener {
            openRouteActivityWithParenting()
        }

        work.setOnClickListener {
            openRouteActivityWithWork()
        }

        electronics.setOnClickListener {
            openRouteActivityWithElectronics()
        }

        weather.setOnClickListener {
            openRouteActivityWithWeather()
        }

        food.setOnClickListener {
            openRouteActivityWithFood()
        }

        travel.setOnClickListener {
            openRouteActivityWithTravel()
        }

        entertainment.setOnClickListener {
            openRouteActivityWithEntertainment()
        }

        party.setOnClickListener {
            openRouteActivityWithParty()
        }

        transportation.setOnClickListener {
            openRouteActivityWithTransportation()
        }

        nature.setOnClickListener {
            openRouteActivityWithNature()
        }

        makeup.setOnClickListener {
            openRouteActivityWithMakeup()
        }

        fashion.setOnClickListener {
            openRouteActivityWithFashion()
        }

        weekdays.setOnClickListener {
            openRouteActivityWithWeekdays()
        }

        sports.setOnClickListener {
            openRouteActivityWithSports()
        }








        return rootView
    }

    private fun applyFragmentTheme() {
        when (sharedPreferences.getInt(KEY_THEME, THEME_LIGHT)) {
            THEME_LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            THEME_DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            THEME_SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    private fun openRouteActivityWithSports() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "sports")
        startActivity(intent)
    }

    private fun openRouteActivityWithWeekdays() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "weekdays")
        startActivity(intent)
    }

    private fun openRouteActivityWithFashion() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "fashion")
        startActivity(intent)
    }

    private fun openRouteActivityWithMakeup() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "makeup")
        startActivity(intent)
    }

    private fun openRouteActivityWithNature() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "nature")
        startActivity(intent)
    }

    private fun openRouteActivityWithTransportation() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "transportation")
        startActivity(intent)
    }

    private fun openRouteActivityWithParty() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "party")
        startActivity(intent)
    }

    private fun openRouteActivityWithEntertainment() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "entertainment")
        startActivity(intent)
    }

    private fun openRouteActivityWithTravel() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "travel")
        startActivity(intent)
    }

    private fun openRouteActivityWithFood() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "food")
        startActivity(intent)
    }

    private fun openRouteActivityWithWeather() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "weather")
        startActivity(intent)
    }

    private fun openRouteActivityWithElectronics() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "electronics")
        startActivity(intent)
    }

    private fun openRouteActivityWithWork() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "work")
        startActivity(intent)
    }

    private fun openRouteActivityWithParenting() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "parenting")
        startActivity(intent)
    }

    private fun openRouteActivityWithHolidays() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "holidays")
        startActivity(intent)
    }

    private fun openRouteActivityWitSeasonal() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "seasonal")
        startActivity(intent)
    }

    private fun openRouteActivityWithMoods() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "moods")
        startActivity(intent)
    }

    private fun openRouteActivityWithBrands() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "brands")
        startActivity(intent)
    }

    private fun openRouteActivityWithGaming() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "gaming")
        startActivity(intent)
    }

    private fun openRouteActivityWithPhotography() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "photography")
        startActivity(intent)
    }

    private fun openRouteActivityWithPopular() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "popular")
        startActivity(intent)
    }

    private fun openRouteActivityWithFollowers() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "followers")
        startActivity(intent)
    }

    private fun openRouteActivityWithReels() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "reels")
        startActivity(intent)
    }




    private fun openRouteActivityWithLikes() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "likes")
        startActivity(intent)
    }

    private fun openRouteActivityWithFragment1() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "fragment1")
        startActivity(intent)
    }

    private fun openRouteActivityWithFragmentTextFormatter() {
        val intent = Intent(activity, RouteModifiedActivity::class.java)
        intent.putExtra("fragment", "textFormatter")
        startActivity(intent)
    }


}