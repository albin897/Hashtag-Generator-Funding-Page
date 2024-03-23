package abc.sadnoxx.hashtaggenerator1.fragments.hashtag.hashtags

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class SharedViewModel : ViewModel() {
    val dataChangedLiveData = MutableLiveData<Unit>()

    fun notifyDataChanged() {
        dataChangedLiveData.value = Unit
    }
}
