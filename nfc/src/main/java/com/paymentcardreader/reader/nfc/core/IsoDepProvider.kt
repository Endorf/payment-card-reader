package com.paymentcardreader.reader.nfc.core

import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.util.Log
import androidx.annotation.VisibleForTesting
import com.paymentcardreader.reader.nfc.core.apdu.ApduCommand
import com.paymentcardreader.reader.nfc.core.apdu.ApduResponse
import java.io.IOException

internal class IsoDepProvider(
    tag: Tag? = null
) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal var isoDep = IsoDep.get(tag)

    fun connect() {
        isoDep.connect()
    }

    private fun transceive(command: ByteArray): ByteArray? {
        return try {
            isoDep?.transceive(command)
        } catch (e: IOException) {
            Log.e(TAG, e.printStackTrace().toString())
            null
        }
    }

    private inline fun parseResult(transceiveAction: () -> ByteArray?) = with(transceiveAction()) {
        Result(
            ApduResponse.valueOf(this) == ApduResponse.SW9000, this
        )
    }

    fun select(data: ByteArray) = parseResult { transceive(ApduCommand.SELECT(*data).data) }

    fun gpo(data: ByteArray) = parseResult { transceive(ApduCommand.GPO(*data).data) }

    fun read(p1: Int, p2: Int, le: Int) =
        parseResult { transceive(ApduCommand.READ(p1, p2, le).data) }

    data class Result(val isSuccessful: Boolean, val data: ByteArray? = null) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Result

            if (isSuccessful != other.isSuccessful) return false
            if (!data.contentEquals(other.data)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = isSuccessful.hashCode()
            result = 31 * result + data.contentHashCode()
            return result
        }

    }

    companion object {
        private const val TAG = "IsoDepProvider"
    }
}
