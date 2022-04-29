package hr.ikvakan.git_hub_search.retrofit


import retrofit2.http.GET
import retrofit2.http.Path



interface GitHubApi {
    @GET("users/{userName}")
    suspend fun getUser(@Path("userName") userName:String): UserItem

    @GET("users/{userName}/repos")
    suspend fun getRepositoryForUserName(@Path("userName") userName: String) :List<UserRepositoryItem>
}