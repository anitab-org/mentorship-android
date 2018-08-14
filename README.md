# Mentorship System (Android)

This is the Android application of the Mentorship System.

The backend for this project can be found on [systers/mentorship-backend](https://github.com/systers/mentorship-backend) GitHub repository.

## About

Mentorship System is an application that allows women in tech to mentor each other, on career development topics, 
through 1:1 relations during a certain period of time.

This project was proposed as [an original project of Google Summer of Code](https://summerofcode.withgoogle.com/projects/#5331289322815488) 2018 edition, by [Isabel Costa](https://github.com/isabelcosta), that was mentored by
[Dilushi](https://github.com/Dilu9218), [Murad](https://github.com/m-murad), [Roopal](https://github.com/roopalJazz) and [May](https://github.com/exactlymay) as an admin.

You can learn more about this project's idea and motivations on [this blog post](https://medium.com/systers-opensource/mentorship-system-by-systers-52dbe1275d9f).

### Tech Stack

- **Language and Framework:** Kotlin, Android SDK
- **Libraries:** ViewModel, LiveData, Databinding, Retrofit, RxJava
- **Architecture:** Model View ViewModel (MVVM)

## How to Setup

### Requirements

You will need [Android Studio](https://developer.android.com/studio/), make sure to have the latest version installed.

To contribute with code to this project you'll need to have [git](https://git-scm.com/) version control tool installed.

These tools work on Windows, Linux and Mac OS. Make sue you have the correct version for your OS.

This Android application supports devices running Android 4.4 (API level 19). Although targets devices running Android version 8.1 (API level 27). 

### Initial setup

1. Go to [the project repo](https://github.com/systers/mentorship-android/) and fork it by clicking "Fork" 
2. Clone the repo to your workspace `git clone https://github.com/YOUR_USERNAME/mentorship-android.git`
3. Fetch the latest version of code from branch "develop"
4. Open the project with Android Studio 

### Configure remotes

When a repository is cloned, it has a default remote called `origin` that points to your fork on GitHub, not the original repository 
it was forked from. To keep track of the original repository, you should add another remote named `upstream`:

1. Open terminal or git bash in your local repository and set up the origin:
    ```
    git remote add origin https://github.com/YOUR_USERNAME/mentorship-android.git
    ```

2. Set the `upstream`:

    ```
    git remote add upstream https://github.com/systers/mentorship-android.git
    ```

3. Run `git remote -v` to check the status, you should see something like the following:

    ```
    $ git remote -v
    origin  https://github.com/YOUR_USERNAME/mentorship-android (fetch)
    origin  https://github.com/YOUR_USERNAME/mentorship-android (push)
    upstream        https://github.com/systers/mentorship-android.git (fetch)
    upstream        https://github.com/systers/mentorship-android.git (push)
    ```

4. To update your local copy with remote changes, run the following:

    ```
    git fetch upstream
    git rebase upstream/develop
    ```

    This will give you an exact copy of the current remote, make sure you don't have any local changes.

## Contributing

Check our [Commit Message Style Guide](https://github.com/systers/mentorship-android/wiki/Commit-Message-Style-Guide) and follow it when making your commits!

You can learn more about the project reading documentation on [GitHub Wiki](https://github.com/systers/mentorship-android/wiki).

Make sure to read our [Code of Conduct](http://systers.io/code-of-conduct) and [Reporting Guidelines](http://systers.io/reporting-guidelines).

If you're new to Open Source and this project you can start looking into issues labeled as `First Timers Only`.
All issues with label `Status: Available` can be worked on by anyone.

## Contact

You can reach maintainers team @mentorship-team on [Systers Open Source Slack](http://systers.io/slack-systers-opensource/) or @systers/maintainers-mentorship-android on GitHub.

We use [#mentorship-system](https://systers-opensource.slack.com/messages/CAE8QK41L/) channel on Slack to discuss this project. If you're interested on contributing to this project make sure to join us there!
