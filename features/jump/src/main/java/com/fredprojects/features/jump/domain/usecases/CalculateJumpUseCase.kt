package com.fredprojects.features.jump.domain.usecases

import kotlin.math.sqrt

class CalculateJumpUseCase {
    private var accelerationCurrent = 0f
    private var accelerationLast = 0f
    private var gravity = 0f
    private var diff = 0f
    private var result = 0
    operator fun invoke(x: Float, y: Float, z: Float): Int {
        gravity = sqrt(ALPHA * x * x + ALPHA * y * y + ALPHA * z * z)
        accelerationLast = accelerationCurrent
        accelerationCurrent = z - gravity
        diff = accelerationCurrent - accelerationLast
        if (diff > 6) result++
        return result
    }
    companion object {
        private const val ALPHA = 0.8f
    }
}