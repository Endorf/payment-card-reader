package com.paymentcardreader.reader.nfc

import android.nfc.tech.IsoDep
import com.paymentcardreader.reader.nfc.core.IsoDepProvider
import org.junit.Test
import org.mockito.Mockito.*

class IsoDepProviderTest {

    private val provider: IsoDepProvider by lazy {
        IsoDepProvider().apply {
            isoDep = mock(IsoDep::class.java)
        }
    }

    @Test
    fun isoDepProvider_CallConnect() {
        provider.connect()

        verify(provider.isoDep).connect()
    }

    @Test
    fun isoDepProvider_CallGPO() {
        provider.connect()

        provider.gpo(byteArrayOf(-1))

        verify(provider.isoDep).transceive(GPO_RESULT)
    }

    @Test
    fun isoDepProvider_CallSelect() {
        provider.connect()

        provider.select(byteArrayOf(-1))

        verify(provider.isoDep).transceive(SELECT_RESULT)
    }

    @Test
    fun isoDepProvider_CallRead() {
        provider.connect()

        provider.read(-1, -1, -1)

        verify(provider.isoDep).transceive(READ_RESULT)
    }

    companion object {
        private val READ_RESULT =
            byteArrayOf(0x00, 0xB2.toByte(), 0xFF.toByte(), 0xFF.toByte(), 0xFF.toByte())
        private val GPO_RESULT =
            byteArrayOf(0x80.toByte(), 0xA8.toByte(), 0x00, 0x00, 0x01, 0xFF.toByte(), 0x00)
        private val SELECT_RESULT =
            byteArrayOf(0x00, 0xA4.toByte(), 0x04, 0x00, 0x01, 0xFF.toByte(), 0x00)
    }
}