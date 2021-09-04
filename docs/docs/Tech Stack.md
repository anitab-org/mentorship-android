---
id: Tech Stack
title: Tech Stack
---
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

TODO TODO TODO
