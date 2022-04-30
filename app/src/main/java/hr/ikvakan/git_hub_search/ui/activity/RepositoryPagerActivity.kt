package hr.ikvakan.git_hub_search.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import hr.ikvakan.git_hub_search.R
import hr.ikvakan.git_hub_search.adapter.RepositoryPagerAdapter

import hr.ikvakan.git_hub_search.model.UserRepositoryModel
import hr.ikvakan.git_hub_search.retrofit.DataState
import hr.ikvakan.git_hub_search.utils.SortType
import hr.ikvakan.git_hub_search.utils.constants.Constants.QUERY_EXTRA
import hr.ikvakan.git_hub_search.utils.constants.Constants.SORT_TYPE
import hr.ikvakan.git_hub_search.utils.showToast
import hr.ikvakan.git_hub_search.viewModels.UserRepositoryViewModel
import kotlinx.android.synthetic.main.activity_host.*

import kotlinx.android.synthetic.main.activity_repository_pager.*


const val ITEM_POSITION = "hr.ikvakan.git_hub_search.ui.activity.repository_item_position"

@AndroidEntryPoint
class RepositoryPagerActivity() : AppCompatActivity() {

    private val viewModel: UserRepositoryViewModel by viewModels()
    private lateinit var repositoryPageAdapter: RepositoryPagerAdapter
    private var itemPosition: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository_pager)

        setupRepositoryPagerView()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun setupRepositoryPagerView() {
        itemPosition = intent.getIntExtra(ITEM_POSITION, 0)
        val sortType= intent.getStringExtra(SORT_TYPE)
        repositoryPageAdapter = RepositoryPagerAdapter(this)

        var query = intent.getStringExtra(QUERY_EXTRA)
        if (query != null) {
            viewModel.getUserRepositoriesByUserName(query)
            viewModel.dataState.observe(this, { dataState ->
                when (dataState) {
                    is DataState.Success<MutableList<UserRepositoryModel>> -> {
                        when(sortType){
                            SortType.FORKS.name ->
                            dataState.data.sortByDescending { i -> i.forks_count }
                            SortType.WATCHERS.name ->
                                dataState.data.sortByDescending { i -> i.watchers_count }
                            SortType.ISSUES.name ->
                                dataState.data.sortByDescending { i -> i.open_issues_count }
                        }
                        repositoryPageAdapter.setData(dataState.data)
                        repositoryPager.adapter = repositoryPageAdapter
                        repositoryPager.currentItem = itemPosition
                    }
                    is DataState.Error -> {
                        showToast(dataState.exception.message, Toast.LENGTH_LONG)
                    }
                    is DataState.Loading -> {
                        return@observe
                    }
                }
            })
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun displayProgressBar(visible: Boolean) {
        progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

}

