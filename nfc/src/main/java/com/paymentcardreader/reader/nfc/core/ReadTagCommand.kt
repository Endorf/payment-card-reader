package com.paymentcardreader.reader.nfc.core

import android.nfc.Tag
import android.util.Log
import androidx.annotation.VisibleForTesting
import com.paymentcardreader.reader.nfc.core.apdu.PaymentEnvironment

internal class ReadTagCommand(
    tag: Tag? = null
) : Runnable {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal var provider = IsoDepProvider(tag)

    override fun run() {
        provider.connect()

        val (isSuccessful, data) = provider.select(PaymentEnvironment.PPSE.toByteArray())

        if (isSuccessful) {
            Log.d("ReadTagCommand", "scanning results: $isSuccessful, ${data?.size}")
        }
    }
}