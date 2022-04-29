package hr.ikvakan.git_hub_search.model



import java.util.*


data class UserModel(
    val avatar_url: String?,
    val url: String?,
    val html_url : String?,
    val organizations_url: String?,
    val userName: String,
    val company: String?,
    val location: String?,
    val email: String?,
    val followers: Int,
    val following: Int,
    val created_at: Date,
    val updated_at: Date,
    val public_repos: Int,
)
