package abc.sadnoxx.hashtaggenerator.fragments.hashtag.saved

import abc.sadnoxx.hashtaggenerator.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SavedHashtagsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_saved_hashtags, container, false)




        return rootView    }

}