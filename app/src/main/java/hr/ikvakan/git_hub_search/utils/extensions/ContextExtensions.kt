package hr.ikvakan.git_hub_search.utils

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.preference.PreferenceManager


private const val QUERY="hr.decode.decode_demo.utils_query"

inline fun<reified T : Activity> Context.startActivity() = Intent(this,T::class.java).also { startActivity(it) }

inline fun <reified T : Activity> Context.startActivity(key:String,value:String) =
    startActivity(Intent(this, T::class.java).apply { putExtra(key,value)})

inline fun<reified T : BroadcastReceiver> Context.sendBroadcast() = sendBroadcast(Intent(this,T::class.java))

fun Context.setBooleanPreference(key:String, value: Boolean) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key,value)
        .apply()

fun Context.getBooleanPreference(key:String) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key,false )

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

