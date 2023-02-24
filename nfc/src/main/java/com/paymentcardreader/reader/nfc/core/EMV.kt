package com.paymentcardreader.reader.nfc.core

/**
 * List of EMV tags for communication with ICC.
 *
 * EMV stands for Europay, MasterCard, Visa, abbreviation, the companies that founded them.
 * For now it is payment standart for communication with payment chip cards.
 */
@Suppress("MagicNumber")
enum class EMV(
    vararg val bytes: Byte
) {
    /** Cryptogram returned by the ICC in response of the GENERATE AC command */
    APPLICATION_CRYPTOGRAM(0x9F.toByte(), 0x26), // 9f26

    /** Indicates the currency in which the account is managed according to ISO 4217 */
    APPLICATION_CURRENCY_CODE(0x9F.toByte(), 0x42), // 9F42

    /** Indicates the implied position of the decimal point from the right of
     * the amount represented according to ISO 4217
     */
    APPLICATION_CURRENCY_EXPONENT(0x9F.toByte(), 0x44), // 9F44

    /** Issuer or payment system specified data relating to the application */
    APPLICATION_DISCRETIONARY_DATA(0x9F.toByte(), 0x05), // 9F05

    /** Date from which the application may be used */
    APPLICATION_EFFECTIVE_DATE(0x5F, 0x25), // 5F25

    /** Date after which application expires */
    APPLICATION_EXPIRATION_DATE(0x5F, 0x24), // 5F24

    /** Indicates the location (SFI, range of records) of the AEFs related to a given application */
    APPLICATION_FILE_LOCATOR(0x94.toByte()), // 94

    /** Identifies the application as described in ISO/IEC 7816-5 */
    APPLICATION_DEDICATED_FILE(0x4F), // 4F

    /** Identifies the application as described in ISO/IEC 7816-5 */
    APPLICATION_IDENTIFIER(0x9F.toByte(), 0x06), // 9F06

    /** Indicates the capabilities of the card to support specific functions in the application */
    APPLICATION_INTERCHANGE_PROFILE(0x82.toByte()), // 82

    /** Mnemonic associated with the AID according to ISO/IEC 7816-5 */
    APPLICATION_LABEL(0x50), // 50

    /** Preferred mnemonic associated with the AID */
    APPLICATION_PREFERRED_NAME(0x9F.toByte(), 0x12), // 9F12

    /** Valid cardholder account number */
    APPLICATION_PRIMARY_ACCOUNT_NUMBER(0x5A), // 5A

    /** Identifies and differentiates cards with the same PAN */
    APPLICATION_PRIMARY_ACCOUNT_NUMBER_SEQUENCE_NUMBER(0x5F, 0x34), // 5F34

    /** Indicates the priority of a given application or group of applications in a directory */
    APPLICATION_PRIORITY_INDICATOR(0x87.toByte()), // 87

    /** 1-4 currency codes used between the terminal and the ICC when the Transaction Currency Code
     * is different from the Application Currency Code; each code is 3 digits according to ISO 4217
     */
    APPLICATION_REFERENCE_CURRENCY(0x9F.toByte(), 0x3B), // 9F3B

    /** Indicates the implied position of th e decimal point from the right of the amount,
     * for each of the 1-4 reference currencies represented according to ISO 4217
     */
    APPLICATION_REFERENCE_CURRENCY_EXPONENT(0x9F.toByte(), 0x43), // 9F43

    /** Contains one or more data objects relevant to an application directory
     * entry according to ISO/IEC 7816-5
     */
    APPLICATION_TEMPLATE(0x61), // 61

    /** Counter maintained by the application in the ICC (incrementing the ATC is managed by the ICC) */
    APPLICATION_TRANSACTION_COUNTER(0x9F.toByte(), 0x36), // 9F36

    /** Indicates issuer’s specified restrictions on the geographic usage
     * and services allowed for the application
     */
    APPLICATION_USAGE_CONTROL(0x9F.toByte(), 0x07), // 9F07

    /** Version number assigned by the payment system for the application */
    APPLICATION_VERSION_NUMBER(0x9F.toByte(), 0x08), // 9F08

    /** Uniquely identifies a bank as defined in ISO 9362 */
    BANK_IDENTIFIER_CODE(0x5F, 0x54), // 5F54

    /** List of data objects (tag and length) to be passed to the ICC in the first GENERATE AC command */
    CARD_RISK_MANAGEMENT_DATA_OBJECT_LIST_1(0x8C.toByte()), // 8C

    /** List of data objects (tag and length) to be passed to the ICC in the second GENERATE AC command */
    CARD_RISK_MANAGEMENT_DATA_OBJECT_LIST_2(0x8D.toByte()), // 8D

    /** Indicates cardholder name according to ISO 7813 */
    CARDHOLDER_NAME(0x5F, 0x20), // 5F20

    /** Indicates the whole cardholder name when greater than 26 characters using
     * the same coding convention as in ISO 7813
     */
    CARDHOLDER_NAME_EXTENDED(0x9F.toByte(), 0x0B), // 9F0B

    /** Identifies a method of verification of the cardholder supported by the application*/
    CARDHOLDER_VERIFICATION_METHOD(0x8E.toByte()), // 8E

    /** Identifies the certification authority’s public key in conjunction with the RID */
    CERTIFICATION_AUTHORITY_PUBLIC_KEY_INDEX(0X8F.toByte()), // 8F

    /** Indicates the type of cryptogram and the actions to be performed by the terminal */
    CRYPTOGRAM_INFORMATION_DATA(0x9F.toByte(), 0x27), // 9F27

    /** An issuer assigned value that is retained by the terminal during
     * the verification process of the Signed Static Application Data
     */
    DATA_AUTHENTICATION_CODE(0x9F.toByte(), 0x45), // 9F45

    /** Identifies the name of the DF as described in ISO/IEC 7816-4 */
    DEDICATED_FILE(0x84.toByte()), // 84

    /** Identifies the name of a DF associated with a directory according to ISO/IEC 7816-5 */
    DIRECTORY_DISCRETIONARY_TEMPLATE(0x73), // 73

    /** List of data objects (tag and length) to be passed
     * to the ICC in the INTERNAL AUTHENTICATE command
     */
    DYNAMIC_DATA_AUTHENTICATION_DATA_OBJECT_LIST(0x9F.toByte(), 0x49), // 9F49

    /** Issuer discretionary part of the FCI */
    FILE_CONTROL_INFORMATION_ISSUER_DISCRETIONARY_DATA(0xBF.toByte(), 0x0C), // BF0C

    /** Identifies the data object proprietary to this specification
     * in the FCI template according to ISO/IEC 7816-4
     */
    FILE_CONTROL_INFORMATION_PROPRIETARY_TEMPLATE(0xA5.toByte()), // A5

    /** Identifies the FCI template according to ISO/IEC 7816-4 */
    FILE_CONTROL_INFORMATION_TEMPLATE(0x6F), // 6F

    /** Time-variant number generated by the ICC, to be captured by the terminal */
    IIC_DYNAMIC_NUMBER(0x9F.toByte(), 0x4C), // 9F4C

    /** ICC PIN Encipherment Public Key certified by the issuer */
    INTEGRATED_CIRCUIT_CARD_PIN_ENCIPHERMENT_PUBLIC_KEY_CERTIFICATE(0x9F.toByte(), 0x2D), // 9F2D

    /** ICC PIN Encipherment Public Key Exponent used for PIN encipherment */
    INTEGRATED_CIRCUIT_CARD_PIN_ENCIPHERMENT_PUBLIC_KEY_EXPONENT(0x9f.toByte(), 0x2e), // 9F2E

    /** Remaining digits of the ICC PIN Encipherment Public Key Modulus */
    INTEGRATED_CIRCUIT_CARD_PIN_ENCIPHERMENT_PUBLIC_KEY_REMAINDER(0x9F.toByte(), 0x2F), // 9f2f

    /** ICC Public Key certified by the issuer */
    INTEGRATED_CIRCUIT_CARD_PUBLIC_KEY_CERTIFICATE(0x9F.toByte(), 0x46), // 9F46

    /** ICC Public Key Exponent used for the verification of the Signed Dynamic Application Data */
    INTEGRATED_CIRCUIT_CARD_PUBLIC_KEY_EXPONENT(0x9F.toByte(), 0x47), // 9F47

    /** Remaining digits of the ICC Public Key Modulus */
    INTEGRATED_CIRCUIT_CARD_PUBLIC_KEY_REMAINDER(0x9F.toByte(), 0x48), // 9F48

    /** Uniquely identifies the account of a customer at a financial institution as defined in ISO 13616. */
    INTERNATIONAL_BANK_ACCOUNT_NUMBER(0x5F, 0x53), // 5F53

    /** Specifies the issuer’s conditions that cause a transaction to be rejected
     * if it might have been approved online, but the terminal is unable to process the transaction online
     */
    ISSUER_ACTION_CODE_DEFAULT(0x9F.toByte(), 0x0D), // 9F0D

    /** Specifies the issuer’s conditions that cause the denial
     * of a transaction without attempt to go online
     */
    ISSUER_ACTION_CODE_DENIAL(0x9F.toByte(), 0x0E), // 9F0E

    /** Specifies the issuer’s conditions that cause a transaction to be transmitted online */
    ISSUER_ACTION_CODE_ONLINE(0x9F.toByte(), 0x0F), // 9F0F

    /** Contains proprietary application data for transmission to the issuer in an online transaction. */
    ISSUER_APPLICATION_DATA(0x9F.toByte(), 0x10), // 9F10

    /** Indicates the code table according to ISO/IEC 8859 for displaying the Application Preferred Name */
    ISSUER_CODE_TABLE_INDEX(0x9F.toByte(), 0x11), // 9F11

    /** Indicates the country of the issuer according to ISO 3166 */
    ISSUER_COUNTRY_CODE(0x5F, 0x28), // 5F28

    /** Indicates the country of the issuer as defined in ISO 3166
     * (using a 2 character alphabetic code)
     */
    ISSUER_COUNTRY_CODE_ALPHA2(0x5F, 0x55), // 5F55

    /** Indicates the country of the issuer as defined in ISO 3166
     * (using a 3 character alphabetic code)
     */
    ISSUER_COUNTRY_CODE_ALPHA3(0x5F, 0x56), // 5F56

    /** The number that identifies the major industry and the card issuer and
     * that forms the first part of the Primary Account Number (PAN)
     */
    ISSUER_IDENTIFICATION_NUMBER(0x42), // 42

    /** Issuer public key certified by a certification authority */
    ISSUER_PUBLIC_KEY_CERTIFICATE(0x90.toByte()), // 90

    /** Issuer public key exponent used for the verification
     * of the Signed Static Application Data and the ICC Public Key Certificate
     */
    ISSUER_PUBLIC_KEY_EXPONENT(0x9F.toByte(), 0x32), // 9F32

    /** Remaining digits of the Issuer Public Key Modulus */
    ISSUER_PUBLIC_KEY_REMAINDER(0x92.toByte()), // 92

    /** The URL provides the location of the Issuer’s Library Server on the Internet. */
    ISSUER_URL(0x5F, 0x50), // 5F50

    /** 1-4 languages stored in order of preference, each represented
     * by 2 alphabetical characters according to ISO 639
     */
    LANGUAGE_PREFERENCE(0x5F, 0x2D), // 5F2D

    /** ATC value of the last transaction that went online */
    LAST_ONLINE_APPLICATION_TRANSACTION_COUNTER_REGISTER(0x9F.toByte(), 0x13), // 9F13

    /** Provides the SFI of the Transaction Log file and its number of records */
    LOG_ENTRY(0x9F.toByte(), 0x4D), // 9F4D

    /** List (in tag and length format) of data objects representing the logged data elements
     * that are passed to the terminal when a transaction log record is read
     */
    LOG_FORMAT(0x9F.toByte(), 0x4F), // 9F4F

    /** Issuer-specified preference for the maximum number of consecutive offline transactions
     * for this ICC application allowed in a terminal with online capability
     */
    LOWER_CONSECUTIVE_OFFLINE_LIMIT(0x9F.toByte(), 0x14), // 9F14

    /** Number of PIN tries remaining */
    PERSONAL_IDENTIFICATION_NUMBER_TRY_COUNTER(0x9F.toByte(), 0x17), // 9F17

    /** Contains a list of terminal resident data objects (tags and lengths) needed
     * by the ICC in processing the GET PROCESSING OPTIONS command
     */
    PROCESSING_OPTIONS_DATA_OBJECT_LIST(0x9F.toByte(), 0x38), // 9F38

    /** Contains the contents of the record read. (Mandatory for SFIs 1-10.
     * Response messages for SFIs 11-30 are outside the scope of EMV, but may use template '70')
     */
    READ_RECORD_RESPONSE_MESSAGE_TEMPLATE(0x70), // 70

    /** Contains the data objects (without tags and lengths) returned by the ICC in response to a command */
    RESPONSE_MESSAGE_TEMPLATE_FORMAT_1(0x80.toByte()), // 80

    /** Contains the data objects (with tags and lengths) returned by the ICC in response to a command */
    RESPONSE_MESSAGE_TEMPLATE_FORMAT_2(0x77), // 77

    /** Service code as defined in ISO/IEC 7813 for track 1 and track 2 */
    SERVICE_CODE(0x5F, 0x30), // 5F30

    /** Identifies the AEF referenced in commands related to a given ADF or DDF.
     * It is a binary data object having a value in the range 1 to 30
     * and with the three high order bits set to zero.
     */
    SHORT_FILE_IDENTIFIER(0x88.toByte()), // 88

    /** Digital signature on critical application parameters for DDA or CDA */
    SIGNED_DYNAMIC_APPLICATION_DATA(0x9F.toByte(), 0x4B), // 9F4B

    /** Digital signature on critical application parameters for SDA */
    SIGNED_STATIC_APPLICATION_DATA(0x93.toByte()), // 93

    /** List of tags of primitive data objects defined in this specification whose value fields
     * are to be included in the Signed Static or Dynamic Application Data
     */
    STATIC_DATA_AUTHENTIFICATION_TAG_LIST(0x9F.toByte(), 0x4A), // 9F4A

    /** Discretionary part of track 1 according to ISO/IEC 7813 */
    TRACK_1_DISCRETIONARY_DATA(0x9F.toByte(), 0x1F), // 9F1F

    /** Discretionary part of track 2 according to ISO/IEC 7813 */
    TRACK_2_DISCRETIONARY_DATA(0x9F.toByte(), 0x20), // 9F20

    /** Contains the data elements of track 2 according to ISO/IEC 7813
     * (PAN, EXP_DATA, SERVICE_CODE, DISCRETIONARY_DATA)
     */
    TRACK_2_EQUIVALENT_DATA(0x57), // 57

    /** List of data objects (tag and length) to be used by the terminal in generating the TC Hash Value */
    TRANSACTION_CERTIFICATE_DATA_OBJECT_LIST(0x97.toByte()), // 97

    /** Issuer-specified preference for the maximum number of consecutive offline transactions
     * for this ICC application allowed in a terminal without online capability
     */
    UPPER_CONSECUTIVE_OFFLINE_LIMIT(0x9F.toByte(), 0x23); // 9F23

    /**
     * A constructed data object where the value field contains one or more primitive or constructed data objects.
     * The value field of a constructed data object is called a template.
     */
    fun isTemplate() = bytes.first().toInt().matchBitByBitIndex(5)

    @Suppress("FunctionNaming")
    companion object {

        fun IIN() = ISSUER_IDENTIFICATION_NUMBER
        fun AFL() = APPLICATION_FILE_LOCATOR
        fun ADF() = APPLICATION_DEDICATED_FILE
        fun PDOL() = PROCESSING_OPTIONS_DATA_OBJECT_LIST
        fun CDOL1() = CARD_RISK_MANAGEMENT_DATA_OBJECT_LIST_1
        fun CDOL2() = CARD_RISK_MANAGEMENT_DATA_OBJECT_LIST_2
        fun SFI() = SHORT_FILE_IDENTIFIER
    }
}
