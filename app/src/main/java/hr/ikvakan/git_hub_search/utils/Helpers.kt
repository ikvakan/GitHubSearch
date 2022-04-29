package hr.ikvakan.git_hub_search.utils

import java.text.SimpleDateFormat


fun String.formatDate(): String {
    val initialFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
    val format = SimpleDateFormat("dd-MM-yyyy")
    val date = initialFormat.parse(this)
    return format.format(date).toString()
}

