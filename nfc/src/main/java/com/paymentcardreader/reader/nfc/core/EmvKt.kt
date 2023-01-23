package com.paymentcardreader.reader.nfc.core

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset

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

internal fun Int.matchBitByBitIndex(pBitIndex: Int): Boolean {
    return if (pBitIndex in 0..31) {
        (this and (1 shl pBitIndex)) != 0
    } else {
        false
    }
}

private fun ByteArrayInputStream.readTagLength(): Int {
    var tagLength: Int = read()

    if (tagLength.isOneByteLength().not()) {
        val length = tagLength and 127 // turn off 8th bit
        tagLength = 0
        for (i in 0 until length) {
            val firstByte = tagLength shl 8
            val secondByte: Int = read()

            tagLength = firstByte or secondByte
        }
    }
    return tagLength
}

/**
 * When bit b8 of the most significant byte of the length field is set to 0,
 * the length field consists of only one byte.
 * Bits b7 to b1 code the number of bytes of the value field. The length field is within the range 1 to 127.
 *
 * When bit b8 of the most significant byte of the length field is set to 1,
 * the subsequent bits b7 to b1 of the most significant byte code the number of subsequent bytes in the length field.
 * The subsequent bytes code an integer representing the number of bytes in the value field.
 * Two bytes are necessary to express up to 255 bytes in the value field.
 */
private fun Int.isOneByteLength(): Boolean = (this shr 8) == 0

private fun ByteArrayInputStream.readBody(length: Int): ByteArray {
    val realLength: Int = available().takeIf { it < length } ?: length
    return ByteArray(length).apply {
        read(this, 0, realLength)
    }
}

/**
 * fixme: add documentation
 */
fun ByteArray?.extractTLV(vararg emv: EMV): ByteArray {
    return this?.inputStream()?.let {
        do {
            it.ignoreEmptyBits()

            val tag = it.readTag()
            val len = it.readTagLength()
            val body = it.readBody(len)

            when {
                tag == null -> continue
                emv.contains(tag) -> return body
                tag.isTemplate() -> with(body.extractTLV(*emv)) {
                    if (isNotEmpty()) return@let this
                }
            }
        } while (it.available() > 0)

        return ByteArray(0)
    } ?: ByteArray(0)
}

/**
 * 0x00 or 0xff should be ignored between TLV items.
 *
 * Before, between, or after TLV-coded data objects,
 * '00' or 'FF' bytes without any meaning may occur (for example, due to erased or modified TLV-coded data objects).
 * It is strongly recommended that issuers do not use tags beginning with ‘FF’ for proprietary purposes.
 */
private fun ByteArrayInputStream.ignoreEmptyBits() {
    mark(0)
    var byte = read().toByte()
    while (byte == 0xFF.toByte() || byte == 0x00.toByte()) {
        mark(0)
        byte = read().toByte()
    }
    reset()
}
