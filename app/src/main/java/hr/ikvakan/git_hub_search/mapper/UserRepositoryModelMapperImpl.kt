package hr.ikvakan.git_hub_search.mapper

import hr.ikvakan.git_hub_search.model.UserRepositoryModel
import hr.ikvakan.git_hub_search.retrofit.UserRepositoryItem
import javax.inject.Inject

class UserRepositoryModelMapperImpl
@Inject
constructor() : ModelMapper<UserRepositoryItem, UserRepositoryModel> {
    override fun mapFromEntityItemToDomainModel(entityItem: UserRepositoryItem): UserRepositoryModel {
        return UserRepositoryModel(
            id = entityItem.id,
            name = entityItem.name,
            private = entityItem.private,
            UserRepositoryModel.OwnerModel(
                userName = entityItem.owner.userName,
                id = entityItem.owner.id,
                avatar_url = entityItem.owner.avatar_url,
                url = entityItem.owner.avatar_url,
                html_url = entityItem.owner.html_url,
                ),
            html_url = entityItem.html_url,
            description = entityItem.description,
            isForked = entityItem.isForked,
            watchers_count = entityItem.watchers_count,
            language = entityItem.language,
            forks_count = entityItem.forks_count,
            created_at = entityItem.created_at,
            updated_at = entityItem.updated_at,
            open_issues_count = entityItem.open_issues_count
        )
    }

    fun mapFromEntityItemToDomainModelList(entityItems:MutableList<UserRepositoryItem>) : MutableList<UserRepositoryModel> {
        return entityItems.map { mapFromEntityItemToDomainModel(it) } as MutableList<UserRepositoryModel>

    }
}