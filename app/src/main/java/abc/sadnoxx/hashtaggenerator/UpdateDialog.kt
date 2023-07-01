package abc.sadnoxx.hashtaggenerator

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UpdateDialog(private val context: Context) {
    fun showNoUpdatesDialog() {
        MaterialAlertDialogBuilder(context)
//            .setTitle("No Updates Available")
            .setMessage(R.string.noupdatedialog)
            .setPositiveButton(R.string.close) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    fun showNewVersionDialog() {
        MaterialAlertDialogBuilder(context)
            .setTitle(R.string.updateavailabledialog)
            .setMessage(R.string.updateavailabledialogdescription)
            .setPositiveButton(R.string.update) { dialog, _ ->
                val playStoreIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=abc.sadnoxx.hashtaggenerator"))
                context.startActivity(playStoreIntent)
                dialog.dismiss()
            }
            .setNegativeButton(R.string.not_now) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


}
