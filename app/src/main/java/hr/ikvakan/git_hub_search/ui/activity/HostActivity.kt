package hr.ikvakan.git_hub_search.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import hr.ikvakan.git_hub_search.R
import hr.ikvakan.git_hub_search.adapter.RepositoryRecyclerAdapter
import hr.ikvakan.git_hub_search.model.UserRepositoryModel
import hr.ikvakan.git_hub_search.retrofit.DataState
import hr.ikvakan.git_hub_search.utils.enums.SortType
import hr.ikvakan.git_hub_search.utils.enums.SpinnerOptions
import hr.ikvakan.git_hub_search.utils.extensions.showSnackBar
import hr.ikvakan.git_hub_search.utils.extensions.showToast
import hr.ikvakan.git_hub_search.viewModels.UserRepositoryViewModel
import kotlinx.android.synthetic.main.activity_host.*


@AndroidEntryPoint
class HostActivity : AppCompatActivity() {

    private lateinit var rvRepositoryAdapter: RepositoryRecyclerAdapter
    private val viewModel: UserRepositoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setContentView(R.layout.activity_host)
        setupRecyclerView()
        setupSpinner()
    }

    private fun setupSpinner() {
        spinner.setItems(resources.getStringArray(R.array.spinner_values).toMutableList())
    }

    private fun setupRecyclerView() {
        rvRepositoryAdapter = RepositoryRecyclerAdapter(this)
        rvItems.apply {
            adapter = rvRepositoryAdapter
            layoutManager = LinearLayoutManager(this@HostActivity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.host_menu, menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem?.actionView as SearchView
        setupSearchView(searchView)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {

                    viewModel.getUserRepositoriesByUserName(query.toString())

                    viewModel.dataState.observe(this@HostActivity, { dataState ->
                        when (dataState) {
                            is DataState.Success<MutableList<UserRepositoryModel>> -> {
                                displayProgressBar(false)
                                when (spinner.selectedIndex) {
                                    SpinnerOptions.FORKS.ordinal -> {
                                        dataState.data.sortByDescending { i -> i.forks_count }
                                        rvRepositoryAdapter.setData(
                                            dataState.data ,
                                            query,
                                            SortType.FORKS.name
                                        )
                                    }
                                    SpinnerOptions.ISSUES.ordinal -> {
                                        dataState.data.sortByDescending { i -> i.open_issues_count }
                                        rvRepositoryAdapter.setData(
                                            dataState.data ,
                                            query,
                                            SortType.ISSUES.name
                                        )
                                    }
                                    SpinnerOptions.WATCHERS.ordinal -> {
                                        dataState.data.sortByDescending { i -> i.watchers_count }
                                        rvRepositoryAdapter.setData(
                                            dataState.data ,
                                            query,
                                            SortType.WATCHERS.name
                                        )
                                    }
                                }
                            }
                            is DataState.Error -> {
                                displayProgressBar(false)
                                hostActivity.showSnackBar(dataState.exception.message)
                            }
                            is DataState.Loading -> {
                                displayProgressBar(true)
                            }
                        }
                    })
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun showError(message: String?) {
        showToast(message, Toast.LENGTH_LONG)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuExit -> {
                exitApp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayProgressBar(visible: Boolean) {
        progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun exitApp() {
        AlertDialog.Builder(this).apply {
            setTitle("Exit")
            setMessage("Do you want to exit ?")
            setIcon(R.drawable.ic_baseline_exit_to_app_24)
            setPositiveButton("Ok") { _, _ -> finish() }
            setNegativeButton("Cancel", null)
            show()
        }
    }

    fun displaySpinner(visible: Boolean) {
        spinner.visibility = if (visible) View.VISIBLE else View.GONE
    }
}

