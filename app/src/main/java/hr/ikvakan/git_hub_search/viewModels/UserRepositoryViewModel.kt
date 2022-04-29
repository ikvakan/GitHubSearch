package hr.ikvakan.git_hub_search.viewModels


import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ikvakan.git_hub_search.model.UserRepositoryModel
import hr.ikvakan.git_hub_search.repository.UserRepositoryRepo

import hr.ikvakan.git_hub_search.retrofit.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepositoryViewModel
@Inject
constructor(
    private val repository: UserRepositoryRepo

) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<UserRepositoryModel>>> =
        MutableLiveData()
    val dataState: LiveData<DataState<List<UserRepositoryModel>>>
        get() = _dataState

    fun getUserRepositoriesByUserName(userName: String) {
        viewModelScope.launch {
            repository.getRepositoryForUserName(userName)
                .onEach { dataState ->
                    _dataState.value = dataState
                }.launchIn(viewModelScope)
        }

    }


}




sealed class UserRepositoryStateEvent {
    object GetUserRepositories : UserRepositoryStateEvent()

}


