# Run an Android App against a localhost API
You can test your client against a backend API running in production App Engine at any time without making any changes. However, if you want to test your client against a backend API running on the local development server, you need to change tne line of code in the client to point to the IP address of the machine running the local development server.

>Note: You can use either an Android Virtual Device (AVD) emulator, or a physical device. However, physical devices must use the same Wi-Fi as the system running the local development server.

To make the required changes and test using the local development server:

1. Make a note of the IP address of the machine that is running the local development server because you need it when you add code to the Android client.

2. Start the local development server, as described in [Running and testing API backends](https://cloud.google.com/endpoints/docs/frameworks/java/test-deploy#gradle_1) locally.

3. In your Android Studio client project, locate the code that gets the handle to the backend API service. Typically, this code uses a Builder to set up the API request.

4. Override the root URL on the Builder object (this is the URL the Android client connects to in the backend API call) by adding the line:

```
yourBuilderObject.setRootUrl("http://YOUR_MACHINE_IP_ADDRESS:8080/_ah/api");
```
                Network instances used by all emulator
![Network instances used by all emulator](https://i.stack.imgur.com/m9eBE.png)

For example:

```
public static Helloworld getApiServiceHandle(@Nullable GoogleAccountCredential credential) {
  // Use a builder to help formulate the API request.
  Helloworld.Builder helloWorld = new Helloworld.Builder(AppConstants.HTTP_TRANSPORT,
      AppConstants.JSON_FACTORY,credential);

  helloWorld.setRootUrl("http://YOUR_MACHINE_IP_ADDRESS:8080/_ah/api");
  return helloWorld.build();
}
```
Replace ```YOUR_MACHINE_IP_ADDRESS``` with your system's IP address.

4. Rebuild your Android client project.
5. If you want to run your client app on an AVD emulator:

     1. In Android Studio, go to **Tools > Android > AVD Manager** and start an existing AVD if you have one, otherwise create one, and then start it.

    2. Go to **Run > Debug** ```YOUR_PROJECT_NAME``` where ```YOUR_PROJECT_NAME``` represents the name of your Google Cloud project.
    3. When prompted to choose a device, select your AVD.
    4. Test your client.
6. If you want to run your client app on a physical Android device:

     1.  Make sure your Android device is [enabled for debugging](http://developer.android.com/tools/device.html#setting-up).

     2. In Android Studio, go to **Run > Debug** ```YOUR_PROJECT_NAME```.

     3. When prompted to choose a device, select your physical Android device.
     4. Test your client.

     >Important: If the backend API is Auth-protected, your client must provide support as described in Adding authentication support with OAuth 2.0. However, when running against the local development server, no credentials page is displayed in response to a user's attempt to sign in. Instead, the sign-on simply occurs, granting the user access to the protected API, and the user returned is always example@example.com.

Resources

- [Google Backend ApI](https://cloud.google.com/endpoints/docs/frameworks/java/calling-from-android)
- [StackOverflow](https://stackoverflow.com/questions/5806220/how-to-connect-to-my-http-localhost-web-server-from-android-emulator)
