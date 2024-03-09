<h1 align="center">Android Challenge</h1>

This repository is part of the interviewing process for the Android Developer position at T-Pro. It contains a collection of challenges to be completed.

## Repository Organization

- [Theoretical Questions](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/blob/master/README.md) - Folder that contains the answers to all the theoretical-only questions inside a README file.
- [Code Questions](https://github.com/T-Pro/android-challenge-elena/tree/master/Code%20Questions) - Folder that contains the answers to all the code questions and also some theoretical points of those inside a README file.
  - [Broken App](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/tree/master/Code%20Questions/BrokenApp) - App requested to be fixed on the first question of the third questions level.
  - [Simple App](hhttps://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/tree/master/Code%20Questions/SimpleApp) - App requested to be developed on the second question of the third questions level.

## Tasks

The whole test is divided in three levels and the collection of answers to these questions is splitted across the different folders as explained on the [Repository Organization](#repository-organization). The following table shows the questions to be answered and for each of them it shows if it was answered and the corresponding link to the answer.

### First Level

| Task |                                                                Done                                                                 | Time Spent |
| ----------- |:-----------------------------------------------------------------------------------------------------------------------------------:| :-: |
| [Explain the main concept of lambda functions.](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/blob/master/README.md) |                 Think of lambda expressions as little coding shortcuts—a way to write concise, anonymous functions.                 | |
| [What's the difference between `UInt16` and `Int32`?](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/blob/master/README.md) |    UInt16 is a 16-bit unsigned integer (0 to 65,535), while Int32 is a 32-bit signed integer (-2,147,483,648 to 2,147,483,647).     | |
| [Do you have any experience with functional programming languages?](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/blob/master/README.md) |                                                                 No                                                                  | |
| [Compile any Kotlin source using `kotlinc`.](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/blob/master/Code%20Questions/README.md) |                                           kotlinc hello.kt -include-runtime -d hello.jar                                            | |
| [Create a function in Kotlin that writes 50 million random floats in a file.](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/blob/master/Code%20Questions/README.md) |                                                        import kotlin.random.Random

fun generateRandomFloatsToFile(fileName: String, count: Int) {
val random = Random.Default
val outputFile = File(fileName)

    // Open the file for writing
    outputFile.bufferedWriter().use { writer ->
        repeat(count) {
            val randomFloat = random.nextFloat()
            writer.write("$randomFloat\n")
        }
    }

    println("Generated $count random floats and saved them to $fileName.")
}

fun main() {
val fileName = "random_floats.txt"
val numberOfFloats = 50_000_000
generateRandomFloatsToFile(fileName, numberOfFloats)
}                                                         |25 Min |
  
### Second Level

| Task | Done | Time Spent |
| ----------- |:----:|:----------:|
| [Do you have experience with C++ (JNI) code in Android projects?](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/blob/master/Theoretical%20Questions/README.md) |  No  |            |
| [Create a code below that implements the basic concept of dependency injection.](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/blob/master/Code%20Questions/README.md) | Yes  |   20 Min   |
| [Implement a simple Model-View-Presenter or Model-View-ViewModel structure below and also explain why you choose MVVM over MVP and vice versa.](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/blob/master/Code%20Questions/README.md) | Yes  |   20 Min   |

### Third Level

| Task | Done | Time Spent |
| ----------- | :-: | :-: |
| Solve the bugs and bad pratices of the project inside the [BrokenApp](https://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/tree/master/Code%20Questions/BrokenApp) folder. |   | |
| Create a [simple app](hhttps://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/tree/master/Code%20Questions/SimpleApp) app that requests a specific endpoint. |   | |

### Bonus

| Task | Done | Time Spent |
| ----------- | :-: | :-: |
| Create a fastlane script to build your [simple app](hhttps://github.com/mouryaavadhesh/android-challenge-tpro-avadhesh/tree/master/Code%20Questions/SimpleApp) project. |   | |
| Use Coroutines for async process. |   | |
