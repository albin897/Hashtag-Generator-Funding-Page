package abc.sadnoxx.hashtaggenerator

import abc.sadnoxx.hashtaggenerator.fragments.tools.route.categories.CategoriesFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class NewCategoriesFragment : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_new_categories, container, false)

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

        likes.setOnClickListener {
            // Pass the category name as an argument
            val categoryName = "likes"
            val bundle = Bundle()
            bundle.putString("category", categoryName)

            // Create a new instance of CategoriesFragment
            val categoriesFragment = CategoriesFragment()
            categoriesFragment.arguments = bundle

            // Replace the current fragment with CategoriesFragment
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_router, categoriesFragment)
            transaction.addToBackStack(null) // Optional: Add to back stack if you want to navigate back
            transaction.commit()
        }

        reels.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("reels")
        }
        followers.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("followers")
        }
        popular.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("popular")
        }
        photography.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("photography")
        }
        gaming.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("gaming")
        }
        brands.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("brands")
        }
        moods.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("moods")
        }
        seasonal.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("seasonal")
        }
        holidays.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("holidays")
        }
        parenting.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("parenting")
        }
        work.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("work")
        }
        electronics.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("electronics")
        }
        weather.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("weather")
        }
        food.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("food")
        }
        travel.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("travel")
        }
        entertainment.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("entertainment")
        }
        party.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("party")
        }
        transportation.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("transportation")
        }
        nature.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("nature")
        }
        makeup.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("makeup")
        }
        fashion.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("fashion")
        }
        weekdays.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("weekdays")
        }
        sports.setOnClickListener {
            openFragmentWithTheRequiredCategoryHashtags("sports")
        }

        return rootView
    }


    private fun openFragmentWithTheRequiredCategoryHashtags(name:String){
        val bundle = Bundle()
        bundle.putString("category", name)

        // Create a new instance of CategoriesFragment
        val categoriesFragment = CategoriesFragment()
        categoriesFragment.arguments = bundle

        // Replace the current fragment with CategoriesFragment
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_router, categoriesFragment)
        transaction.addToBackStack(null) // Optional: Add to back stack if you want to navigate back
        transaction.commit()
    }
}
