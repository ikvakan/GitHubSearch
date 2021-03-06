package hr.ikvakan.git_hub_search.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hr.ikvakan.git_hub_search.ui.AboutDialogFragment
import hr.ikvakan.git_hub_search.ui.activity.ITEM_POSITION
import hr.ikvakan.git_hub_search.R
import hr.ikvakan.git_hub_search.ui.activity.RepositoryPagerActivity
import hr.ikvakan.git_hub_search.model.UserRepositoryModel
import hr.ikvakan.git_hub_search.utils.constants.Constants.QUERY_EXTRA
import hr.ikvakan.git_hub_search.utils.constants.Constants.SORT_TYPE
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item.view.*



//const val SORT_TYPE="sort_type"

class RepositoryRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RepositoryRecyclerAdapter.ViewHolder>() {
    private var items: MutableList<UserRepositoryModel> = mutableListOf()
    private lateinit var _query:String
    private lateinit var _sortType:String

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
        private val tvRepositoryName: TextView = itemView.findViewById(R.id.tvRepositoryName)
        private val ivAvatar: ImageView = itemView.findViewById(R.id.ivAvatar)
        private val tvWatcher: TextView = itemView.findViewById(R.id.tvWatchersCount)
        private val tvIssues: TextView = itemView.findViewById(R.id.tvOpenIssueCount)
        private val tvForks: TextView = itemView.findViewById(R.id.tvForkCount)

        fun bind(item: UserRepositoryModel) {
            Picasso.get()
                .load(item.owner.avatar_url)
                .error(R.mipmap.git_hub_image)
                .transform(RoundedCornersTransformation(50, 5))
                .into(ivAvatar)
            tvRepositoryName.text=item.name
            tvUserName.text=item.owner.userName
            tvWatcher.text=item.watchers_count.toString()
            tvIssues.text=item.open_issues_count.toString()
            tvForks.text=item.forks_count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            Intent(context, RepositoryPagerActivity::class.java).also {
                it.putExtra(QUERY_EXTRA, _query)
                it.putExtra(ITEM_POSITION, position)
                it.putExtra(SORT_TYPE,_sortType)
                context.startActivity(it)
            }
        }
        holder.itemView.ivAvatar.setOnClickListener {
            AboutDialogFragment(_query,items[position].html_url,items[position].owner.html_url).also { dialog->
                dialog.show((context as AppCompatActivity).supportFragmentManager,"dialog_tag")
            }
        }

        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    fun setData(newItems: MutableList<UserRepositoryModel>, query: String,sortType:String){
        items=newItems
        _query=query
        _sortType=sortType
        notifyDataSetChanged()
    }
}

