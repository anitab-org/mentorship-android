Every time a new commit is merged into the develop branch an apk is released in the apk branch.To Navigate to apk Branch see here : 

![Branch chnage](https://user-images.githubusercontent.com/16835617/92631953-8a52a900-f2ef-11ea-9e61-60692555283b.png)


If you want to do manual testing on the application without setting up the development environment, you can download this app-debug.apk and install it on your Android device.

![apk-branch](https://user-images.githubusercontent.com/16835617/92632334-a5251d80-f2ef-11ea-8d7d-007374cd882e.png)


![apkInstall](https://user-images.githubusercontent.com/16835617/92632771-c4bc4600-f2ef-11ea-8d30-6618d990166d.png)


For manually testing in physical device tick the option here :
![Screenshot of Unknown install option ](https://static.apkpure.com/www/static/imgs/unknown_sources.jpg)

Now click the apk to install !

For Testing on Emulator :

- Now Open the project in Android Studio
- Wait untill all background process are finished 
- click the ADV button or click from already created devices in Android studio click 
![advPress](https://user-images.githubusercontent.com/16835617/92633082-de5d8d80-f2ef-11ea-9593-ffb3646e8b55.png)
- For information on how to create an Emulator and Run it visit [Creating an ADV](https://developer.android.com/studio/run/managing-avds#createavd)
- Now Run the Emulator you have created 
![RunADV](https://user-images.githubusercontent.com/16835617/92633141-f59c7b00-f2ef-11ea-9e3b-f686995fc57f.png)
- Navigate to the apk file downloaded 
![apkAndEmulator](https://user-images.githubusercontent.com/16835617/92633190-0b11a500-f2f0-11ea-9c0b-2d4d7ee76162.png)
- Drag and Drop the Apk file into the Emulator.
- Now just look for the App in App tray and click to open.
![gif](./images/gifDemo.gif)
- Now Test the app as required. :)

If you want ideas of test cases to use when testing the mobile application business logic, you can check this set of Quality Assurance test cases page from [anitab-org/mentorship backend](https://github.com/anitab-org/mentorship-backend) repository.

Resources: - [Apk branch](https://github.com/anitab-org/mentorship-android/tree/apk) - [app-debug.apk](https://github.com/anitab-org/mentorship-android/blob/apk/app-debug.apk) - [Quality Assurance test cases](https://github.com/anitab-org/mentorship-backend/blob/develop/docs/quality-assurance-test-cases.md)
