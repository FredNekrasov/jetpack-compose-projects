package com.fredprojects.domain.features.wapi.common

/**
 * MathModel is used to store data of the math model.
 *
 * @param expression the expression to be solved
 * @param result the result of the expression
 */
data class MathModel(
    val expression: String,
    val result: String
)