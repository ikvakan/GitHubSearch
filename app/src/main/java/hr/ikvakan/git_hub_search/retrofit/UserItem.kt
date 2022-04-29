package hr.ikvakan.git_hub_search.retrofit

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserItem(
    @SerializedName("avatar_url") val avatar_url : String?,
    @SerializedName("url") val url : String?,
    @SerializedName(" html_url") val html_url : String?,
    @SerializedName("organizations_url") val organizations_url : String?,
    @SerializedName("login") val userName : String,
    @SerializedName("company") val company : String?,
    @SerializedName("location") val location : String?,
    @SerializedName("email") val email : String,
    @SerializedName("followers") val followers : Int,
    @SerializedName("following") val following : Int,
    @SerializedName("created_at") val created_at : Date,
    @SerializedName("updated_at") val updated_at : Date,
    @SerializedName("public_repos") val public_repos : Int,
)
