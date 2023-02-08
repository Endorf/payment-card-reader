package com.paymentcardreader.reader.nfc

import android.annotation.SuppressLint
import android.app.Activity
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.nfc.tech.NfcA
import android.util.Log
import com.paymentcardreader.reader.nfc.core.ReadTagCommand
import com.paymentcardreader.reader.nfc.util.parcelable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

@SuppressLint("UnspecifiedImmutableFlag")
class NFCCardReader(
    private val activity: Activity
) {

    private val nfcAdapter: NfcAdapter by lazy { NfcAdapter.getDefaultAdapter(activity) }

    private val nfcAdapterIntent: PendingIntent by lazy {
        with(Intent(activity, activity.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)) {
            PendingIntent.getActivity(activity, 0, this, 0)
        }
    }

    private var readTask: Future<*>? = null
    private val readTaskExecutor: ExecutorService = Executors.newSingleThreadExecutor()

    fun onNewIntent(intent: Intent?) {
        val tag = intent?.parcelable<Tag>(NfcAdapter.EXTRA_TAG)
        readTaskExecutor.submit(ReadTagCommand(tag))
        Log.d(TAG, "tag: $tag")
    }

    fun enableForegroundDispatch() {
        nfcAdapter.enableForegroundDispatch(activity, nfcAdapterIntent, INTENT_FILTER, TECH_LIST)
    }

    fun disableForegroundDispatch() {

        readTask?.cancel(true)
        nfcAdapter.disableForegroundDispatch(activity)
    }

    companion object {

        private const val TAG = "NFCCardReader"

        private val TECH_LIST = arrayOf(arrayOf(NfcA::class.java.name, IsoDep::class.java.name))

        private val INTENT_FILTER = arrayOf(
            IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED),
            IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        )
    }
}