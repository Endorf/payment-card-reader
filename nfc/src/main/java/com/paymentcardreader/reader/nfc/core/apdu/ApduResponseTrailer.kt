package com.paymentcardreader.reader.nfc.core.apdu

/**
 * Represents trailer (SW1 + SW2) in APDU response.
 *
 * Trailer is a part of response from ICC after APDU command was send. It shows the state of processed command.
 *
 * @property status Trailer SW1, SW2 bytes.
 */
@Suppress("MagicNumber")
internal enum class ApduResponseTrailer(
    vararg val status: Byte
) {

    /** No information given. */
    SW6500(0x65, 0x00),

    /** Process completed. */
    SW9000(0x90.toByte(), 0x00);

    companion object {
        private const val MAX_TRAILER_BYTES_COUNT = 2

        /**
         * Returns the ApduResponseTrailer. If trailer isn't identified, null will be returned.
         *
         * @param value Trailer SW1, SW2 bytes.
         */
        fun valueOf(value: ByteArray?): ApduResponseTrailer? {
            if (value == null || value.size < MAX_TRAILER_BYTES_COUNT) return null

            return values().find {
                val sw1 = value[value.size - 2]
                val sw2 = value[value.size - 1]
                it.status.size == 1 && sw1 == it.status[0] ||
                    sw1 == it.status[0] && sw2 == it.status[1]
            }
        }
    }
}