package com.example.cathaybankinterview.glidemodule

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.LibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader

import com.bumptech.glide.load.model.GlideUrl
import com.example.cathaybankinterview.CathyBankInterviewApplication
import com.example.cathaybankinterview.model.CAManager
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.*
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager


@GlideModule
class OkHttpLibraryGlideModule : LibraryGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val okhttpClient = createOkHttpClient()
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(okhttpClient))
    }

    private fun createOkHttpClient(): OkHttpClient {
        val application = CathyBankInterviewApplication.getInstance()
        val crtInputStream = application.assets.open("GTLSCA.crt")
        val ca = CAManager.getCAFromAssets(crtInputStream)
        val keyStore = CAManager.createKeyStoreContainCAs(ca)
        val trustManagerFactory = CAManager.createTrustManagerFactory(keyStore)

        val trustManagers = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            throw IllegalStateException(
                "Unexpected default trust managers:"
                        + Arrays.toString(trustManagers)
            )
        }
        val trustManager: X509TrustManager = trustManagers[0] as X509TrustManager

        val sslContext: SSLContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf(trustManager), null)
        val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory

        return OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustManager)
            .build()
    }
}