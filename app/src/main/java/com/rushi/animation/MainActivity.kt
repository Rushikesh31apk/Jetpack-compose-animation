package com.rushi.animation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rushi.animation.ui.theme.AnimationTheme

class MainActivity : ComponentActivity() {
	@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			AnimationTheme {
				Scaffold(modifier = Modifier.fillMaxSize()) {
					MyApp()
				}
			}
		}
	}
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
	Box(
		modifier.fillMaxSize(),
		contentAlignment = Alignment.Center
	) {
		val composition by rememberLottieComposition(
			spec = LottieCompositionSpec.RawRes(R.raw.love)
		)
		var isPlaying by remember {
			mutableStateOf(true)
		}
		val progress by animateLottieCompositionAsState(
			composition = composition,
			isPlaying = isPlaying
		)
		LaunchedEffect(key1 = progress) {
			if (progress == 0f) {
				isPlaying = true
			}
			if (progress == 1f) {
				isPlaying = false
			}
		}

		LottieAnimation(
			composition = composition,
			modifier = modifier
				.size(500.dp)
				.clickable {
					isPlaying = true
				},
			iterations = LottieConstants.IterateForever
//					progress = {
//				progress
//			}
		)
	}
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyAppPreview() {
	MyApp()
}