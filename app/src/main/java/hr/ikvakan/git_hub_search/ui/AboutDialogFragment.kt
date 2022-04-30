package hr.ikvakan.git_hub_search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import hr.ikvakan.git_hub_search.R
import hr.ikvakan.git_hub_search.model.UserModel
import hr.ikvakan.git_hub_search.retrofit.DataState
import hr.ikvakan.git_hub_search.ui.activity.ITEM_URL
import hr.ikvakan.git_hub_search.ui.activity.WebViewActivity
import hr.ikvakan.git_hub_search.utils.extensions.showToast
import hr.ikvakan.git_hub_search.utils.extensions.startActivity
import hr.ikvakan.git_hub_search.viewModels.UserViewModel
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.about_dialog_fragment.*
import kotlinx.android.synthetic.main.about_dialog_fragment.view.*
import kotlinx.android.synthetic.main.activity_host.*
import kotlinx.android.synthetic.main.item.*

@AndroidEntryPoint
class AboutDialogFragment(
    private val query: String,
    private val repoUrl: String,
    private val userUrl: String
) : DialogFragment() {
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.ThemeOverlay_AppCompat_Dialog)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var dialogView: View = inflater.inflate(R.layout.about_dialog_fragment, container, false)
        dialogView.ibClose.setOnClickListener {
            dismiss()
        }
        dialogView.ibUserWebView.setOnClickListener {
            context?.startActivity<WebViewActivity>(ITEM_URL, userUrl)
        }
        viewModel.getUser(query)
        viewModel.dataState.observe(this, { dataState ->
            when (dataState) {
                is DataState.Success<UserModel> -> {
                    displayProgressBar(false)
                    populateUser(dataState.data, dialogView)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    showToast(requireContext(),dataState.exception.message,Toast.LENGTH_LONG)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)

                }
            }
        })
        return dialogView
    }

    private fun populateUser(model: UserModel, view: View) {
        Picasso.get()
            .load(model.avatar_url)
            .transform(RoundedCornersTransformation(50, 5))
            .into(view.ivAvatarDialogItem)
        view.tvUserName.text = model.userName
        view.tvUserEmail.text = model.email ?: "No email provided"
        view.tvUserLoaction.text = model.location ?: "No location provided"
        view.tvUserUrl.text = userUrl
    }
    private fun displayProgressBar(visible: Boolean) {
        progressBarDialog.visibility = if (visible) View.VISIBLE else View.GONE
    }

}