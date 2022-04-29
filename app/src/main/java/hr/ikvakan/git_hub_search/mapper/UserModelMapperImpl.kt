package hr.ikvakan.git_hub_search.mapper

import hr.ikvakan.git_hub_search.model.UserModel
import hr.ikvakan.git_hub_search.retrofit.UserItem
import javax.inject.Inject

class UserModelMapperImpl
@Inject constructor(): ModelMapper<UserItem, UserModel> {
    override fun mapFromEntityItemToDomainModel(entityItem: UserItem): UserModel {
        return UserModel(
            entityItem.avatar_url,
            entityItem.url,
            entityItem.html_url,
            entityItem.organizations_url,
            entityItem.userName,
            entityItem.company,
            entityItem.location,
            entityItem.email,
            entityItem.followers,
            entityItem.following,
            entityItem.created_at,
            entityItem.updated_at,
            entityItem.public_repos
        )
    }

}
