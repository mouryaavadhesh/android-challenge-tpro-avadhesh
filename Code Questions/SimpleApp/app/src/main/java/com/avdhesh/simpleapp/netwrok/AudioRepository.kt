import android.media.MediaPlayer
import com.avdhesh.simpleapp.network.RetrofitService
import javax.inject.Inject

class AudioRepository @Inject constructor(private val apiService: RetrofitService) {
    private var mediaPlayer: MediaPlayer? = null

    suspend fun getAudio() = apiService.getAudio()


    suspend fun getMediaPlayer(audioUrl: String): MediaPlayer? {
        mediaPlayer?.release() // Release previous player if any
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setDataSource(audioUrl)
        return mediaPlayer
    }

    fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}