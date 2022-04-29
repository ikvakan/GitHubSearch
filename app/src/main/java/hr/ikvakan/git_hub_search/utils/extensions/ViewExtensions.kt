package hr.ikvakan.git_hub_search.utils.extensions

import android.view.View
import android.view.animation.AnimationUtils
import com.google.android.material.snackbar.Snackbar

fun View.applyAnimation(resourceId: Int) =
    startAnimation(AnimationUtils.loadAnimation(context, resourceId))

fun View.showSnackBar(message:String?, duration: Int = Snackbar.LENGTH_LONG){
    message?.let { Snackbar.make(rootView, it,duration).show() }
}
