package com.paymentcardreader.entity.mapper

import com.paymentcardreader.reader.nfc.entity.ScanResult
import com.paymentcardreader.entity.ScanState

fun ScanResult.toScanState() = ScanState(applicationLabel, aid)