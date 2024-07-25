package com.fredprojects.helloworld.presentation.features.jumps

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import kotlin.math.*

@Composable
internal fun StickMan(countOfJumps: Int) {
    val jump = remember { Animatable(0f) }
    val angle = remember { Animatable(0f) }
    val ropeAngle = remember { Animatable(0f) }
    Column(Modifier.wrapContentSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        if (countOfJumps > 0) {
            LaunchedEffect(countOfJumps) {
                launch {
                    repeat(countOfJumps) {
                        async { angle.animateTo(91f / 180f * PI.toFloat(), tween(1000)) }.await()
                        async { angle.animateTo(0f / 180f * PI.toFloat(), tween(1000)) }.await()
                    }
                }
                launch {
                    repeat(countOfJumps) {
                        async { jump.animateTo(50f, tween(1000)) }.await()
                        async { jump.animateTo(0f, tween(1000)) }.await()
                    }
                }
                launch {
                    repeat(countOfJumps) {
                        async { ropeAngle.animateTo(400f, tween(1000)) }.await()
                        async { ropeAngle.animateTo(-300f, tween(1000)) }.await()
                    }
                }
            }
        }
        Canvas(Modifier.wrapContentSize().padding(8.dp)) {
            val height = jump.value
            val rightLeg = Offset((size.width/2)+25, size.height - height)
            val leftLeg = Offset((size.width/2)-25, size.height - height)

            val bodyS = Offset(size.width/2, size.height - (height+260))
            val bodyE = Offset(size.width/2, size.height - (height+460))

            val startArms = Offset(size.width/2, bodyE.y+50)
            val rightArmE = Offset((size.width/2)+100, bodyE.y+225)
            val leftArmE = Offset((size.width/2)-100, bodyE.y+225)

            val handRS = Offset((size.width/2)-96, rightArmE.y)
            val handRE = Offset((size.width/2)-125, rightArmE.y)
            val handLS = Offset((size.width/2)+96, leftArmE.y)
            val handLE = Offset((size.width/2)+125, leftArmE.y)

            val rope1 = Offset(handLE.x, ropeAngle.value)
            val rope2 = Offset(handRE.x, ropeAngle.value)

            val legLength = (bodyS - rightLeg).getDistance()
            val startLegs = bodyS + Offset(legLength/2* cos(angle.value), legLength/2* sin(angle.value))
            val centerOfLL = (startLegs + leftLeg)/2f
            val centerOfRL = (startLegs + rightLeg)/2f
            val vectorL = leftLeg-startLegs
            val vectorR = rightLeg-startLegs
            val normL = Offset(-vectorL.y, vectorL.x)
            val normR = Offset(vectorR.y, -vectorR.x)
            val kneeL = centerOfLL+normL/normL.getDistance()
            val kneeR = centerOfRL+normR/normR.getDistance()

            drawLine(Color.Magenta, bodyS, kneeL, strokeWidth = 10f)
            drawLine(Color.Magenta, bodyS, kneeR, strokeWidth = 10f)
            drawLine(Color.Magenta, leftLeg, kneeL, strokeWidth = 10f)
            drawLine(Color.Magenta, rightLeg, kneeR, strokeWidth = 10f)

            drawLine(Color.Magenta, bodyS, bodyE, strokeWidth = 10f)
            drawLine(Color.Magenta, startArms, rightArmE, strokeWidth = 10f)
            drawLine(Color.Magenta, startArms, leftArmE, strokeWidth = 10f)
            drawLine(Color.Magenta, handRS, handRE, strokeWidth = 10f)
            drawLine(Color.Magenta, handLS, handLE, strokeWidth = 10f)
            drawCircle(Color.Magenta, radius = 50f, Offset(bodyE.x, bodyE.y-50f), style = Stroke(width = 10f))

            drawLine(Color.Magenta, handLE, rope1, strokeWidth = 10f)
            drawLine(Color.Magenta, rope2, rope1, strokeWidth = 10f)
            drawLine(Color.Magenta, handRE, rope2, strokeWidth = 10f)
        }
    }
}