# Code Questions

In order to proceed with the interviewing process, T-Pro asked a couple of code questions that also requires something theoretical to justify the implementation or just to provide some explanation regarding the topic.

## First Level Questions

**5. Compile any Kotlin source using `kotlinc`.**

 A:kotlinc hello.kt -include-runtime -d hello.jar

**6. Create a function in Kotlin that writes 50 million random floats in a file, the memory usage should be minimum in the entire process.**

 A:fun generateRandomFloatsToFile(fileName: String, count: Int) {
 val random = Random.Default
 val outputFile = File("avadhesh.txt")

    // Open the file for writing
    outputFile.bufferedWriter().use { writer ->
        repeat(50000000) {
            val randomFloat = random.nextFloat()
            writer.write("$randomFloat\n")
        }
    }

    println("Generated $count random floats and saved them to $fileName.")
}

## Second Level Questions

**2. Create a code below that implements the basic concept of dependency injection.**

 A: implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
 kapt "androidx.hilt:hilt-compiler:1.0.0"
 interface EmailSubmitter {
 fun submit(email: String, message: String): Boolean
 }
 class DefaultEmailSubmitter @Inject constructor() : EmailSubmitter {
 override fun submit(email: String, message: String): Boolean {
 // Implement the logic for submitting the email
 println("Email submitted to $email with message: $message")
 return true
 }
 }
 import dagger.hilt.android.scopes.ViewModelScoped
 import javax.inject.Inject

@ViewModelScoped
class EmailSender @Inject constructor(private val emailSubmitter: EmailSubmitter) {
fun sendEmail(email: String, message: String): Boolean {
// Implement any additional logic before submitting
return emailSubmitter.submit(email, message)
}
}
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hiltdagger.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val message = binding.messageEditText.text.toString()

            viewModel.sendEmail(email, message)
        }
    }
}


**3. Implement a simple Model-View-Presenter or Model-View-ViewModel structure below, you can reuse this in the projects below. Also explain why you choose MVVM over MVP and vice versa.**

 A:MVVM I Selected because of :
 Data Binding: MVVM leverages data binding, reducing boilerplate code.
 Separation of Concerns: Clearly separates UI logic from business logic.
 ViewModel Flexibility: ViewModel can serve multiple Views.
 Event-Driven: MVVM is more event-driven.

public class User {
private String name;
private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

public class UserViewModel extends ViewModel {

    private MutableLiveData<User> user;

    public UserViewModel() {
        user = new MutableLiveData<>();
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }

    public LiveData<User> getUser() {
        return user;
    }
}
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Set initial user data
        viewModel.setUser(User("John Doe", 30))

        // Observe changes in user data
        viewModel.user.observe(this, Observer { user ->
            // Update UI based on new user data (optional)
        })
    }
}
+----------------+         +-------------------+         +-----------------+
|       Model      |         |  ViewModel        |         |        View       |
+----------------+         +-------------------+         +-----------------+
|                     |                     |
v                     v                     v
(Data Properties)       (LiveData<T>)          (Data Binding)
|                     v                     |
v                     +-------------------+
(Business Logic)           |  UI Updates       |
|                     +-------------------+
v
(User Interactions)
## Questions of the third level

The third level questions are on the corresponding folders, `BrokenApp` and `SimpleApp`, inside this directory.
