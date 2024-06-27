package com.fredprojects.data.math.remote.service

import com.fredprojects.data.math.remote.dto.MathDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * IMathService is used to work with the Math API.
 */
interface IMathService {
    /**
     * GetResult is used to get the result of the expression.
     * @param expression the expression to be solved
     * @return the result of the expression
     */
    @GET("/api/v2/simplify/{expression}")
    fun getResult(@Path("expression") expression: String): Call<MathDto>
    companion object {
        const val BASE_URL = "https://newton.now.sh/"
    }
}