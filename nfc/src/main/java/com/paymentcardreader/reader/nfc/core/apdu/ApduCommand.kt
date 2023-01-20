package com.paymentcardreader.reader.nfc.core.apdu

//application unblock
//card block
//external authenticate (7816-4)
//generate application cryptogram
//get data (7816-4)
//get processing options
//internal authenticate (7816-4)
//PIN change / unblock
//read record (7816-4)
//select (7816-4)
//verify (7816-4)
sealed interface ApduCommand {

    val cla: Int

    val ins: Int

    val p1: Int

    val p2: Int

    val le: Int get() = 0

    val data: ByteArray

    class SELECT(vararg bytes: Byte) : ApduCommand {

        override val cla: Int = 0x00
        override val ins: Int = 0xA4
        override val p1: Int = 0x04
        override val p2: Int = 0x00

        override val data: ByteArray

        init {
            val lc = bytes.size // Lc Send Data

            data = byteArrayOf(
                cla.toByte(),
                ins.toByte(),
                p1.toByte(),
                p2.toByte(),
                lc.toByte(),
                *bytes,
                le.toByte()
            )
        }
    }

    /**
     * An APDU Read Command.
     */
    class READ(
        override val p1: Int,
        override val p2: Int,
        override val le: Int
    ) : ApduCommand {

        override val cla: Int = 0x00
        override val ins: Int = 0xB2

        override val data: ByteArray = byteArrayOf(
            cla.toByte(),
            ins.toByte(),
            p1.toByte(),
            p2.toByte(),
            le.toByte()
        )
    }

    /**
     * An APDU GPO (Get Processing Options) Command.
     */
    class GPO(vararg bytes: Byte) : ApduCommand {

        override val cla: Int = 0x80
        override val ins: Int = 0xA8
        override val p1: Int = 0x00
        override val p2: Int = 0x00

        override val data: ByteArray

        init {
            val lc = bytes.size

            data = byteArrayOf(
                cla.toByte(),
                ins.toByte(),
                p1.toByte(),
                p2.toByte(),
                lc.toByte(),
                *bytes,
                le.toByte()
            )
        }
    }
}