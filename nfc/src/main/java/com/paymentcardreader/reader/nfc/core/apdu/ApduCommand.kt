package com.paymentcardreader.reader.nfc.core.apdu

/**
 * An Application Protocol Data Unit (APDU).
 * The communication unit between a smart card reader and a smart card.
 *
 * Command structure:
 * Header: CLA, INS, P1, P2
 * Body: Lc, CommandData, Le
 *
 * @property CLA Class Byte of the Command Message
 * @property INS Instruction Byte of Command Message
 * @property P1 Parameter 1. Record number. If not used, a parameter byte has the value 0x00.
 * @property P2 Parameter 2. If not used, a parameter byte has the value 0x00.
 * @property Lc The length of command data field.
 * @property Le The expected bytes in response. In case Le present and contains 0x00,
 * the maximum number of available data bytes (up to 256) is expected. In command body Le must be set to 0x00
 *
 *
 * NOTE: The class has implementation of three commands which are using in current project.
 */
@Suppress("MagicNumber")
internal sealed interface ApduCommand {

    val cla: Int

    val ins: Int

    val p1: Int

    val p2: Int

    val le: Int get() = 0

    val data: ByteArray

    /**
     * Select command.
     * Command selects information item from ICC or a field (Tag).
     *
     * @property bytes Data bytes.
     */
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
                lc.toByte()
            ) + bytes + le.toByte()
        }
    }

    /**
     * Read Record command.
     * Command requests to read a record from ICC. The response contains the record.
     *
     * @property P1 Record number (Parameter 1)
     * @property P2 Reference control parameter (Parameter 2)
     * @property Le The expected bytes in response.
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
     * Get processing options (GPO) command.
     * Command initiates the transaction with the ICC.
     *
     * @property bytes Processing Options Data Object List (PDOL) related data.
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
                lc.toByte()
            ) + bytes + le.toByte()
        }
    }
}