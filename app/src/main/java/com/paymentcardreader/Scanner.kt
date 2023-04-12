package com.paymentcardreader

import android.nfc.Tag

interface Scanner {

    fun onNewTag(intent: Tag?)
}