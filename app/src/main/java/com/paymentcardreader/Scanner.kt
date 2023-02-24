package com.paymentcardreader

import android.content.Intent

interface Scanner {

    fun onNewIntent(intent: Intent?)
}