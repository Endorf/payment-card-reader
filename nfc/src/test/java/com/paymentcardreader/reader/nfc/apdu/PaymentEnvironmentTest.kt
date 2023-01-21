package com.paymentcardreader.reader.nfc.apdu

import com.paymentcardreader.reader.nfc.core.apdu.PaymentEnvironment
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class PaymentEnvironmentTest {

    @Test
    fun environmentCreation() {
        assertArrayEquals(PSE, PaymentEnvironment.PSE.toByteArray())
        assertArrayEquals(PPSE, PaymentEnvironment.PPSE.toByteArray())
    }

    companion object {
        private val PPSE = byteArrayOf(50, 80, 65, 89, 46, 83, 89, 83, 46, 68, 68, 70, 48, 49)
        private val PSE = byteArrayOf(49, 80, 65, 89, 46, 83, 89, 83, 46, 68, 68, 70, 48, 49)
    }
}