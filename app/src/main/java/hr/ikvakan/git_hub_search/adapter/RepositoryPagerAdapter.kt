package hr.ikvakan.git_hub_search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.ikvakan.git_hub_search.ITEM_URL
import hr.ikvakan.git_hub_search.R
import hr.ikvakan.git_hub_search.WebViewActivity
import hr.ikvakan.git_hub_search.model.UserRepositoryModel
import hr.ikvakan.git_hub_search.utils.formatDate
import hr.ikvakan.git_hub_search.utils.startActivity
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.repository_pager.view.*


class RepositoryPagerAdapter(
    private val context: Context
) :
    RecyclerView.Adapter<RepositoryPagerAdapter.ViewHolder>() {
    private var items: MutableList<UserRepositoryModel> = mutableListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatarPagerItem)
        private val tvRepositoryName: TextView = itemView.findViewById(R.id.tvRepositoryName)
        private val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        private val tvLanguage: TextView = itemView.findViewById(R.id.tvLanguage)
        private val tvCreatedAt: TextView = itemView.findViewById(R.id.tvCreatedAt)
        private val tvLastModified: TextView = itemView.findViewById(R.id.tvLastModified)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescirptionPager)
        private val tvForks: TextView = itemView.findViewById(R.id.tvForkCount)
        private val tvWatchers: TextView = itemView.findViewById(R.id.tvWatchersCount)
        private val tvIssues: TextView = itemView.findViewById(R.id.tvOpenIssueCount)
        private val btnGitHub: Button = itemView.findViewById(R.id.btnGoToGithub)


        fun bind(item: UserRepositoryModel) {
            Picasso.get()
                .load(item.owner.avatar_url)
                .error(R.mipmap.git_hub_image)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivAvatar)

            tvRepositoryName.text = item.name
            tvUserName.text = item.owner.userName
            tvLanguage.text = item.language ?: "No language provided"
            tvCreatedAt.text = item.created_at.formatDate()
            tvLastModified.text = item.updated_at.formatDate()
            tvDescription.text = item.description ?:"No description provided"
            tvForks.text = item.forks_count.toString()
            tvWatchers.text = item.watchers_count.toString()
            tvIssues.text = item.open_issues_count.toString()
            btnGitHub.setOnClickListener {
                context?.startActivity<WebViewActivity>(ITEM_URL, item.html_url)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_pager, parent, false)



        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    fun setData(newItems: MutableList<UserRepositoryModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}