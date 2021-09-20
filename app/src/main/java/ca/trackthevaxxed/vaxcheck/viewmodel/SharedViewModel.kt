package ca.trackthevaxxed.vaxcheck.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.trackthevaxxed.vaxcheck.data.local.DataStoreRepo
import ca.trackthevaxxed.vaxcheck.model.ImmunizationStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *[SharedViewModel]
 *
 * @author Amit Metri
 */
@HiltViewModel
class SharedViewModel @Inject constructor(
    private val dataStoreRepo: DataStoreRepo
) : ViewModel() {

    val isOnBoardingShown = dataStoreRepo.isOnBoardingShown.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )
    private val _status: MutableLiveData<Pair<String, ImmunizationStatus>> = MutableLiveData()
    val status: LiveData<Pair<String, ImmunizationStatus>>
        get() = _status


    fun setStatus(status: Pair<String, ImmunizationStatus>) {
        _status.value = status
    }

    fun setOnBoardingShown(shown: Boolean) = viewModelScope.launch {
        dataStoreRepo.setOnBoardingShown(shown)
    }
}
