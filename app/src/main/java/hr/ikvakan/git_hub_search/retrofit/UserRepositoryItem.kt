package hr.ikvakan.git_hub_search.retrofit

import com.google.gson.annotations.SerializedName

data class UserRepositoryItem(
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String,
    @SerializedName("private") val private : Boolean?,
    @SerializedName("owner") val owner : OwnerItem,
    @SerializedName("html_url") val html_url : String,
    @SerializedName("description") val description : String?,
    @SerializedName("fork") val isForked : Boolean?,
    @SerializedName("watchers_count") val watchers_count : Int,
    @SerializedName("language") val language : String?,
    @SerializedName("forks_count") val forks_count : Int,
   // @SerializedName("watchers") val watchers : Int,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("open_issues_count") val open_issues_count : Int,

    ){
    data class OwnerItem(
        @SerializedName("login") val userName : String?,
        @SerializedName("id") val id : Int,
        @SerializedName("avatar_url") val avatar_url : String,
        @SerializedName("url") val url : String,
        @SerializedName("html_url") val html_url : String,
//        @SerializedName("followers_url") val followers_url : String,
//        @SerializedName("following_url") val following_url : String,
//        @SerializedName("gists_url") val gists_url : String,
//        @SerializedName("starred_url") val starred_url : String,
//        @SerializedName("subscriptions_url") val subscriptions_url : String,
//        @SerializedName("organizations_url") val organizations_url : String,
//        @SerializedName("repos_url") val repos_url : String,
//        @SerializedName("events_url") val events_url : String,
//        @SerializedName("received_events_url") val received_events_url : String,
//        @SerializedName("type") val type : String,
//        @SerializedName("site_admin") val site_admin : Boolean

    )
}