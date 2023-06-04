package abc.sadnoxx.hashtaggenerator.fragments.hashtag

import abc.sadnoxx.hashtaggenerator.fragments.hashtag.hashtags.HashtagsFragment
import abc.sadnoxx.hashtaggenerator.fragments.hashtag.saved.SavedHashtagsFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HashtagPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return if(position == 0)
            HashtagsFragment()
        else
            SavedHashtagsFragment()
    }
}