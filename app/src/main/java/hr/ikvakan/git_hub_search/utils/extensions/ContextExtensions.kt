package hr.ikvakan.git_hub_search.utils.extensions

import android.app.Activity
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.preference.PreferenceManager



inline fun<reified T : Activity> Context.startActivity() = Intent(this,T::class.java).also { startActivity(it) }

inline fun <reified T : Activity> Context.startActivity(key:String,value:String) =
    startActivity(Intent(this, T::class.java).apply { putExtra(key,value)})

fun Context.isOnline() :Boolean {
    val connectivityManager= getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork
    if (activeNetwork != null ){
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        if (networkCapabilities != null){
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    }
    return false;
}

fun Context.showToast(message:String?,duration: Int){
    Toast.makeText(this,message,duration).show()
}

fun DialogFragment.showToast(context: Context,message:String?,duration: Int){
    Toast.makeText(context,message,duration).show()

}


