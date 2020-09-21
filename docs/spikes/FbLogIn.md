# Facebook Log In Process
**Register**
- Login using your Facebook account to: [Facebook For Developers](]https://developers.facebook.com/apps/).

![](https://cdn.journaldev.com/wp-content/uploads/2018/03/android-login-with-fb-homepage.png)
- Now create a new app. And set the app name and the email id as shown in the below pic.

![](https://cdn.journaldev.com/wp-content/uploads/2018/03/android-login-with-fb-set-app-name.png)

    Note: Facebook doesn’t allow us to set app names which contain the keyword fb/facebook etc.

- You shall see the dashboard in the next screen. Choose Facebook Login Setup and select the platform as Android as shown in the below images.

![](https://cdn.journaldev.com/wp-content/uploads/2018/03/android-login-with-fb-choose-facebook-login.png)


![](https://cdn.journaldev.com/wp-content/uploads/2018/03/android-login-with-fb-choose-platform.png)

**Integrate the Facebook SDK**

The Facebook Login SDK for Android is a component of the [Facebook SDK for Android](https://developers.facebook.com/docs/android/componentsdks). To use the Facebook Login SDK in your project, make it a dependency in Maven, or download it.

Using Maven
In your project, open ```your_app > Gradle Scripts > build.gradle (Project)``` make sure the following repository is listed in the buildscript { repositories {}}:
```
jcenter()
```
In your project, ```open your_app > Gradle Scripts > build.gradle (Module: app)``` and add the following implementation statement to the dependencies{} section to depend on the latest version of the Facebook Login SDK:
```
 implementation 'com.facebook.android:facebook-login:[5,6)'Copy Code
 ```
Build your project.

**Edit Your Resources and Manifest**

Create strings for your Facebook app ID and for those needed to enable Chrome Custom Tabs. Also, add FacebookActivity to your Android manifest.
Open your ```/app/res/values/strings.xml``` file.
Add the following:
```
<string name="facebook_app_id">[APP_ID]</string>
<string name="fb_login_protocol_scheme">fb[APP_ID]</string>
```
Open the ```/app/manifest/AndroidManifest.xml``` file.
Add the following uses-permission element after the application element:
```
  <uses-permission android:name="android.permission.INTERNET"/>
```
Add the following meta-data element, an activity for Facebook, and an activity and intent filter for Chrome Custom Tabs inside your application element:
```
<meta-data android:name="com.facebook.sdk.ApplicationId" 
        android:value="@string/facebook_app_id"/>
    
    <activity android:name="com.facebook.FacebookActivity"
        android:configChanges=
                "keyboard|keyboardHidden|screenLayout|screenSize|orientation"
        android:label="@string/app_name" />
```
The next window asks for the setting up the key hashes. That can be done by running the following commands on mac terminal/windows cmd:

```
//Mac cmd
keytool -exportcert -alias androiddebugkey -keystore ~/.android/debug.keystore | openssl sha1 -binary | openssl base64

//Windows cmd
keytool -exportcert -alias androiddebugkey -keystore "C:\Users\USERNAME\.android\debug.keystore" | "PATH_TO_OPENSSL_LIBRARY\bin\openssl" sha1 -binary | "PATH_TO_OPENSSL_LIBRARY\bin\openssl" base64
Copy and paste the hash key in the Key hashes field below.
You will need to provide a key hash for every device you run this application on till it is in development mode.
```
**Add the facebook log in Button**

The simplest way to add Facebook Login to your app is to add ```LoginButton``` from the SDK. The LoginButton is a UI element that wraps functionality available in the ```LoginManager```. When someone clicks on the button, the login is initiated with the permissions set in the ```LoginManager```. The button follows the login state, and displays the correct text based on someone's authentication state.

To add the ```Facebook Login button```, first add it to your layout XML file:

```
<com.facebook.login.widget.LoginButton
    android:id="@+id/login_button"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="center_horizontal"
    android:layout_marginTop="30dp"
    android:layout_marginBottom="30dp" />
```
**Register a Callback**

Now create a callbackManager to handle login responses by calling ```CallbackManager.Factory.create```.

```
callbackManager = CallbackManager.Factory.create();
```
If you are adding the button to a Fragment you must also update your activity to use your fragment. You can customize the properties of ```Login button``` and register a callback in your ```onCreate()``` or ```onCreateView()``` method. Properties you can customize includes``` LoginBehavior```, ```DefaultAudience```, ```ToolTipPopup.Style``` and permissions on the ```LoginButton```. For example:
```

      
    private static final String EMAIL = "email";
      
    loginButton = (LoginButton) findViewById(R.id.login_button);
    loginButton.setReadPermissions(Arrays.asList(EMAIL));
    // If you are using in a fragment, call loginButton.setFragment(this);    

    // Callback registration
    loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            // App code
        }

        @Override
        public void onCancel() {
            // App code
        }

        @Override
        public void onError(FacebookException exception) {
            // App code
        }
    });
```
To respond to a login result, you need to register a callback with either ```LoginManager``` or ```LoginButton```. If you register the callback with ```LoginButton```, don't need to register the callback on ```Login manager```.

You add the LoginManager callback to your activity or fragment's ```onCreate()``` method:

```

    callbackManager = CallbackManager.Factory.create();

    LoginManager.getInstance().registerCallback(callbackManager,
            new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    // App code
                }

                @Override
                public void onCancel() {
                     // App code
                }

                @Override
                public void onError(FacebookException exception) {
                     // App code   
                }
    });
```
If login succeeds, the ```LoginResult``` parameter has the new ```AccessToken```, and the most recently granted or declined permissions.

You don't need a ```registerCallback``` for login to succeed, you can choose to follow current access token changes with the ```AccessTokenTracker``` class described below.

Finally, in your ```onActivityResult``` method, call ```callbackManager.onActivityResult``` to pass the login results to the ```LoginManager``` via ```callbackManager```.

```

      @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
```
**Check Login Status**

our app can only have one person at a time logged in, and ```LoginManager``` sets the current ```AccessToken``` and ```Profile``` for that person. The FacebookSDK saves this data in shared preferences and sets at the beginning of the session. You can see if a person is already logged in by checking AccessToken.
```getCurrentAccessToken()``` and ```Profile.getCurrentProfile()```.

You can load ```AccessToken.getCurrentAccessToken``` with the SDK from cache or from an app book mark when your app launches from a cold start. You should check its validity in your ```Activity's onCreate``` method
```
AccessToken accessToken = AccessToken.getCurrentAccessToken();
boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
```
Then you can later perform the actual login, such as in a custom button's OnClickListener:
```
LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
```
