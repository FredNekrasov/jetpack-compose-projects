package com.fredprojects.features.clients.data.remote

import com.fredprojects.features.clients.data.remote.services.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ApiServiceBuilder is used to create the Retrofit services
 */
private fun provideRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
    .baseUrl(baseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
internal fun createAstronomyInfoService(): IAstronomyInfoService = provideRetrofit(IAstronomyInfoService.BASE_URL).create(IAstronomyInfoService::class.java)
internal fun createMathService(): IMathService = provideRetrofit(IMathService.BASE_URL).create(IMathService::class.java)
internal fun createBBService(): IBBService = provideRetrofit(IBBService.BASE_URL).create(IBBService::class.java)