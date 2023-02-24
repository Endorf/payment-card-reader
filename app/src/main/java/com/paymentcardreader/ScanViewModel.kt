package com.paymentcardreader

import android.nfc.Tag
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paymentcardreader.entity.ScanState
import com.paymentcardreader.entity.mapper.toScanState
import com.paymentcardreader.reader.nfc.NFCCardReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScanViewModel : ViewModel() {

    private var nfcCardReader: NFCCardReader? = null

    val inProgressState: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    val scanState: MutableLiveData<ScanState> by lazy { MutableLiveData<ScanState>() }

    fun scan() {
        when (inProgressState.value) {
            true -> disableForegroundDispatch()
            false -> enableForegroundDispatch()
            else -> enableForegroundDispatch()
        }
    }

    fun enableForegroundDispatch() {
        inProgressState.value = true

        nfcCardReader?.enableForegroundDispatch()
    }

    fun disableForegroundDispatch() {
        inProgressState.value = false

        nfcCardReader?.disableForegroundDispatch()
    }

    fun onNewIntent(tag: Tag?) {
        viewModelScope.launch(Dispatchers.Default) {
            val result = nfcCardReader?.submitTag(tag)

            withContext(Dispatchers.Main) {
                scanState.value = result?.getOrNull()?.toScanState()
                disableForegroundDispatch()
            }
        }
    }

    fun initScanner(requireActivity: FragmentActivity) {
        nfcCardReader = NFCCardReader(requireActivity)
    }

    override fun onCleared() {
        nfcCardReader = null
        super.onCleared()
    }
}