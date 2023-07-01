package abc.sadnoxx.hashtaggenerator

import abc.sadnoxx.hashtaggenerator.HapticUtils.performHapticFeedback
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Vibrator
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.pm.PackageInfoCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AboutFragment : Fragment() {
    private lateinit var changelogs: MaterialCardView
    private lateinit var contributers: MaterialCardView
    private lateinit var privacyPolicy: MaterialCardView
    private lateinit var termsAndConditions: MaterialCardView
    private lateinit var rateAndReview: MaterialCardView
    private lateinit var reportBugsCard: MaterialCardView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var versionTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_about, container, false)

        contributers = rootView.findViewById(R.id.contributers)
        privacyPolicy = rootView.findViewById(R.id.privacyPolicy)
        termsAndConditions = rootView.findViewById(R.id.termsAndConditions)
        rateAndReview = rootView.findViewById(R.id.rateAndReview)
        reportBugsCard = rootView.findViewById(R.id.reportBugsCard)
        versionTextView = rootView.findViewById(R.id.versionTextView)
        changelogs = rootView.findViewById(R.id.changelogs)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val vibrator = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator



        val packageInfo = requireContext().packageManager.getPackageInfo(
            requireContext().packageName, 0
        )

        val versionName = packageInfo.versionName
        val versionCodeLong = PackageInfoCompat.getLongVersionCode(packageInfo)
        val versionCode = versionCodeLong.toInt()


        val versionText = "v$versionName ( $versionCode )"


        versionTextView.text = versionText



        reportBugsCard.setOnClickListener {
            performHapticFeedback(vibrator, sharedPreferences)
            val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:sadnoxx.dev@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Bug Report / Suggestion")
                putExtra(Intent.EXTRA_TEXT, "Please describe the bug you encountered:")
            }

            if (emailIntent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(emailIntent)
            } else {
                Toast.makeText(requireContext(), "Gmail app not found.", Toast.LENGTH_SHORT).show()
            }
        }

        changelogs.setOnClickListener {
            performHapticFeedback(vibrator, sharedPreferences)
                val inflater = layoutInflater
                val dialogView = inflater.inflate(R.layout.dialog_changelog, null)
                val dialog = MaterialAlertDialogBuilder(requireContext())
                    .setView(dialogView)
                    .setPositiveButton(R.string.ok) { dialog, _ ->
                        // Positive button click action
                        dialog.dismiss()
                    }
                    .setCancelable(true)
                    .create()

                dialog.show()
            }


        privacyPolicy.setOnClickListener {
            performHapticFeedback(vibrator, sharedPreferences)
            openLinkToOtherSites("https://sadnoxx.github.io/hashtagGenerator/terms/")
        }

        rateAndReview.setOnClickListener {
            performHapticFeedback(vibrator, sharedPreferences)
            openLinkToOtherSites("https://play.google.com/store/apps/details?id=abc.sadnoxx.hashtaggenerator&hl=en&gl=US")
        }


        termsAndConditions.setOnClickListener {
            performHapticFeedback(vibrator, sharedPreferences)
            openLinkToOtherSites("https://sadnoxx.github.io/hashtagGenerator/terms/")
        }

        contributers.setOnClickListener {
            performHapticFeedback(vibrator, sharedPreferences)
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null)
            view.setOnClickListener {
                openLinkToOtherSites("https://github.com/akhhyyl")
            }

            context?.let { it1 -> showLinkDialog(it1, view) }
        }


        return rootView
    }

    private fun showLinkDialog(context: Context, view: View) {

        MaterialAlertDialogBuilder(context)
            .setTitle("Contributors")
            .setView(view)
            .setPositiveButton(R.string.close) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun openLinkToOtherSites(linkUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
        requireContext().startActivity(intent)
    }

}