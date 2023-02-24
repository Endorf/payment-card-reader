package com.paymentcardreader

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.paymentcardreader.entity.ScanState
import com.paymentcardreader.reader.nfc.NFCCardReader

class ScanViewModel : ViewModel() {

    private var nfcCardReader: NFCCardReader? = null

    val inProgressState: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>(false) }

    val scanState: MutableLiveData<ScanState> by lazy { MutableLiveData<ScanState>() }

    fun scan() {
        enableForegroundDispatch()
    }

    fun enableForegroundDispatch() {
        inProgressState.value = true

        nfcCardReader?.enableForegroundDispatch()
    }

    fun disableForegroundDispatch() {
        inProgressState.value = false

        nfcCardReader?.disableForegroundDispatch()
    }

    fun onNewIntent(intent: Intent?) {
        nfcCardReader?.onNewIntent(intent)
    }

    fun initScanner(requireActivity: FragmentActivity) {
        nfcCardReader = NFCCardReader(requireActivity)
    }

    override fun onCleared() {
        nfcCardReader = null
        super.onCleared()
    }
}