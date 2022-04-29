package hr.ikvakan.git_hub_search.repository

import hr.ikvakan.git_hub_search.mapper.UserModelMapperImpl
import hr.ikvakan.git_hub_search.model.UserModel
import hr.ikvakan.git_hub_search.retrofit.DataState
import hr.ikvakan.git_hub_search.retrofit.GitHubApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class UserRepo
@Inject
constructor(
    private val gitHubApi: GitHubApi,
    private val userModelMapperImpl: UserModelMapperImpl,
    ) {
    suspend fun getUser(userName: String): Flow<DataState<UserModel>> =
        flow {
            emit(DataState.Loading)
            try {
                val response = gitHubApi.getUser(userName)
                val user = userModelMapperImpl.mapFromEntityItemToDomainModel(response)
                emit(DataState.Success(user))
            } catch (e: Exception) {
                emit(DataState.Error(e))

            }
        }



}