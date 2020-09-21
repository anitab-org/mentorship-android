# Alternate Ways to Login

<p align="center">
     <img src="https://miro.medium.com/max/272/1*zBpmnu_GmOuagTE9ahrFsQ.png" height="350" width="272"/>
</p>

<b>OAuth</b> and <b>OAuth 2.0</b> are widely used authorization protocols which alert resource providers (e.g. Facebook, Twitter, Google, Microsoft etc.) every time resource owners give permission to third parties to run HTTP applications without exposing user passwords. It is a delegated authorization framework for REST/APIs. It enables apps to obtain limited access (scopes) to a user’s data without giving away a user’s password</td>

## Contents

- <a href="#what-is-oauth-20-">What is OAuth 2.0</a>
- <a href="#how-oauth-20-works-">How OAuth 2.0 works</a>
- <a href="#signing-in-with-google-">Signing In with Google</a>
     - <a href="#plain-oauth-20">Plain OAuth 2.0</a>
     - <a href="#google-sign-in-for-android">Google Sign-In for Android</a>
     - <a href="#one-tap-sign-in-and-sign-up">One Tap Sign-In/Sign-Up</a>
- <a href="#signing-in-with-github-">Signing In with GitHub</a>
     - <a href="#creating-a-github-oauth-app">Creating a GitHub OAuth App</a>
     - <a href="#authorizing-oauth-apps">Authorizing OAuth Apps</a>
     - <a href="#use-the-access-token-to-access-the-api">Use the access token to access the API</a>
- <a href="#other-ways-to-login">Other ways to Sign-In</a>

## What is OAuth 2.0 ?

- OAuth is an open-standard authorization protocol or framework that describes how unrelated servers and services can safely allow authenticated access to their assets without actually sharing the initial, related, single logon credential. In authentication parlance, this is known as secure, third-party, user-agent, delegated authorization.

- OAuth is about authorization and not authentication. Authorization is asking for permission to do stuff. Authentication is about proving you are the correct person because you know things. OAuth doesn’t pass authentication data between consumers and service providers – but instead acts as an authorization token of sorts.

- OAuth doesn’t share password data but instead uses authorization tokens to prove an identity between consumers and service providers. OAuth is an authentication protocol that allows you to approve one application interacting with another on your behalf without giving away your password.

- **OAuth 2.0** is faster and easier to implement. It has six flows for different types of applications and requirements, and enables signed secrets over HTTPS. The tokens are encrypted in transit.

## How OAuth 2.0 works ?

