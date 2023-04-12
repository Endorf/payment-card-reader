package com.paymentcardreader.reader.nfc.exception

class TagNotRecognizedException : NullPointerException(MESSAGE) {

    companion object {
        private const val MESSAGE = "Tag not recognized"
    }
}