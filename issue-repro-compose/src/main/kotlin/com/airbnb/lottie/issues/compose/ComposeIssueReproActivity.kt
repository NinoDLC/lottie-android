package com.airbnb.lottie.issues.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationState
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import java.util.concurrent.atomic.AtomicInteger

class ComposeIssueReproActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Content()
        }
    }

    private val toastCount = AtomicInteger()

    @Composable
    fun Content() {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.heart))
        val lottieAnimationState: LottieAnimationState = animateLottieCompositionAsState(composition)

        if (lottieAnimationState.isAtEnd) {
            Log.w("Bug", "isAtEnd ${toastCount.getAndIncrement()}")
        }

        LaunchedEffect(lottieAnimationState.isAtEnd) {
            if (lottieAnimationState.isAtEnd) {
                Log.w("Bug", "LaunchedEffect.isAtEnd ${toastCount.getAndIncrement()}")
            }
        }

        LottieAnimation(composition)
    }
}
