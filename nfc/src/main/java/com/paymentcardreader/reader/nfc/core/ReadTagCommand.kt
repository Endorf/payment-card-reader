package com.paymentcardreader.reader.nfc.core

import android.nfc.Tag
import android.util.Log
import androidx.annotation.VisibleForTesting
import com.paymentcardreader.reader.nfc.core.apdu.PaymentEnvironment
import com.paymentcardreader.reader.nfc.core.util.toHex

/**
 * Communicate and parse EMV public card data.
 */
internal class ReadTagCommand(
    tag: Tag? = null
) : Runnable {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal var provider = IsoDepProvider(tag)

    override fun run() {
        provider.connect()

        val (isSuccessful, data) = provider.select(PaymentEnvironment.PPSE.toByteArray())

        if (isSuccessful) {
            extractLabel(data)
            selectADF(data)
        }
    }

    private fun selectADF(data: ByteArray?) {
        val aid = data.extractTLV(EMV.ADF())
        val (isSuccessful, adf) = provider.select(aid)

        Log.d(
            "ReadTagCommand", """
            AID: ${aid.toHex()} ( ${aid.contentToString()} )
            ADF select: ${adf.contentToString()}
        """.trimIndent()
        )
    }

    private fun extractLabel(data: ByteArray?) {
        val label = data.extractTLV(EMV.APPLICATION_LABEL)
        Log.d("ReadTagCommand", "Application Label: ${label.asString()}")
    }
}