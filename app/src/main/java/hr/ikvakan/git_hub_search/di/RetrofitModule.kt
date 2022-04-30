package hr.ikvakan.git_hub_search.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import hr.ikvakan.git_hub_search.mapper.UserModelMapperImpl
import hr.ikvakan.git_hub_search.repository.UserRepositoryRepo


import hr.ikvakan.git_hub_search.retrofit.GitHubApi
import hr.ikvakan.git_hub_search.mapper.UserRepositoryModelMapperImpl
import hr.ikvakan.git_hub_search.repository.UserRepo
import hr.ikvakan.git_hub_search.utils.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun provideRetrofitInstance() :GitHubApi = Retrofit.Builder()
        .baseUrl(Constants.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GitHubApi::class.java)

    @Singleton
    @Provides
    fun provideRepositoryRepo(gitHubApi: GitHubApi,userRepositoryModelMapperImpl: UserRepositoryModelMapperImpl) : UserRepositoryRepo {
        return UserRepositoryRepo(gitHubApi,userRepositoryModelMapperImpl)
    }

    @Singleton
    @Provides
    fun provideUserRepo(gitHubApi: GitHubApi,userModelMapperImpl: UserModelMapperImpl) : UserRepo {
        return UserRepo(gitHubApi,userModelMapperImpl)
    }
}