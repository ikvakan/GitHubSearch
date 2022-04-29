package hr.ikvakan.git_hub_search.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.ikvakan.git_hub_search.model.UserModel
import hr.ikvakan.git_hub_search.mapper.UserModelMapperImpl
import hr.ikvakan.git_hub_search.repository.UserRepo
import hr.ikvakan.git_hub_search.retrofit.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject constructor(
    private val repository: UserRepo,
    private val userModelMapperImpl: UserModelMapperImpl
) : ViewModel() {
    private val _dataState: MutableLiveData<DataState<UserModel>> = MutableLiveData()
    val dataState: LiveData<DataState<UserModel>>
        get() = _dataState

    fun getUser(userName: String) {
        viewModelScope.launch {
            repository.getUser(userName)
                .onEach { dataState ->
                    _dataState.value = dataState
                }.launchIn(viewModelScope)
        }

    }

}