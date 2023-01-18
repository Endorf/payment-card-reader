package com.paymentcardreader

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.paymentcardreader.reader.nfc.NFCCardReader

class MainActivity : AppCompatActivity() {

    private val nfcCardReader: NFCCardReader by lazy { NFCCardReader(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        nfcCardReader.onNewIntent(intent)
    }

    override fun onResume() {
        super.onResume()
        nfcCardReader.enableForegroundDispatch()
    }

    override fun onPause() {
        super.onPause()
        nfcCardReader.disableForegroundDispatch()
    }
}