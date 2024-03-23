package abc.sadnoxx.hashtaggenerator1

import android.content.SharedPreferences
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

object HapticUtils {


    fun performHapticFeedback(vibrator: Vibrator, sharedPreferences: SharedPreferences) {
        val vibrationEnabled = sharedPreferences.getBoolean("vibrationSwitch", true)

        if (vibrationEnabled) {
            // Trigger haptic feedback for a short duration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        30,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                // Deprecated in API 26
                vibrator.vibrate(30)
            }
        }
    }
}
