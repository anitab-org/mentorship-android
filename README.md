# Mentorship System (Android)

| Branch | [Travis](https://travis-ci.org/) | [Codacy](https://www.codacy.com/) |
| :---: | :---: | :---: |
| [master](https://github.com/systers/mentorship-android/tree/master) | [![Build Status](https://travis-ci.org/systers/mentorship-android.svg?branch=master)](https://travis-ci.org/systers/mentorship-android) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee27e44e4ac646e0afe440173ea47823?branch=master)](https://www.codacy.com/app/m-murad/mentorship-android) |
| [develop](https://github.com/systers/mentorship-android/tree/develop) | [![Build Status](https://travis-ci.org/systers/mentorship-android.svg?branch=develop)](https://travis-ci.org/systers/mentorship-android) | [![Codacy Badge](https://api.codacy.com/project/badge/Grade/ee27e44e4ac646e0afe440173ea47823?branch=develop)](https://www.codacy.com/app/m-murad/mentorship-android) |

[Mentorship System](https://github.com/systers/mentorship-backend) is an application that allows women in tech to mentor each other, on career development topics, through 1:1 relations for a certain period.

This is the Android client for the Mentorship System.

## Setting up the project

To setup the project locally read these wiki pages and follow the instructions:

 - [Fork, Clone and Remote](https://github.com/systers/mentorship-android/wiki/Fork%2C-Clone-%26-Remote)
 - [Open project in Android Studio](https://github.com/systers/mentorship-android/wiki/Open-the-project-in-Android-Studio)

## Contributing 

Please read our [Contributing guidelines](https://github.com/systers/mentorship-android/blob/develop/.github/CONTRIBUTING.md), [Code of Conduct](http://systers.io/code-of-conduct) and [Reporting Guidelines](http://systers.io/reporting-guidelines)

Please follow our [Commit Message Style Guide](https://github.com/systers/mentorship-android/wiki/Commit-Message-Style-Guide) while sending PRs.

## Branches

The repository has the following permanent branches:

 * **master** This contains the code which has been released.

 * **develop** This contains the latest code. All the contributing PRs must be sent to this branch. When we want to release the next version of the app, this branch is merged into the `master` branch.

 * **apk** This branch contains the apks for the code in the develop branch. The apks are automatically updated when a commit is pushed to `develop` branch.


##Run Server Locally To Test Android

 * **Run server on Local machine methods.**
	1. Run by local ip :-
		 Connect your mobile and laptop on the same network. Type command ipconfig if you are using Linux or ipconfig if you are using windows. Modify the run.py by following line 

		if __name__ == "__main__":
		application.debug=True
		application.run(host='<local-ip>',port=5000)
	        <local-ip> be like 192.168.xx.xx
	2. By using ngrok :-
	        Install ngrok on your system by following command:- 
		brew cask install ngrok
		ngrok http <port>
		    This outputs, among some other information, a line like
	        Forwarding                    http://4cc5ac02.ngrok.io -> localhost:5000
	        Now, you can navigate to http://4cc5ac02.ngrok.io on any device that is connected to the Internet, and this URL redirects to localhost:5000 of your laptop.

 
 * **Connectivity with Android App.**
	Your have to change the url in Android app by :-
            val apiBaseUrl: String 
                get()=if(BuildConfig.DEBUG){
		“http://192.168.xx.xx:5000/” 
		} else {
		“http://192.168.xx.xx:5000/”
		}



## Contact

You can reach maintainers team @mentorship-team on [Systers Open Source Slack](http://systers.io/slack-systers-opensource/) or @systers/maintainers-mentorship-android on GitHub.

We use [#mentorship-system](https://systers-opensource.slack.com/messages/CAE8QK41L/) channel on Slack to discuss this project. If you're interested in contributing to this project, join us there!
