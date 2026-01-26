package com.example.hw3

import android.net.Uri

fun drawableToUri(drawableResId: Int): Uri {
    val packageName = "com.example.hw3"
    return Uri.parse("android.resource://$packageName/$drawableResId")
}