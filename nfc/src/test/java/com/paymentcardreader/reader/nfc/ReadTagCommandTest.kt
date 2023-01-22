package com.paymentcardreader.reader.nfc

import com.paymentcardreader.reader.nfc.core.IsoDepProvider
import com.paymentcardreader.reader.nfc.core.ReadTagCommand
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any

class ReadTagCommandTest {

    private val command: ReadTagCommand by lazy {
        ReadTagCommand().apply {
            provider = Mockito.mock(IsoDepProvider::class.java).apply {
                `when`(select(any()))
                    .thenReturn(
                        IsoDepProvider.Result(true, ByteArray(0))
                    )
            }
        }
    }

    @Test
    fun onRunCommand_connectIsoDepProvider() {
        command.run()

        Mockito.verify(command.provider).connect()
        Mockito.verify(command.provider).select(any())
    }
}