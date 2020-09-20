# Inserting a login through Google Button in the Login Page
- **To integrate the Google Sign-In API in our app, we need to configure our app to Google developer account and download the 'google-service.json' file for the Android application.**
    - Create a Google developer account at [Google Android Signin](https://developers.google.com/identity/sign-in/android/start-integrating) and click on 'GET A CONFIGURATION FILE'.  

![](https://static.javatpoint.com/androidpages/images/android-googlesignin-integration.png)
 - Fill all the application detail and select your country/region and click 'Choose and configure services'.

![](https://static.javatpoint.com/androidpages/images/android-googlesignin-integration2.png)

- After successful creation of the Google app support configuration, it will redirect to next windows for selecting Google services. will select Google Sign-In service.

![](https://static.javatpoint.com/androidpages/images/android-googlesignin-integration3.png)

 - Now, we need to provide signing certification SHA-1 key of our application
   - There are two different ways of generating certification SHA-1 key.
        - By using the command prompt.
        **Windows**
        ```
        keytool -exportcert -list -v \  
        -alias androiddebugkey -keystore %USERPROFILE%\.android\debug.keystore
        ```  
        **Mac/Linux**
        ```
        keytool -exportcert -list -v \  
        -alias androiddebugkey -keystore ~/.android/debug.keystore  
        ```
        - By Android Studio itself.
           - Open Android project.
           - Open the Gradle tab from a right side panel.
           - Double click on 'signingReport'.
           - We will found our app SHA-1 key on 'Gradle Console'.

 ![](https://static.javatpoint.com/androidpages/images/android-googlesignin-integration5.png)

- Paste the generated SHA-1 key to Google Sign-In service and click on 'ENABLE GOOGLE SIGN-IN' and 'Generate certification files'.

 ![](https://static.javatpoint.com/androidpages/images/android-googlesignin-integration6.png)

 - Now download the 'google-services.json' file to integrate it into the Android application.

      ![](https://static.javatpoint.com/androidpages/images/android-googlesignin-integration7.png)


We need to paste the downloaded 'google-services.json' file in our Android project app directory.
**Required Permission**
- Add the Internet permission in [manifest file](app/src/main/AndroidManifest.xml) file.
```
<uses-permission android:name="android.permission.INTERNET" />  
```
**Adding Dependancies**
- in your app-level build.gradle file, declare Google Play services as a dependency:
```
    apply plugin: 'com.android.application'
    ...
    dependencies {
        implementation 'com.google.android.gms:play-services-auth:18.1.0'
    } 
```
-build.gradle(Module)
```
dependencies {  
    implementation 'com.google.android.gms:play-services-auth:11.6.0'  
    implementation 'com.github.bumptech.glide:glide:3.7.0'  
}  
apply plugin: 'com.google.gms.google-services'  
```
**Configure Google Sign-in and the GoogleSignInClient object**
- In your sign-in activity's ```onCreate``` method, configure Google Sign-In to request the user data required by your app. For example, to configure Google Sign-In to request users' ID and basic profile information, create a [GoogleSignInOptions](https://developers.google.com/android/reference/com/google/android/gms/auth/api/signin/GoogleSignInOptions.Builder#GoogleSignInOptions.Builder()) object with the ```DEFAULT_SIGN_IN``` parameter. To request users' email addresses as well, create the ```GoogleSignInOptions``` object with the ```requestEmail option```.
```
// Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build();
```
If you need to request additional scopes to access Google APIs, specify them with [requestScopes](https://developers.google.com/android/reference/com/google/android/gms/auth/api/signin/GoogleSignInOptions.Builder#requestScopes(com.google.android.gms.common.api.Scope,%20com.google.android.gms.common.api.Scope...))
- Then, also in your sign-in activity's ```onCreate``` method, create a ```GoogleSignInClient``` object with the options you specified.
```
// Build a GoogleSignInClient with the options specified by gso.
mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
```
**Check for an existing signed-in user**
In your activity's ```onStart``` method, check if a user has already signed in to your app with Google.
```
// Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
updateUI(account);
```
If ```GoogleSignIn.getLastSignedInAccount``` returns a ```GoogleSignInAccount``` object (rather than null), the user has already signed in to your app with Google. Update your UI accordingly—that is, hide the sign-in button, launch your main activity, or whatever is appropriate for your app.

If ```GoogleSignIn.getLastSignedInAccount``` returns ```null```, the user has not yet signed in to your app with Google. Update your UI to display the Google Sign-in button.
**Add the Google Sign-in button to your app**
- Add the SignInButton in your application's layout:
```
<com.google.android.gms.common.SignInButton
 android:id="@+id/sign_in_button"
 android:layout_width="wrap_content"
 android:layout_height="wrap_content" />
 ```
- Optional: If you are using the default sign-in button graphic instead of providing your own sign-in button assets, you can customize the button's size with the setSize method.
```
// Set the dimensions of the sign-in button.
SignInButton signInButton = findViewById(R.id.sign_in_button);
signInButton.setSize(SignInButton.SIZE_STANDARD);
```

- In the Android activity (for example, in the onCreate method), register your button's OnClickListener to sign in the user when clicked:
```
findViewById(R.id.sign_in_button).setOnClickListener(this);
```
**Start the sign-in flow**
- In the activity's onClick method, handle ```sign-in button``` taps by creating a sign-in intent with the ```getSignInIntent``` method, and starting the intent with startActivityForResult
```
@Override
public void onClick(View v) {
    switch (v.getId()) {
        case R.id.sign_in_button:
            signIn();
            break;
        // ...
    }
}
```
- After the user signs in, you can get a GoogleSignInAccount object for the user in the activity's onActivityResult method.
```
@Override
public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
    if (requestCode == RC_SIGN_IN) {
        // The Task returned from this call is always completed, no need to attach
        // a listener.
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        handleSignInResult(task);
    }
}
```

The GoogleSignInAccount object contains information about the signed-in user, such as the user's name.
```
private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
    try {
        GoogleSignInAccount account = completedTask.getResult(ApiException.class);

        // Signed in successfully, show authenticated UI.
        updateUI(account);
    } catch (ApiException e) {
        // The ApiException status code indicates the detailed failure reason.
        // Please refer to the GoogleSignInStatusCodes class reference for more information.
        Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        updateUI(null);
    }
}
```
You can also get the user's email address with getEmail, the user's Google ID (for client-side use) with getId, and an ID token for the user with getIdToken. If you need to pass the currently signed-in user to a backend server, send the ID token to your backend server and validate the token on the server.

Sources:
- [Configuration](https://www.javatpoint.com/android-googlesignin-integrating)

- [Developers-Google](https://developers.google.com/identity/sign-in/android/sign-in)