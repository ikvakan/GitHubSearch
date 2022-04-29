package hr.ikvakan.git_hub_search

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService
import hr.ikvakan.git_hub_search.utils.sendBroadcast

private const val JOB_ID=1

class GitHubAppService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        Thread.sleep(6000)

        sendBroadcast<GitHubReceiver>()

    }

    companion object{
        fun enqueueWork(context:Context,intent: Intent){
            enqueueWork(context,GitHubAppService::class.java,JOB_ID,intent)
        }
    }
}