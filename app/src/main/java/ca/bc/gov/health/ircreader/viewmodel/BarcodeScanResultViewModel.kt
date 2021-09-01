package ca.bc.gov.health.ircreader.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.bc.gov.health.ircreader.model.SHCData
import ca.bc.gov.health.ircreader.utils.PayLoadProcessor

class BarcodeScanResultViewModel : ViewModel() {

    private val userName: MutableLiveData<String> = MutableLiveData()
    private val vaccinationStatus: MutableLiveData<Int> = MutableLiveData()

    fun observeUserName(): MutableLiveData<String> {
        return userName
    }

    fun observeVaccinationStatus(): MutableLiveData<Int> {
        return vaccinationStatus
    }

    fun processShcUri(shcData: SHCData) {
        userName.value = PayLoadProcessor().fetchName(shcData)
        vaccinationStatus.value = 2 // TODO: 01/09/21 hard coded value for testing
    }
}
