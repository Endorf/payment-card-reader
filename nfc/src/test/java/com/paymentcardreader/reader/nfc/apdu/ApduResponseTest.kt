package com.paymentcardreader.reader.nfc.apdu

import com.paymentcardreader.reader.nfc.core.apdu.ApduResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class ApduResponseTest {

    @Test
    fun retrieveApduResponse_WithValueOf() {
        assertEquals(null, ApduResponse.valueOf(null))
        assertEquals(null, ApduResponse.valueOf(NONE))
        assertEquals(ApduResponse.SW6500, ApduResponse.valueOf(SW6500))
        assertEquals(ApduResponse.SW9000, ApduResponse.valueOf(SW9000))
    }

    companion object {
        private val NONE = byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x02, 0x03)
        private val SW6500 = byteArrayOf(0x65, 0x00)
        private val SW9000 = byteArrayOf(0x90.toByte(), 0x00)
    }
}