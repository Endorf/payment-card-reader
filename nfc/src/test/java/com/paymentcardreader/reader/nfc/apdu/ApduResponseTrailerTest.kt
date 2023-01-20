package com.paymentcardreader.reader.nfc.apdu

import com.paymentcardreader.reader.nfc.core.apdu.ApduResponseTrailer
import org.junit.Assert.assertEquals
import org.junit.Test

class ApduResponseTrailerTest {

    @Test
    fun retrieveApduResponse_WithValueOf() {
        assertEquals(null, ApduResponseTrailer.valueOf(null))
        assertEquals(null, ApduResponseTrailer.valueOf(NONE))
        assertEquals(ApduResponseTrailer.SW6500, ApduResponseTrailer.valueOf(SW6500))
        assertEquals(ApduResponseTrailer.SW9000, ApduResponseTrailer.valueOf(SW9000))
    }

    companion object {
        private val NONE = byteArrayOf(0x00, 0x00, 0x00, 0x01, 0x02, 0x03)
        private val SW6500 = byteArrayOf(0x65, 0x00)
        private val SW9000 = byteArrayOf(0x90.toByte(), 0x00)
    }
}