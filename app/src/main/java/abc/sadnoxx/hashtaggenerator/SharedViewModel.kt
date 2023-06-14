package abc.sadnoxx.hashtaggenerator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class SharedViewModel : ViewModel() {
    val dataChangedLiveData = MutableLiveData<Unit>()

    fun notifyDataChanged() {
        dataChangedLiveData.value = Unit
    }
}
