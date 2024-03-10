import com.avdhesh.simpleapp.network.RetrofitService
import javax.inject.Inject

class AudioRepository @Inject constructor(private val apiService: RetrofitService) {

    suspend fun getAudio() = apiService.getAudio()


}