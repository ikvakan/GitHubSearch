package hr.ikvakan.git_hub_search.repository

import hr.ikvakan.git_hub_search.model.UserRepositoryModel
import hr.ikvakan.git_hub_search.retrofit.GitHubApi
import hr.ikvakan.git_hub_search.mapper.UserRepositoryModelMapperImpl
import hr.ikvakan.git_hub_search.retrofit.DataState
import hr.ikvakan.git_hub_search.retrofit.UserRepositoryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryRepo
@Inject
constructor(
    private val gitHubApi: GitHubApi,
    private val userRepositoryModelMapperImpl: UserRepositoryModelMapperImpl,
    ) {
    suspend fun getRepositoryForUserName(userName: String): Flow<DataState<MutableList<UserRepositoryModel>>> =
        flow {
            emit(DataState.Loading)
            try {
                val response = gitHubApi.getRepositoryForUserName(userName)
                val userRepositoryList =
                    userRepositoryModelMapperImpl.mapFromEntityItemToDomainModelList(response)
                emit(DataState.Success(userRepositoryList))
            } catch (e: Exception) {
                emit(DataState.Error(e))
            }
        }
}