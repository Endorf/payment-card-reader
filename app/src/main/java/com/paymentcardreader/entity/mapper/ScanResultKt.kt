package com.paymentcardreader.entity.mapper

import com.paymentcardreader.entity.ScanState
import com.paymentcardreader.reader.nfc.entity.ScanResult

fun ScanResult.toScanState() = ScanState(applicationLabel, aid)