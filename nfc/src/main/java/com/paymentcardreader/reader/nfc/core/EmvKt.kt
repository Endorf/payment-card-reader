package com.paymentcardreader.reader.nfc.core

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * According to ISO/IEC 8825, Table 36 defines the coding rules of the subsequent bytes of a BER-TLV
 * tag when tag numbers ≥ 31 are used (that is, bits b5 - b1 of the first byte equal '11111')
 */
private const val MAX_TAG_VALUE = 0x1F

private fun ByteArrayInputStream.readTag(): EMV? {
    val bytes = readTagIdBytes()
    return EMV.values().find {
        it.bytes.contentEquals(bytes)
    }
}

private fun ByteArrayInputStream.readTagIdBytes(): ByteArray? {
    val outputStream = ByteArrayOutputStream()
    do {
        val nextByte = read()
        outputStream.write(nextByte)
    } while (
        nextByte and MAX_TAG_VALUE == MAX_TAG_VALUE &&
        nextByte != -1 &&
        !(!nextByte.matchBitByBitIndex(7) ||
                nextByte.matchBitByBitIndex(7) && nextByte and 0x7f == 0)
    )

    return outputStream.toByteArray()
}

fun Int.matchBitByBitIndex(pBitIndex: Int): Boolean {
    return if (pBitIndex in 0..31) {
        (this and (1 shl pBitIndex)) != 0
    } else {
        false
    }
}

