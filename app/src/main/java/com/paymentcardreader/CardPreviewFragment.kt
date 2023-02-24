package com.paymentcardreader

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.paymentcardreader.databinding.FragmentCardInfoPreviewBinding

class CardPreviewFragment : Fragment(), Scanner {

    private lateinit var binding: FragmentCardInfoPreviewBinding
    private val viewModel: ScanViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel.initScanner(requireActivity())
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_card_info_preview, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onNewIntent(intent: Intent?) {
        viewModel.onNewIntent(intent)
    }
}