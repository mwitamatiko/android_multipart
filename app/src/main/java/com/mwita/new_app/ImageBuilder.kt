package com.mwita.new_app

import android.os.Build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ImageBuilder {
    //Base URL
    private const val URL = "http://192.168.100.156:8800/api/v1/documents/"

    private val logger = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY
    )

    // create okhttp client
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(logger)


    //create retrofit builder
    private val builder = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())

    //create retrofit instance
    private val retrofit = builder.build()

    /**
     * used to implement inner classes when calling apis
     */
    fun <T> buildService(serviceType: Class<T>):T{
        return retrofit.create(serviceType)
    }

}