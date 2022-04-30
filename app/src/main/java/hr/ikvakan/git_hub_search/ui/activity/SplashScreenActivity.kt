package hr.ikvakan.git_hub_search.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import hr.ikvakan.git_hub_search.GitHubAppService
import hr.ikvakan.git_hub_search.R
import hr.ikvakan.git_hub_search.utils.extensions.applyAnimation
import hr.ikvakan.git_hub_search.utils.getBooleanPreference
import hr.ikvakan.git_hub_search.utils.isOnline
import hr.ikvakan.git_hub_search.utils.startActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*

private const val DELAY: Long = 3000
const val DATA_IMPORTED = "hr.decode.decode_demo_data_imported"

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startAnimation()
        redirect()
    }

    private fun startAnimation() {
        tvSplash.applyAnimation(R.anim.blink)
    }

    private fun redirect() {
        if (getBooleanPreference(DATA_IMPORTED)) {
            Handler(Looper.getMainLooper()).postDelayed(
                { startActivity<HostActivity>() }, DELAY
            )
        } else{
            if (isOnline()){
                Intent(this, GitHubAppService::class.java).apply {
                    GitHubAppService.enqueueWork(this@SplashScreenActivity, this)
                }
            }else{
                Toast.makeText(this,"Please connect to the internet",Toast.LENGTH_LONG).show()
                finish()
            }
        }

    }
}