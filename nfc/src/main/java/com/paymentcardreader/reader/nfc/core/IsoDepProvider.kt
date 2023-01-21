package com.paymentcardreader.reader.nfc.core

import android.nfc.Tag
import android.nfc.tech.IsoDep
import androidx.annotation.VisibleForTesting
import com.paymentcardreader.reader.nfc.core.apdu.ApduCommand
import com.paymentcardreader.reader.nfc.core.apdu.ApduResponseTrailer

/**
 * Provides simple interface for communication with NFC devices using ISO-DEP protocol.
 */
internal class IsoDepProvider(
    tag: Tag? = null
) {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal var isoDep = IsoDep.get(tag)

    /**
     * Initiates communication with NFC devices.
     */
    fun connect() {
        isoDep.connect()
    }

    private fun transceive(command: ByteArray) = isoDep?.transceive(command) ?: ByteArray(0)

    /**
     * Initiates Select command.
     */
    fun select(data: ByteArray) = parseResult { transceive(ApduCommand.SELECT(bytes = data).data) }

    /**
     * Initiates GPO command.
     */
    fun gpo(data: ByteArray) = parseResult { transceive(ApduCommand.GPO(bytes = data).data) }

    /**
     * Initiates Read command.
     */
    fun read(p1: Int, p2: Int, le: Int) =
        parseResult { transceive(ApduCommand.READ(p1, p2, le).data) }

    private inline fun parseResult(transceiveAction: () -> ByteArray?) = with(transceiveAction()) {
        Result(
            ApduResponseTrailer.valueOf(this) == ApduResponseTrailer.SW9000, this
        )
    }

    /**
     * Class represents results of operations with NFC device.
     */
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
}
