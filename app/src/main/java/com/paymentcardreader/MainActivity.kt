package com.paymentcardreader

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.paymentcardreader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        intent.takeIf { NfcAdapter.ACTION_TECH_DISCOVERED == intent?.action }
            ?.let {
                val tag = it.parcelable<Tag>(NfcAdapter.EXTRA_TAG)
                (
                    navHostFragment?.childFragmentManager
                        ?.primaryNavigationFragment as? Scanner
                    )
                    ?.onNewTag(tag)
            }
    }
}