<p>This is how an OAuth 2.0 workflow looks like. (Image take from <a href="https://api.slack.com/docs/sign-in-with-slack">Slack's Authentication Page</a> ).</p>


<p align="center">
  <img src="https://a.slack-edge.com/fbd3c/img/api/articles/oauth_scopes_tutorial/slack_oauth_flow_diagram.png"  width="720"/>
</p>


**Steps are as follows :**
<ol>
     <li>Before your application can access private data, app requests authorization from the user
     <li>Some requests require an authentication step where the user logs in with their respective account. After logging in, the user is asked whether they are willing to grant one or more permissions that your application is requesting. This process is called user consent.
     <li>After the User authorizes app and grants permission, app presents the received grant to the server to get a Token
     <li>Token is restricted to only access what the User authorized for the specific App
</ol>

## Signing In With Google <img src="https://i.pinimg.com/originals/39/21/6d/39216d73519bca962bd4a01f3e8f4a4b.png" width="30"/>

Google provides various ways to Sign In using their **Google Identity Platform**. You can read more about it <a href="https://developers.google.com/identity/choose-auth?authuser=0">here</a>. Three of these identity solutions can be easily used to sign in users into this application. All of them are based on the same principle of OAuth but the way in which they are implemented is different. These are explained below : 

- Plain OAuth 2.0
- Google Sign-In (Most Used)
- One Tap Sign-In and Sign-Up (Latest and Recommended)

### Plain OAuth 2.0

- It is a plain implementation of the OAuth 2.0 without any fancy libraries on android.
- As app cannot keep secrets, Google APIs can be accessed while the user is present at the app or when the app is running in the background.
- The Google OAuth 2.0 endpoint supports applications that are installed on mobile devices, and tablets. When you create a client ID through the <a href="https://console.developers.google.com/?authuser=0&pli=1">Google API Console</a>, specify that this is an Installed application, then select Android, as the application type.
- The authorization sequence begins when your application redirects a browser to a Google URL; the URL includes query parameters that indicate the type of access being requested. Google handles the user authentication, session selection, and user consent. The result is an authorization code, which the application can exchange for an access token and a refresh token.
- The application should store the refresh token for future use and use the access token to access a Google API. Once the access token expires, the application uses the refresh token to obtain a new one.

<p align="center">
     <img src="https://developers.google.com/identity/protocols/oauth2/images/flows/authorization-code.png?authuser=0"/>
</p>

- <b>Drawbacks</b>
     - Complex to implement
     - A browser window is opened to authenticate a user which is not a good user experience

- A detailed implementation for the same can be found <a href="https://developers.google.com/identity/protocols/oauth2/native-app?authuser=0">here</a>.
- For server side (flask) implementation, please refer <a href="https://developers.google.com/identity/protocols/oauth2/web-server?authuser=0">this</a>.


### Google Sign-In for Android

- By using this method, users can sign into the app quickly and securely using the Google Accounts they already have.

- For Android, Google Sign-In method is preferred over Plain OAuth 2.0. Following are its <b>advantages</b> over Plain OAuth 2.0 :

     - The Google Sign-in client libraries handle authentication and user authorization, and they are simpler to implement than the lower-level protocol.
     - A Sign-In intent is triggerred instead of opening a browser.
     - Users sign in once and are authenticated on all of their devices.

- <b>Drawbacks</b> :
     - Most of the documentation is still in Java The Programming Language. So, it'll be a bit tougher to apply this using Kotlin.
     
- Steps to integrate Google Sign-In: 
     - Refer <a href="">this link</a> to configure a Google API Console project for android devices by providing SHA-1 hash and the package name and to generate backend server's OAuth 2.0 client ID 
     - Follow <a href="https://developers.google.com/identity/sign-in/android/sign-in?authuser=0">this</a> to Configure Google Sign-in and the GoogleSignInClient object and to add a sign in button in the app. 
     - Button : 
     <br><p align="center"><img src="https://developers.google.com/identity/images/btn_google_signin_light_normal_web.png?authuser=0"/></p>
     - An intent similar to the image below is triggered whenever the user clicks on the button : 
     <br><p align="center"><img src="https://developers.google.com/identity/sign-in/android/images/choose-account.png?authuser=0" height="240"/></p>
     - <b>Mentorship-Backend</b> is written in Python. So, it's necessary to communicate with the backend. To do so securely, after a user successfully signs in, send the user's ID token to your server using HTTPS. Then, on the server, verify the integrity of the ID token and use the user information contained in the token to establish a session or create a new account.
     - Google provides a wide range of OAuth libraries to make the <b>backend</b> coding easier. For example <a href="https://github.com/googleapis/google-api-python-client">google api python client</a>. Things like verifying the token can be done easily using these libraries.
     
     ```python
          from google.oauth2 import id_token
          from google.auth.transport import requests

          # (Receive token by HTTPS POST)
          # ...

          try:
               # Specify the CLIENT_ID of the app that accesses the backend:
               idinfo = id_token.verify_oauth2_token(token, requests.Request(), CLIENT_ID)
          
               # ID token is valid. Get the user's Google Account ID from the decoded token.
               userid = idinfo['sub']
               
          except ValueError:
               # Invalid token
               pass
     ```
     
     - <a href="https://developers.google.com/identity/sign-in/android/backend-auth?authuser=0">This guide</a> explains how authentication can be done using a backend server. It walks you through the steps like: 
     
          - Sending id to the server
          - Verifying the id token
          - Calling the tokenifo endpoint to get the details of the user
          - Creating an account or session


### One Tap Sign-In and Sign-Up

- One Tap is a new cross-platform sign-in mechanism for Android and Web, supporting and streamlining multiple types of credentials. It has an updated user experience and other improvements and is a recommended mechanism by Google.

- <b>Advantages: </b>
     - Sign up new users with one tap.
     - Frictionlessly sign in returning users to the Android app  when they have saved passwords or Google Account credentials.
     - Simple client API calls.
     - Better documentation in Kotlin

- <b>One Tap Sign-In :</b> (<a href="https://developers.google.com/identity/one-tap/android/get-saved-credentials?authuser=0">Link</a>) <br>

     <p align="center">
          <img src="https://developers.google.com/identity/one-tap/android/images/signin-16-9_720.png?authuser=0"/>
     </p>

     - Returning users can sign in to the app with one tap on any device, whether they use a Google Account or a password.
     - Can be helpful even if you already have a <b>"Sign in with Google"</b> button: because the One Tap UI can be configured to show only credentials the user previously used to sign-in, it can be a reminder to users who infrequently sign in how they signed in last time, and prevent them from accidentally creating new accounts with your app.
     
- <b>One Tap Sign-Up and integrating it with backend:</b> (<a href="https://developers.google.com/identity/one-tap/android/create-new-accounts?authuser=0">Link</a>)<br>
     
     <p align="center">
          <img src="https://developers.google.com/identity/one-tap/android/images/signup-16-9_720.png?authuser=0"/>
     </p>
     
     - New users can sign up with just one tap, without being interrupted by a sign-up screen. Users get a secure, token-based, passwordless account with your app, protected by their Google Account.
     - Gives users a frictionless account creation experience that never takes them out of the context of your app.
     - When you display the One Tap UI, users are prompted to create a new account with your app using one of the Google Accounts on their device. If the user chooses to continue, you get an ID token with basic profile information—their name, profile photo, and their verified email address—which can be used to create the new account.
     - The One Tap sign-in client retrieves a Google ID token when the user selects a Google Account. An ID token is a signed assertion of a user's identity that also contains a user's basic profile information, possibly including an email address that has been verified by Google.
When ID tokens are available, you can use them to securely authenticate with your app's backend, or to automatically sign up the user for a new account without the need to verify the user's email address.
     - To sign in or sign up a user with an ID token, send the token to your app's backend. On the backend, verify the token using either a Google API client library or a general-purpose JWT library. If the user hasn't signed in to your app with this Google Account before, create a new account.
     - Its backend implementation is very similar to the one mentioned above in Google Sign-In method. Here's a <a href="https://developers.google.com/identity/one-tap/android/idtoken-auth?authuser=0">link</a> for the same. 


<br><br>

## Signing In with GitHub <img src="https://image.flaticon.com/icons/png/512/25/25231.png" width=30/>

Unlike Google, GitHub doesn't provide any native APIs with an intent that can be accessed while the user is present at the app. So, the only way to implement GitHub Sign-In feature is by using plain OAuth. This requires a user to login and grant access/permission within a browser. After that, the user is again redirected to the app.
First, we need to create the **Client ID and Client Secret** on the GitHub website. To do so, we need to create a **GitHub OAuth App**.

### Creating a GitHub OAuth App

- OAuth App can be registered under any personal account or an organization.
- Whole process is properly documented <a href="https://docs.github.com/en/developers/apps/creating-an-oauth-app">here</a>. Remember to properly enter the <code>Homepage URL</code> and <code>Callback URL</code>.
- Following the steps above will generate **Client ID and Client Secret**.
- After registering the OAuth App, you'll see a screen similar to the screen below :
<img src="https://i.stack.imgur.com/IpYtM.png"/>
<br>

### Authorizing OAuth Apps

- As we've generated the Client ID and Client Secret above, we'll follow the <a href="https://docs.github.com/en/developers/apps/authorizing-oauth-apps#web-application-flow">web application flow</a> to authorize users for the app i.e. :

     1. Users are redirected to request their GitHub identity
     2. Users are redirected back to the app by GitHub
     3. App accesses the API with the user's access token

- All the APIs related to OAuth can only be called through the backend. None of that can be done on the Android app as it is a web application flow. 

- <b>Request a user's GitHub identity : </b> When your GitHub App specifies a login parameter, it prompts users with a specific account they can use for signing in and authorizing your app.

```curl
     GET https://github.com/login/oauth/authorize
```

<b>Parameters : </b>

     - client_id
     - redirect_uri
     - scope

You can read more about the parameters <a href="https://docs.github.com/en/developers/apps/authorizing-oauth-apps#parameters-1">here</a>.
By default, the response takes the following <code>JSON</code> form:

```json
     {
          "access_token":"e72e16c7e42f292c6912e7710c838347ae178b4a",
          "scope":"repo,gist",
          "token_type":"bearer"
     }
```

### Use the access token to access the API

The access token allows you to make requests to the API on a behalf of a user. Set the <b>Authorization</b> header and then call a <code>GET</code> request as follows :

```curl
     Authorization: token OAUTH-TOKEN
     GET https://api.github.com/user
```

You'll get the required user's info.
Now, the received info can be used to Sign-In or Sign-Up the user. 
<br>


## Other ways to login

- <b>Facebook</b> [<a href="https://developers.facebook.com/docs/facebook-login/android">Link</a>]

- <b>Slack</b> [<a href="https://api.slack.com/docs/sign-in-with-slack">Link</a>]
<br>
