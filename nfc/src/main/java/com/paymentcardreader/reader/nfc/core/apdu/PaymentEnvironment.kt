package com.paymentcardreader.reader.nfc.core.apdu

/**
 * Contact or contactless card payment system environment.
 */
internal enum class PaymentEnvironment(private val env: String) {

    /**
     * Contact card.
     * Payment System Environment (PSE)
     */
    PSE("1PAY.SYS.DDF01"),

    /**
     * Contactless card.
     * Proximity Payment System Environment (PPSE)
     */
    PPSE("2PAY.SYS.DDF01");

    /**
     * Converts env directory to byte array.
     */
    fun toByteArray() = env.toByteArray()
}
