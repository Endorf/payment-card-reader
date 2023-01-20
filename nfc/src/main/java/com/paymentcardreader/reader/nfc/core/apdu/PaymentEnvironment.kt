package com.paymentcardreader.reader.nfc.core.apdu

enum class PaymentEnvironment(private val env: String) {

    /**
     * Contact card
     * Payment System Environment (PSE)
     */
    PSE("1PAY.SYS.DDF01"),

    /**
     * Contactless card
     * Proximity Payment System Environment (PPSE)
     */
    PPSE("2PAY.SYS.DDF01");

    fun toByteArray() = env.toByteArray()
}
