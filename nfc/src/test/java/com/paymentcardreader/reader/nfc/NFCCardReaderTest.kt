package com.paymentcardreader.reader.nfc

import android.content.Intent
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class NFCCardReaderTest {

    @Test
    fun onNewIntent_withNull_TriggerOnCall() {
        val reader = mock(NFCCardReader::class.java)
        reader.onNewIntent(null)

        verify(reader).onNewIntent(null)
    }

    @Test
    fun onNewIntent_withIntent_TriggerOnCall() {
        val intent = Intent()
        val reader = mock(NFCCardReader::class.java)

        reader.onNewIntent(intent)

        verify(reader).onNewIntent(intent)
    }

    @Test
    fun disableForegroundDispatch_TriggerOnCall() {
        val reader = mock(NFCCardReader::class.java)

        reader.disableForegroundDispatch()

        verify(reader).disableForegroundDispatch()
    }

    @Test
    fun enableForegroundDispatch_TriggerOnCall() {
        val reader = mock(NFCCardReader::class.java)

        reader.enableForegroundDispatch()

        verify(reader).enableForegroundDispatch()
    }
}