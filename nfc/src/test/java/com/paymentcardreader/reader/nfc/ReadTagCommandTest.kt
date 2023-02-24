package com.paymentcardreader.reader.nfc

import com.paymentcardreader.reader.nfc.core.IsoDepProvider
import com.paymentcardreader.reader.nfc.core.ReadTagCommand
import com.paymentcardreader.reader.nfc.core.apdu.PaymentEnvironment
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ReadTagCommandTest {

    private val command: ReadTagCommand by lazy {
        ReadTagCommand().apply {
            provider = mock(IsoDepProvider::class.java).apply {
                `when`(select(TEST_PAYMENT_ENV))
                    .thenReturn(
                        IsoDepProvider.Result(true, TEST_RESPONSE)
                    )
            }
        }
    }

    @Test
    fun onRunCommand_connectIsoDepProvider() {
        command.run()

        Mockito.verify(command.provider).connect()
        Mockito.verify(command.provider).select(TEST_PAYMENT_ENV)
    }

    companion object {
        private val TEST_PAYMENT_ENV = PaymentEnvironment.PPSE.toByteArray()
        private val TEST_RESPONSE = byteArrayOf(1, 2, 3, 4, 5, 6, 7)
    }
}