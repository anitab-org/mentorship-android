
[![project chat](https://img.shields.io/badge/zulip-join_chat-brightgreen.svg?logo=zulip)](https://anitab-org.zulipchat.com/#narrow/stream/222534-mentorship-system)
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg?logo=GNU)](http://www.gnu.org/licenses/gpl-3.0)
[![GitHub forks](https://img.shields.io/github/forks/anitab-org/mentorship-android?style=social)](https://github.com/anitab-org/mentorship-android/network)
[![GitHub stars](https://img.shields.io/github/stars/anitab-org/mentorship-android?style=social)](https://github.com/anitab-org/mentorship-android/stargazers)

# Mentorship System (Android)

| Branch | [Travis](https://travis-ci.org/) | [Codacy](https://www.codacy.com/) |
| :---: | :---: | :---: |
| [master](https://github.com/anitab-org/mentorship-android/tree/master) | [![Build Status](https://img.shields.io/travis/com/anitab-org/mentorship-android/master?logo=travis)](https://travis-ci.org/anitab-org/mentorship-android) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee27e44e4ac646e0afe440173ea47823?branch=master)](https://www.codacy.com/app/m-murad/mentorship-android) |
| [develop](https://github.com/anitab-org/mentorship-android/tree/develop) | [![Build Status](https://img.shields.io/travis/com/anitab-org/mentorship-android/develop?logo=travis)](https://travis-ci.org/anitab-org/mentorship-android) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee27e44e4ac646e0afe440173ea47823?branch=develop)](https://www.codacy.com/app/m-murad/mentorship-android) |

[Mentorship System](https://github.com/anitab-org/mentorship-backend) is an application that allows women in tech to mentor each other, on career development topics, through 1:1 relations for a certain period.

This is the Android client for the Mentorship System.

## Features
* Once the App is installed user can view an onboarding screen which introduces the app and give user an idea of how it works.
* A member gets an option to be a Mentor, Mentee or Both.
* A member can build/customise the app profile with username, bio, skills, interests, location, occupation, etc. anytime. 
* A member can read and know more about Mentorship System including the terms and conditions, privacy and policy and code of conduct.
* A member can directly access the AnitaB-org Github repo, Zulip chat and website from this App.
* A member can refresh the every page.
* A member can view a list of other members and search for a member on the Members Page.
* A member can search for particular members there in Mentorship System.
* A member can sort other members on the basis of their name either (A-Z) OR (Z-A), registration date, age.
* A member can filter other members by the label **need mentoring** or **available to mentor**, interest, location and skills that given while creating profile.
* A member can either send mentorship requests to other members as a Mentor or Mentee according to their interests or reject mentorship requests from other members.
* A member can track the number of **Pending Resquests**, **Accepted Requests**, **Rejected Requests**, and **Completed Requests** and view **Recent Achievements** on the Home Page.  
* A member can view the details of pending, past, all the mentorship requests in the Requests Page.
* A member can create, update, or delete tasks in their current mentorship relation.
* A member can send feedback about the Mentorship System such as reporting a bug, giving suggestions or other comments.
* A member can delete their account.
* A member can change their account password anytime.

## Setting up the project

To setup the project locally read these wiki pages and follow the instructions:

 - [Fork, Clone and Remote](https://github.com/anitab-org/mentorship-android/wiki/Fork%2C-Clone-%26-Remote)
 - [Open project in Android Studio](https://github.com/anitab-org/mentorship-android/wiki/Open-the-project-in-Android-Studio)

## Contributing 

Please read our [Contributing guidelines](https://github.com/anitab-org/mentorship-android/blob/develop/.github/CONTRIBUTING.md), [Code of Conduct](http://systers.io/code-of-conduct) and [Reporting Guidelines](http://systers.io/reporting-guidelines)

Please follow our [Commit Message Style Guide](https://github.com/anitab-org/mentorship-android/wiki/Commit-Message-Style-Guide) while sending PRs.

Please follow Kotlin official docs for [Coding Conventions](https://kotlinlang.org/docs/reference/coding-conventions.html)to maintain a consistent code style in the repository.

## Running the UI tests

To run the existing UI tests follow the steps given below:
* Connect your Android device or open the emulator.
* On the terminal type: `./gradlew clean build connectedAndroidTest --stacktrace`
* If the above command is not working try using the command: `gradlew clean build connectedAndroidTest --stacktrace`
* Wait until all the tests are completed.
* You will get a report generated with a test summary.
* See the complete report `./mentorship-android/app/build/reports/androidTests/connected/index.html`
* The report `./mentorship-android/app/build/reports/androidTests/connected/index.html` will show all the testsuites that have passed as well as failed.
* To get a more detailed explanation about the tests of a particular testsuite visit respective html file of the testsuite.
  For e.g.: `org.systers.mentorship.LoginActivityTest.html` will contain the results of all the tests run under that particular testsuite.
* You can find the respective html files of the different testsuites under the heading classes in `index.html` report.

## Documentation

To learn more about this app you can look at [this project's wiki](https://github.com/anitab-org/mentorship-android/wiki).

Check out the design history at [User Interface Design](https://github.com/anitab-org/mentorship-android/wiki/User-Interface-Design).

Our tech stack includes:
- **Language:** [Kotlin](https://kotlinlang.org/)
- **Architecture:** Model View ViewModel (MVVM)
- **Libraries:** [Retrofit](http://square.github.io/retrofit/), [RxJava](https://github.com/ReactiveX/RxJava), ViewModel, LiveData, DataBinding

## Branches

The repository has the following permanent branches:

 * **master** This contains the code which has been released.

 * **develop** This contains the latest code. All the contributing PRs must be sent to this branch. When we want to release the next version of the app, this branch is merged into the `master` branch.

 * **apk** This branch contains the apks for the code in the develop branch. The apks are automatically updated when a commit is pushed to `develop` branch.

## Screenshots
|![Splashscreen](Screenshots/Splashscreen.png)|![SignUp Screen](Screenshots/SignUp%20Screen.png)|![Login Page](Screenshots/Login%20Page.png)| 
|---|---|---|
|![Home Page](Screenshots/Home%20Page.png)|![Profile Page](Screenshots/Profile%20Page.png)|![Edit Profile Page](Screenshots/Edit%20Profile%20Page.png)| 
|![Relations Page](Screenshots/Relations%20Page.png)|![Tasks Page](Screenshots/Tasks%20Page.png)|![Members Page](Screenshots/Members%20Page.png)
|![Member Profile](Screenshots/Member%20Profile%20Page.png)|![Send Request Page](Screenshots/Send%20Request%20Page.png)|![Requests Page](Screenshots/Requests%20Page.png)|
![Settings Screen](Screenshots/Settings%20Screen.png)|![About Page](Screenshots/About%20Page.png)|![Change Password Page](Screenshots/Change%20Password%20Page.png)|
## Contact

You can reach our community at [AnitaB.org Open Source Zulip](https://anitab-org.zulipchat.com/).

We use [#mentorship-system](https://anitab-org.zulipchat.com/#narrow/stream/222534-mentorship-system) stream on Zulip to discuss this project and interact with the community. If you're interested in contributing to this project, join us there!

## License

Mentorship System is licensed under the GNU General Public License v3.0. Learn more about it in the [LICENSE](LICENSE) file.
