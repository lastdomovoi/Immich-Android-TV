package nl.giejay.android.tv.immich.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient

object ApiClientFactory {

    // Keep the upstream call signature for now; neither debug logging nor TLS bypass is
    // available in the private build, even when an old preference says otherwise.
    fun getClient(disableSsl: Boolean, apiKey: String, debugMode: Boolean): OkHttpClient {
        val apiKeyInterceptor = interceptor(apiKey)
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(apiKeyInterceptor)
        return builder.build()
    }

    private fun interceptor(apiKey: String): Interceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("x-api-key", apiKey.trim())
            .build()
        chain.proceed(newRequest)
    }
}
