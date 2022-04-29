package hr.ikvakan.git_hub_search.model

data class UserRepositoryModel(
    val id: Int,
    val name: String,
    val private: Boolean?,
    val owner: OwnerModel,
    val html_url: String,
    val description: String?,
    val isForked: Boolean?,
    val watchers_count: Int,
    val language: String?,
    val forks_count: Int,
   // val watchers: Int,
    val created_at: String,
    val updated_at: String,
    val open_issues_count: Int,

    ) {
    data class OwnerModel(
        val userName: String?,
        val id: Int,
        val avatar_url: String,
        val url: String,
        val html_url: String,
//        val followers_url: String,
//        val following_url: String,
//        val gists_url: String,
//        val starred_url: String,
//        val subscriptions_url: String,
//        val organizations_url: String,
//        val repos_url: String,
//        val events_url: String,
//        val received_events_url: String,
//        val type: String,
//        val site_admin: Boolean

    )
}