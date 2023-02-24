package com.paymentcardreader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.paymentcardreader.databinding.FragmentCardInfoPreviewBinding

class CardPreviewFragment : Fragment() {

    private lateinit var binding: FragmentCardInfoPreviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_card_info_preview, container, false)
//        binding.lifecycleOwner = this

        return binding.root
    }
}