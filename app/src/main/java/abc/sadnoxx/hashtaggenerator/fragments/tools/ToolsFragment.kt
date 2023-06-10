package abc.sadnoxx.hashtaggenerator.fragments.tools

import abc.sadnoxx.hashtaggenerator.R
import abc.sadnoxx.hashtaggenerator.fragments.tools.route.RouteActivity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.card.MaterialCardView

class ToolsFragment : Fragment() {

  private lateinit var topHashtags: MaterialCardView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_tools, container, false)

        topHashtags = rootView.findViewById(R.id.topHashtags)



        topHashtags.setOnClickListener {
            openRouteActivityWithFragment1()
        }


        return rootView
    }

    private fun openRouteActivityWithFragment1() {
        val intent = Intent(activity, RouteActivity::class.java)
        intent.putExtra("fragment", "fragment1")
        startActivity(intent)
    }
}