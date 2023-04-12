package com.paymentcardreader.reader.nfc.core

import android.nfc.Tag
import android.util.Log
import androidx.annotation.VisibleForTesting
import com.paymentcardreader.reader.nfc.core.apdu.PaymentEnvironment
import com.paymentcardreader.reader.nfc.core.util.toHex
import com.paymentcardreader.reader.nfc.entity.ScanResult
import com.paymentcardreader.reader.nfc.exception.TagNotRecognizedException

class ReadTagCallable(
    tag: Tag? = null
) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal var provider = IsoDepProvider(tag)

    suspend fun call(): Result<ScanResult> {
        provider.connect()

        val (isSuccessful, data) = provider.select(PaymentEnvironment.PPSE.toByteArray())

        return if (isSuccessful) {
            val label = extractLabel(data)
            val aid = selectADF(data)

            return Result.success(ScanResult(label, aid))
        } else {
            Result.failure(TagNotRecognizedException())
        }
    }

    private fun selectADF(data: ByteArray?): String {
        val aid = data.extractTLV(EMV.ADF())
        val (_, adf) = provider.select(aid)

        Log.d(
            "ReadTagCommand",
            """
            AID: ${aid.toHex()} ( ${aid.contentToString()} )
            ADF select: ${adf.contentToString()}
            """.trimIndent()
        )

        return aid.toHex()
    }

    private fun extractLabel(data: ByteArray?) =
        data.extractTLV(EMV.APPLICATION_LABEL).asString().also {
            Log.d("ReadTagCommand", "Application Label: $it")
        }
}