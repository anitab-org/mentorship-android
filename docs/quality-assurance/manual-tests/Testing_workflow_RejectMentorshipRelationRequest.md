# Testing the workflow: Reject mentorship relation request
## Test 1: Signing-Up
<img src="https://user-images.githubusercontent.com/57208352/93809382-d47e4780-fc6a-11ea-972d-2ef3d9be2b3d.gif" alt ="Boarding" width="300"/>
<img src="https://user-images.githubusercontent.com/57208352/93809388-d7793800-fc6a-11ea-9c59-826c5839f3fe.gif" alt ="HomeToSignUp" width="300"/>

After scrolling across the boarding pages we click the tick button and reach the Sign-In Page. From there we reach the Sign-Up page
### Negative tests
### 1.1 Email Id already registered:
<img src="https://user-images.githubusercontent.com/57208352/93809394-d8aa6500-fc6a-11ea-97d9-3fbf6ec0505f.png" width="300"/>

##### Expected Result: 
"User with that email already exists"
##### Actual Result:
"User with that email already exists"

### 1.2 Format for name is incorrect:
<img src="https://user-images.githubusercontent.com/57208352/93809397-d942fb80-fc6a-11ea-9744-e8ab92764805.png" width="300"/>

##### Expected Result: 
"Your name is invalid"
##### Actual Result:
"Your name is invalid"

### 1.3 Weak Password
<img src="https://user-images.githubusercontent.com/57208352/93809399-d942fb80-fc6a-11ea-8183-0df4abc785e0.png" width="300"/>

##### Expected Result: 
"Your password is too weak"
##### Actual Result:
"Your password is too weak"

### 1.4 Password Mismatch:
<img src="https://user-images.githubusercontent.com/57208352/93809402-dc3dec00-fc6a-11ea-896d-b6f270d4879f.png" width="300"/>

##### Expected Result: 
"Password did not match"
##### Actual Result:
"Password did not match"

### 1.5 Username already exists:
<img src="https://user-images.githubusercontent.com/57208352/93809405-dcd68280-fc6a-11ea-9578-0716365d67ea.png" width="300"/>

##### Expected Result: 
"A user with that username already exists"
##### Actual Result:
"A user with that username already exists"

### 1.6 Positive test:  All the credentials filled are correct
<img src="https://user-images.githubusercontent.com/57208352/93809391-d811ce80-fc6a-11ea-840c-e8b738054841.png" alt ="Succesful Log" width="300"/>

##### Expected Result: 
"User was created succesfully" message
##### Actual Result:
"User was created succesfully" message

###### The User now recieves a mail for confirmation
<img src="https://user-images.githubusercontent.com/57208352/93809410-de07af80-fc6a-11ea-8fa3-a9a1add8c223.png" width="300"/>
<img src="https://user-images.githubusercontent.com/57208352/93809412-dea04600-fc6a-11ea-8e82-b6bbab59df00.png" width="300"/>

## Test 2: Log In
### Negative tests:

### 2.1 Login before confirming the registration:
<img src="https://user-images.githubusercontent.com/57208352/93821781-b588b100-fc7c-11ea-8662-46fd09b4ce96.png" width="300"/>

##### Expected Result: 
"Please verify your email before login"
##### Actual Result:
"Please verify your email before login"

### 2.2 Invalid password:
<img src="https://user-images.githubusercontent.com/57208352/93809417-dfd17300-fc6a-11ea-94ef-a47537cac861.gif" width="300"/>

##### Expected Result: 
"Password/Username is incorrect"
##### Actual Result:
Bug

### 2.3 Positive test: Login credentials are correct
<img src="https://user-images.githubusercontent.com/57208352/93809415-dea04600-fc6a-11ea-86b7-959dd11ebad0.gif" width="300"/>

##### Expected Result: 
"Logging Successful"
##### Actual Result:
"Logging Successful"

## Test 3: Finding Mentor and sending relation request
###### The user searches for another user and views the profile
<img src="https://user-images.githubusercontent.com/57208352/93809421-e102a000-fc6a-11ea-9b3d-ab9763811f47.gif" width="300"/>
<img src="https://user-images.githubusercontent.com/57208352/93809423-e2cc6380-fc6a-11ea-9f20-486d6031e7a5.png" width="300"/>

#### 3.1 Positive test: Sends Request
<img src="https://user-images.githubusercontent.com/57208352/93809426-e2cc6380-fc6a-11ea-9773-54960d09b17d.gif" width="300"/>

##### Expected Result: 
"Mentorship request was sent succesfully"
##### Actual Result:
"Mentorship request was sent succesfully"

#### 3.2 Negative test: the other person is not a mentor
<img src="https://user-images.githubusercontent.com/57208352/93821785-b7eb0b00-fc7c-11ea-97d0-9d1b63fb40be.png" width="300"/>

##### Expected Result: 
"Mentor User is not available to user"
##### Actual Result:
"Mentor User is not available to user"

## Test 4: Receiving the relation request and rejecting

<img src="https://user-images.githubusercontent.com/57208352/93809428-e3fd9080-fc6a-11ea-8646-a221f4d00294.png" width="300"/>

#### The mentor receives a mail. On opening the app, ze can see pending requests.

<img src="https://user-images.githubusercontent.com/57208352/93809430-e4962700-fc6a-11ea-90f7-0fcca45d355c.png" width="300"/>

#### On clicking the Requests button in the bottom bar

<img src="https://user-images.githubusercontent.com/57208352/93809436-e5c75400-fc6a-11ea-88ea-704f1557a158.png" width="300"/>

#### 4.1 Positive test: rejects request
<img src="https://user-images.githubusercontent.com/57208352/93809437-e5c75400-fc6a-11ea-878c-a3ef30feb0eb.gif" width="300"/>

##### Expected Result: 
"Mentorship request was rejected succesfully"
##### Actual Result:
"Mentorship request was rejected succesfully"

#### Now,the first user who sent the request can see the count of rejected requests increased in the home screen

<img src="https://user-images.githubusercontent.com/57208352/93809440-e65fea80-fc6a-11ea-8bec-ebda41b2549d.png" width="300"/>
