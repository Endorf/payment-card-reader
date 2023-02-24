package com.paymentcardreader.reader.nfc.core.util

fun ByteArray.toHex() = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }