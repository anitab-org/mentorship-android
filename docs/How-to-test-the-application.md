# How to test App:
- Using Physical device and apk
- Using Emulator and Apk
- Directly Running in Android Studio ( Recommended )


## For option one or two:

Every time a new commit is merged into the `develop` branch an _apk_ is released in the `apk` branch. To Navigate to `apk` Branch see here: 

![Branch chnage](https://user-images.githubusercontent.com/16835617/92631953-8a52a900-f2ef-11ea-9e61-60692555283b.png)


If you want to do manual testing of the application without setting up the development environment, you can download this [app-debug.apk](https://github.com/anitab-org/mentorship-android/blob/apk/app-debug.apk) and install it on your Android device.

![apk-branch](https://user-images.githubusercontent.com/16835617/97575784-42105700-1a13-11eb-8a73-4669b8ebb343.png)


![apkInstall](https://user-images.githubusercontent.com/16835617/97575669-1db47a80-1a13-11eb-8074-bcac49ee59db.png)


For Testing on a physical device tick the option here :
![Screenshot of Unknown install option ](https://static.apkpure.com/www/static/imgs/unknown_sources.jpg)

Now click the apk to install !

## For Testing on Emulator with APk :

1. Now Open the project in Android Studio
2. Wait untill all background process are finished 
3. Click on the AVD button or select from already created devices in Android studio  
![advPress](https://user-images.githubusercontent.com/16835617/92633082-de5d8d80-f2ef-11ea-9593-ffb3646e8b55.png)
4. For information on how to create an Emulator and Run it visit [Creating an AVD](https://developer.android.com/studio/run/managing-avds#createavd)
5. Now Run the Emulator you have created 
![RunAVD](https://user-images.githubusercontent.com/16835617/92633141-f59c7b00-f2ef-11ea-9e3b-f686995fc57f.png)
6. Navigate to the apk file downloaded 
![apkAndEmulator](https://user-images.githubusercontent.com/16835617/92633190-0b11a500-f2f0-11ea-9c0b-2d4d7ee76162.png)
7. Drag and Drop the Apk file into the Emulator.
8. Now just look for the App in App tray and click to open.
![gif](./images/gifDemo.gif)


## For running through Android Studio
1. [Clone the repository](https://github.com/anitab-org/mentorship-android/wiki/Fork,-Clone-&-Remote) follow the instructions here.
2. Repeat step 1-5 of **Testing on Emulator with APk** 
3. Press the Run 'app' button now as shown 
![Run app](https://user-images.githubusercontent.com/16835617/93021832-b5e0d680-f602-11ea-8345-63aab5961895.png)
4. Wait for the emulator to load up. Rest the IDE would handle. ;)  
5. Now your app is ready to test any changes made (*ps: make sure to press run 'app' button to reinstall the app in emulator*)
6. ![Sucess](https://user-images.githubusercontent.com/16835617/93021921-5e8f3600-f603-11ea-9f0c-c0825c32b26b.png)

- Now Test the app as required. :)

If you want ideas of test cases to use when testing the mobile application business logic, you can check this set of Quality Assurance test cases page from [anitab-org/mentorship-backend](https://github.com/anitab-org/mentorship-backend) repository.

Resources:
- [Apk branch](https://github.com/anitab-org/mentorship-android/tree/apk)
- [app-debug.apk](https://github.com/anitab-org/mentorship-android/blob/apk/app-debug.apk)
- [Quality Assurance test cases](https://github.com/anitab-org/mentorship-backend/blob/develop/docs/quality-assurance-test-cases.md)
