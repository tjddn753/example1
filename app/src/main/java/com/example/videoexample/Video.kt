package com.example.videoexample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
//https://medium.com/@munbonecci/how-to-display-videos-using-exoplayer-on-android-with-jetpack-compose-1fb4d57778f4
@Composable
fun ExoPlayerView(){

    val context = LocalContext.current
    val exoPlayer = ExoPlayer.Builder(context).build()
    val mediaSource = remember(EXAMPLE_VIDEO_URI){
        MediaItem.fromUri(EXAMPLE_VIDEO_URI)
    }

    LaunchedEffect(mediaSource){
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    DisposableEffect(Unit ){
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(factory = {
        ctx->
        PlayerView(ctx).apply {
            player = exoPlayer
        }
    })
}
const val EXAMPLE_VIDEO_URI = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"