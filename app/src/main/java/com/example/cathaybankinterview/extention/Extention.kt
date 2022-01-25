package com.example.cathaybankinterview.extention

import android.widget.ImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.net.URL
import java.net.URLConnection
import java.nio.charset.CharsetDecoder
import java.nio.charset.StandardCharsets
import kotlin.text.Charsets.UTF_8

fun ImageView.loadImageUrl(url: String){
    val httpsUrl = url.replace("http","https")
    Glide.with(this)
        .load(httpsUrl)
        .into(this)
}