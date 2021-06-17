For this Android application, we decided to use Kotlin as the main language.

**Summary**

- **Language:** [Kotlin](https://kotlinlang.org/)
- **Architecture:** Model View ViewModel (MVVM)
- **Libraries:** [Retrofit](http://square.github.io/retrofit/), [RxJava](https://github.com/ReactiveX/RxJava), ViewModel, LiveData, DataBinding


## Why use Kotlin?

Since this was a new project we decided to experiment a new tech stack for Android. Since Kotlin was declared as a first class programming language for Android, it started becoming much more popular. You can check a [comparison from Kotlin to Java programming language](https://kotlinlang.org/docs/reference/comparison-to-java.html).

If you're new with kotlin there are many resources from where you can learn more:
- [Try Kotlin](https://try.kotlinlang.org/) 
- [Kotlin Koans](https://kotlinlang.org/docs/tutorials/koans.html) - This tutorial walks you through a series of exercises to get familiar with Kotlin
- [Kotlin for Android Developers](https://eu.udacity.com/course/kotlin-for-android-developers--ud888) course on Udacity
- ... There is a lot of information regarding Kotlin spread across blog posts, video tutorials, open sourced projects... You can always use Google about this and find awesome resources!

## Why use a ~native approach instead of using a hybrid solution for this?

We considered using [flutter](https://flutter.io/) as a solution for the mobile application but since the first student working on this project had experience with Android development and did not have any experience with the flutter framework, so the team ultimately decided to build the first version of the app for Android platform. Also, the team was short on time to build the mobile application for [Google Summer of Code](https://summerofcode.withgoogle.com).

Nonetheless, the community had [a brief discussion about this](https://systers-opensource.slack.com/archives/CAE8QK41L/p1531508427000351) on [Systers Open Source Slack](http://systers.io/slack-systers-opensource/). The outcome of this was that using flutter was a good way to develop and iOS and Android version of the application at the same time and that this could be done in the future, for example on next open source programs (e.g.: Google Summer of Code).

## Why use MVVM architecture?

Developers always prefer a clean and structured code for the projects.
By organizing the codes according to a design pattern helps in the maintenance of the software
. By having knowledge of all crucial logic parts of the android application, it is easier to add and remove app features.

Model — View — ViewModel [MVVM](https://developer.android.com/jetpack/guide?gclid=CjwKCAjwtdeFBhBAEiwAKOIy54BbAQsy_XH_sdwfTNM0Ben6F4omB1u3v5rUrEt6JhV7m0CokKUUGxoCRgsQAvD_BwE&gclsrc=aw.ds) is the industry-recognized software architecture pattern that overcomes all drawbacks of MVP and MVC design patterns. MVVM suggests separating the
data presentation logic(Views or UI) from the core business logic part of the application.
 MVVM is the best way to structure code.
 The separate code layers of MVVM are:
  -Model
  -View
  -ViewModel

**Benefits of using MVVM pattern are as follows :**
- The UI components are kept away from the business logic- 
  
  So ,Designers and developers can work independently and concurrently on their components during the development process. Designers can focus on the view, while developers can work on the view model and model components.
- The business logic is kept away from the database operations-

   If there's an existing model implementation that encapsulates existing business logic, it can be difficult or risky to change it. In this scenario, the view model acts as an adapter for the model classes and enables you to avoid making any major changes to the model code.
- It's easy to understand and read  
- And if done correctly, you have a lot less to worry about when it comes to lifecycle events (eg: screen rotations)