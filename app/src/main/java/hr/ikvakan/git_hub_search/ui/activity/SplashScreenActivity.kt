package hr.ikvakan.git_hub_search.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import hr.ikvakan.git_hub_search.R
import hr.ikvakan.git_hub_search.utils.extensions.applyAnimation
import hr.ikvakan.git_hub_search.utils.extensions.isOnline
import hr.ikvakan.git_hub_search.utils.extensions.showToast
import hr.ikvakan.git_hub_search.utils.extensions.startActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DELAY: Long = 3000

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
        lifecycleScope.launch {
            delay(DELAY)
            if (isOnline()) {
                startActivity<HostActivity>()
            } else {
                showToast("Please connect to the internet", Toast.LENGTH_LONG)
                finish()
            }
        }
    }
}
