
[![project chat](https://img.shields.io/badge/zulip-join_chat-brightgreen.svg)](https://anitab-org.zulipchat.com/#narrow/stream/222534-mentorship-system)
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0)
[![GitHub forks](https://img.shields.io/github/forks/anitab-org/mentorship-android?style=social)](https://github.com/anitab-org/mentorship-android/network)
[![GitHub stars](https://img.shields.io/github/stars/anitab-org/mentorship-android?style=social)](https://github.com/anitab-org/mentorship-android/stargazers)

# Mentorship System (Android)

| Branch | [Travis](https://travis-ci.org/) | [Codacy](https://www.codacy.com/) |
| :---: | :---: | :---: |
| [master](https://github.com/anitab-org/mentorship-android/tree/master) | [![Build Status](https://travis-ci.org/anitab-org/mentorship-android.svg?branch=master)](https://travis-ci.org/anitab-org/mentorship-android) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee27e44e4ac646e0afe440173ea47823?branch=master)](https://www.codacy.com/app/m-murad/mentorship-android) |
| [develop](https://github.com/anitab-org/mentorship-android/tree/develop) | [![Build Status](https://travis-ci.org/anitab-org/mentorship-android.svg?branch=develop)](https://travis-ci.org/anitab-org/mentorship-android) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee27e44e4ac646e0afe440173ea47823?branch=develop)](https://www.codacy.com/app/m-murad/mentorship-android) |

[Mentorship System](https://github.com/anitab-org/mentorship-backend) is an application that allows women in tech to mentor each other, on career development topics, through 1:1 relations for a certain period.

This is the Android client for the Mentorship System.

## Features

* A member gets an option to be a Mentor, Mentee or Both.
* A member can build/customise the app profile with username, bio, skills, interests, location, occupation, etc. anytime. 
* A member can view a list of other members and search for a member on the Members Page
* A member can either send mentorship requests to other members as a Mentor or Mentee according to their interests or reject mentorship requests from other members.
* A member can track the number of **Pending Resquests**, **Accepted Requests**, **Rejected Requests**, and **Completed Requests** and view **Recent Achievements** on the Home Page.  
* A member can view the details of pending, past, all the mentorship reqests in the Requests Page.
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
|![Introduction Slider](https://user-images.githubusercontent.com/63957920/107955208-86743280-6fc3-11eb-9d5f-0e1031beb992.jpeg)|![SignUp Screen](https://user-images.githubusercontent.com/63957920/107955263-968c1200-6fc3-11eb-952d-def226ef1174.jpeg)|![Login Page](https://user-images.githubusercontent.com/63957920/107955213-883df600-6fc3-11eb-9d43-768a25f11aff.jpeg)| 
|:---:|:---:|:---:|
|![Home Page](https://user-images.githubusercontent.com/63957920/107955205-84aa6f00-6fc3-11eb-95c7-4db92f18f6df.jpeg)|![Profile Page](https://user-images.githubusercontent.com/63957920/107955253-94c24e80-6fc3-11eb-9463-39aa50cb382c.jpeg)|![Edit Profile Page](https://user-images.githubusercontent.com/63957920/107955188-7f4d2480-6fc3-11eb-8698-66f41d6ae835.jpeg)|
|![Relations Page](https://user-images.githubusercontent.com/63957920/107955255-955ae500-6fc3-11eb-8d00-2664a2dce0cc.jpeg)|![Tasks Page](https://user-images.githubusercontent.com/63957920/107955267-9724a880-6fc3-11eb-9b20-c11ee5013014.jpeg)|![Members Page](https://user-images.githubusercontent.com/63957920/107955249-9429b800-6fc3-11eb-9a67-9a9cdafcb8ba.jpeg)|
|![Filter Members Screen](https://user-images.githubusercontent.com/63957920/107955194-81af7e80-6fc3-11eb-8ce7-c08d7e39b22a.jpeg)|![Member Profile](https://user-images.githubusercontent.com/63957920/107955215-8a07b980-6fc3-11eb-90b8-cde542c517bd.jpeg)|![Send Request Page](https://user-images.githubusercontent.com/63957920/107955259-95f37b80-6fc3-11eb-9e4f-5a284d82195a.jpeg)|
|![Requests Page](https://user-images.githubusercontent.com/63957920/107955257-955ae500-6fc3-11eb-8aec-488ed1cd8e0a.jpeg)|![Settings Screen](https://user-images.githubusercontent.com/63957920/107955260-95f37b80-6fc3-11eb-8862-4e0668c6d6e1.jpeg)|![About Page](https://user-images.githubusercontent.com/63957920/107955159-76f4e980-6fc3-11eb-87f8-464d6e9b9db4.jpeg)|
|![Change Password Page](https://user-images.githubusercontent.com/63957920/107955177-7bb99d80-6fc3-11eb-8a61-729001a3c330.jpeg)|
## Contact

You can reach our community at [AnitaB.org Open Source Zulip](https://anitab-org.zulipchat.com/).

We use [#mentorship-system](https://anitab-org.zulipchat.com/#narrow/stream/222534-mentorship-system) stream on Zulip to discuss this project and interact with the community. If you're interested in contributing to this project, join us there!

## License

Mentorship System is licensed under the GNU General Public License v3.0. Learn more about it in the [LICENSE](LICENSE) file.
