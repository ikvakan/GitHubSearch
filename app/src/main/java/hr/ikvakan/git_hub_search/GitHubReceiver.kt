package hr.ikvakan.git_hub_search

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.ikvakan.git_hub_search.ui.activity.DATA_IMPORTED
import hr.ikvakan.git_hub_search.utils.setBooleanPreference

class GitHubReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        context.setBooleanPreference(DATA_IMPORTED,true)
       // context.startActivity<HostActivity>().apply { setFlags(  Intent.FLAG_ACTIVITY_NEW_TASK) }
    }
}