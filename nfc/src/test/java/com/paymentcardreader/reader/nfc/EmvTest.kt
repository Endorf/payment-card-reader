package com.paymentcardreader.reader.nfc

import com.paymentcardreader.reader.nfc.core.EMV
import com.paymentcardreader.reader.nfc.core.asString
import com.paymentcardreader.reader.nfc.core.extractTLV
import org.junit.Assert.assertEquals
import org.junit.Test

class EmvTest {

    @Test
    fun detect_ApplicationLabel_asString() {
        val mastercardLabel = APPLICATION_LABEL_DEBIT_MASTERCARD.extractTLV(EMV.APPLICATION_LABEL).asString()
        assertEquals(MASTERCARD, mastercardLabel)
        val visaLabel = APPLICATION_LABEL_VISA.extractTLV(EMV.APPLICATION_LABEL).asString()
        assertEquals(VISA, visaLabel)
        val visa2Label = APPLICATION_LABEL_VISA_CLASSIC.extractTLV(EMV.APPLICATION_LABEL).asString()
        assertEquals(VISA_CLASSIC, visa2Label)
    }

    companion object {
        private val APPLICATION_LABEL_DEBIT_MASTERCARD = byteArrayOf(80, 16, 68, 101, 98, 105, 116, 32, 77, 97, 115, 116, 101, 114, 67, 97, 114, 100, -121, 1, 1, -112, 0)
        private const val MASTERCARD = "Debit MasterCard"
        private val APPLICATION_LABEL_VISA = byteArrayOf(80, 4, 86, 105, 115, 97)
        private const val VISA = "Visa"
        private val APPLICATION_LABEL_VISA_CLASSIC = byteArrayOf(80, 12, 86, 105, 115, 97, 32, 67, 108, 97, 115, 115, 105, 99)
        private const val VISA_CLASSIC = "Visa Classic"
    }
}