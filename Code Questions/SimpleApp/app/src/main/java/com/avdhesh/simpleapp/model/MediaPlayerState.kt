package com.avdhesh.simpleapp.model

sealed class MediaPlayerState {
    object Idle : MediaPlayerState()
    object Loading : MediaPlayerState()
    object Playing : MediaPlayerState()
    object Paused : MediaPlayerState()
    class Error(val error: Throwable) : MediaPlayerState()
}