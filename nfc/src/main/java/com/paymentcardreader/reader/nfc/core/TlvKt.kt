package com.paymentcardreader.reader.nfc.core

import java.nio.charset.Charset

/**
 * Convert ByteArray of TLV value into String.
 */
fun ByteArray?.asString(charset: Charset = Charsets.UTF_8) =
    this?.run { String(this, charset) } ?: ""
