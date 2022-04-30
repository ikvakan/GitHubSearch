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
    )
}