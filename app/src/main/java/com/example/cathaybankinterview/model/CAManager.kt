package com.example.cathaybankinterview.model

import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.InputStream
import java.net.URL
import java.security.KeyStore
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

object CAManager {

    fun getCAFromFile(fileName: String): X509Certificate {
        // Load CAs from an InputStream
        // (could be from a resource or ByteArrayInputStream or ...)
        val cf: CertificateFactory = CertificateFactory.getInstance("X.509")
        // From https://www.washington.edu/itconnect/security/ca/load-der.crt
        val caInput: InputStream = BufferedInputStream(FileInputStream(fileName))
        val ca: X509Certificate = caInput.use {
            cf.generateCertificate(it) as X509Certificate
        }
        return ca
    }

    fun getCAFromAssets(inputStream: InputStream) : X509Certificate{
        // Load CAs from an InputStream
        // (could be from a resource or ByteArrayInputStream or ...)
        val cf: CertificateFactory = CertificateFactory.getInstance("X.509")
        val caInput: InputStream = inputStream
        val ca: X509Certificate = caInput.use {
            cf.generateCertificate(it) as X509Certificate
        }
        return ca
    }

    fun createSslContext(tmf: TrustManagerFactory): SSLContext {
        val context: SSLContext = SSLContext.getInstance("TLS").apply {
            init(null, tmf.trustManagers, null)
        }
        return context
    }

    /**
     * Create a TrustManager that trusts the CAs inputStream our KeyStore
     */
    fun createTrustManagerFactory(keyStore: KeyStore): TrustManagerFactory {
        // Create a TrustManager that trusts the CAs inputStream our KeyStore
        val tmfAlgorithm: String = TrustManagerFactory.getDefaultAlgorithm()
        val trustManagerFactory: TrustManagerFactory =
            TrustManagerFactory.getInstance(tmfAlgorithm).apply {
                init(keyStore)
            }
        return trustManagerFactory
    }

    /**
     * Create an SSLContext that uses our TrustManager
     */
    fun createKeyStoreContainCAs(ca: X509Certificate): KeyStore {
        // Create a KeyStore containing our trusted CAs
        val keyStoreType = KeyStore.getDefaultType()
        val keyStore = KeyStore.getInstance(keyStoreType).apply {
            load(null, null)
            setCertificateEntry("ca", ca)
        }
        return keyStore
    }
}