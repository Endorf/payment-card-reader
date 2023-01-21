package com.paymentcardreader.reader.nfc.apdu

import com.paymentcardreader.reader.nfc.core.apdu.ApduCommand
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class ApduCommandTest {

    @Test
    fun apduCommandCreation_Select() {
        val userData = byteArrayOf(0x11, 0x12, 0x21, 0x22)
        val leByte: Byte = 0x00
        val lcByte = byteArrayOf(userData.size.toByte())
        val selectCommand = ApduCommand.SELECT(*userData).data

        val resultArray = SELECT + lcByte + userData + leByte

        assertArrayEquals(resultArray, selectCommand)
    }

    @Test
    fun apduCommandCreation_Read() {
        val userDataP1 = 1
        val userDataP2 = 2
        val userDataLe = 3
        val readCommand = ApduCommand.READ(userDataP1, userDataP2, userDataLe).data

        val resultArray = READ + userDataP1.toByte() + userDataP2.toByte() + userDataLe.toByte()

        assertArrayEquals(resultArray, readCommand)
    }

    @Test
    fun apduCommandCreation_GPO() {
        val userData = byteArrayOf(0x11, 0x12, 0x21, 0x22)
        val leByte: Byte = 0x00
        val lcByte = byteArrayOf(userData.size.toByte())
        val gpoCommand = ApduCommand.GPO(*userData).data

        val resultArray = GPO + lcByte + userData + leByte

        assertArrayEquals(resultArray, gpoCommand)
    }

    companion object {
        private val SELECT =
            byteArrayOf(0x00, 0xA4.toByte(), 0x04, 0x00)
        private val READ =
            byteArrayOf(0x00, 0xB2.toByte())
        private val GPO =
            byteArrayOf(0x80.toByte(), 0xA8.toByte(), 0x00, 0x00)
    }
}