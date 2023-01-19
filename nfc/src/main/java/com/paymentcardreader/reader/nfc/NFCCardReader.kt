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
import android.os.Build
import android.util.Log

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

    fun onNewIntent(intent: Intent?) {
        val tag = obtainTag(intent)
        Log.d(TAG, "tag: $tag")
    }

    @Suppress("DEPRECATION")
    private fun obtainTag(intent: Intent?): Tag? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(NfcAdapter.EXTRA_TAG, Tag::class.java)
        } else {
            intent?.getParcelableExtra(NfcAdapter.EXTRA_TAG) as? Tag
        }
    }

    fun enableForegroundDispatch() {
        nfcAdapter.enableForegroundDispatch(activity, nfcAdapterIntent, INTENT_FILTER, TECH_LIST)
    }

    fun disableForegroundDispatch() {
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