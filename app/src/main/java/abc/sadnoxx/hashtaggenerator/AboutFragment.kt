package abc.sadnoxx.hashtaggenerator

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AboutFragment : Fragment() {
    private lateinit var contributers: MaterialCardView
    private lateinit var privacyPolicy: MaterialCardView
    private lateinit var  termsAndConditions: MaterialCardView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val rootView = inflater.inflate(R.layout.fragment_about, container, false)

        contributers = rootView.findViewById(R.id.contributers)
        privacyPolicy= rootView.findViewById(R.id.privacyPolicy)
        termsAndConditions= rootView.findViewById(R.id.termsAndConditions)
        val linkUrl = "https://play.google.com/store/apps/dev?id=6273310203005723815"

        contributers.setOnClickListener {
            showLinkDialog(requireContext(),linkUrl)
        }

        privacyPolicy.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sadnoxx.github.io/hashtagGenerator/privacy/"))
            requireContext().startActivity(intent)
        }


        termsAndConditions.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sadnoxx.github.io/hashtagGenerator/terms/"))
            requireContext().startActivity(intent)

        }




        return rootView
    }

    private fun showLinkDialog(context: Context, linkUrl: String) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null)

        view.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
            context.startActivity(intent)
        }

        MaterialAlertDialogBuilder(context)
            .setTitle("Contributors")
            .setView(view)
            .setPositiveButton(R.string.close) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}