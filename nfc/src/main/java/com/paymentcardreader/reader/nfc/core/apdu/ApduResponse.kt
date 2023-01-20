package com.paymentcardreader.reader.nfc.core.apdu

enum class ApduResponse(
    vararg val status: Byte
) {

    SW6500(0x65, 0x00),
    SW9000(0x90.toByte(), 0x00);

    companion object {
        fun valueOf(value: ByteArray?): ApduResponse? {
            if (value == null) return null

            return values().find {
                val sw1 = value[value.size - 2]
                val sw2 = value[value.size - 1]
                it.status.size == 1 && sw1 == it.status[0]
                        || sw1 == it.status[0] && sw2 == it.status[1]
            }
        }
    }
}