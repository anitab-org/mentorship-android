# TESTING THE WORKFLOW : Accept mentorship relation request

## **Test-1 : signing-up/registration**
Click the sign up button for registering an account and fill the details


> ### **positive tests**

#### *test 1.1: All the details are filled correctly :*


<img src="https://user-images.githubusercontent.com/53532851/93902073-cbe24b80-fd14-11ea-8c15-f769be7c950c.gif" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"user account created successfully"
##### ACTUAL RESULT: 
"user account created successfully"

> ### **negative tests**



#### *test 1.2: Format for name is incorrect :*

<img src="https://user-images.githubusercontent.com/53532851/93903313-20d29180-fd16-11ea-84c7-f732f8a145d8.jpeg" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"Your name is invalid"
##### ACTUAL RESULT: 
"Your name is invalid"

#### *test 1.3: Format for username is incorrect :*

<img src="https://user-images.githubusercontent.com/53532851/93903317-229c5500-fd16-11ea-8dc9-834b3b8a6b8e.jpeg" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"The username has to longer than 4 characters and shorter than 26 characters"
##### ACTUAL RESULT: 
"The username has to longer than 4 characters and shorter than 26 characters"

#### *test 1.4: Format for email is incorrect :*

<img src="https://user-images.githubusercontent.com/53532851/93903321-24661880-fd16-11ea-9df0-d5b0360d81fe.jpeg" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"Your email is invalid"
##### ACTUAL RESULT: 
"Your email is invalid"

#### *test 1.5: Format for password is incorrect :*

<img src="https://user-images.githubusercontent.com/53532851/93903325-25974580-fd16-11ea-81e7-8df02dd6ca6b.jpeg" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"Your password is too weak"
##### ACTUAL RESULT: 
"Your password is too weak"

#### *test 1.6: Mentor/mentee/both selected none :*

<img src="https://user-images.githubusercontent.com/53532851/93903328-26c87280-fd16-11ea-90a9-e05682c99105.jpeg" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"Please choose atleast one of the following options"
##### ACTUAL RESULT: 
"Please choose atleast one of the following options"



## **Test-2 : Email confirmation**
confirming email after successfully signing up


#### *test 2.1: email confirmed*
<img src="https://user-images.githubusercontent.com/53532851/93903499-5a0b0180-fd16-11ea-9bcb-d22752a3dfd9.png" alt ="registration" width="800"/>
<img src="https://user-images.githubusercontent.com/53532851/93903525-62fbd300-fd16-11ea-9d76-29c7d389b076.png" alt ="registration" width="800"/>

##### EXPECTED RESULT: 
"you have confirmed your account"
##### ACTUAL RESULT: 
"you have confirmed your account"



## **Test-3 : Login**
logging in the account


> ### **positive tests**

#### *test 3.1: login via verified email address :*

<img src="https://user-images.githubusercontent.com/53532851/93901760-7b6aee00-fd14-11ea-8e07-8728d96af082.gif" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"Logging successful"
##### ACTUAL RESULT: 
"Logging successful"

#### *test 3.2: login via username :*

<img src="https://user-images.githubusercontent.com/53532851/93901717-7148ef80-fd14-11ea-96ef-7d2911619eba.gif" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"Logging successful"
##### ACTUAL RESULT: 
"Logging successful"


> ### **negative tests**

#### *test 3.3: entering wrong password :*

<img src="https://user-images.githubusercontent.com/53532851/93903799-c38b1000-fd16-11ea-8db1-bf8f36a64670.jpeg" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"password incorrect"
##### ACTUAL RESULT: 
`"something went wrong"`


#### *test 3.4: logging before confirming email :*

<img src="https://user-images.githubusercontent.com/53532851/93914882-ca208400-fd24-11ea-923c-7af3a700d1c1.gif" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"please verify your email"
##### ACTUAL RESULT: 
"please verify your email"



## **Test-4 : sending mentorship request**
sending request to be a mentor or mentee to another user


> ### **positive**

#### *test 4.1: sending request to a user :*

<img src="https://user-images.githubusercontent.com/53532851/93903804-c4bc3d00-fd16-11ea-8752-c0d296030de4.jpeg" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"mentorship relation was sent successfully"


##### ACTUAL RESULT: 
"mentorship relation was sent successfully"

> ### **negative**

#### *test 4.2: sending request to the same user again :*

<img src="https://user-images.githubusercontent.com/53532851/93901623-5a0a0200-fd14-11ea-9beb-4cdfba5a1eb0.gif" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"mentorship relation request already sent"


##### ACTUAL RESULT: 
`"mentorship relation was sent successfully"`




#### *test 4.3: sending request for short period of time :*

<img src="https://user-images.githubusercontent.com/53532851/93903815-c6860080-fd16-11ea-8a4d-e88e09f33a95.jpeg" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"mentorship relation minimum duration is 4 week"


##### ACTUAL RESULT: 
"mentorship relation minimum duration is 4 week"


#### *test 4.4: sending request to a user to be mentor who have not selected to be a mentor :*

<img src="https://user-images.githubusercontent.com/53532851/93901805-8a51a080-fd14-11ea-97bd-1e076376e46b.gif" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"mentor user not available to be a mentor"


##### ACTUAL RESULT: 
"mentor user not available to be a mentor"


#### *test 4.5: sending request to a user who is already in relation:*

<img src="https://user-images.githubusercontent.com/53532851/93901671-68f0b480-fd14-11ea-8bd3-8e08ea1ecbb8.gif" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"mentor user already in relation"


##### ACTUAL RESULT: 
"mentor user already in relation"





## **Test-5 : Accepting mentorship request**
accepting request to be a mentor or mentee by another user

> ### **positive**

#### *test 5.1: accepting successfully :*

<img src="https://user-images.githubusercontent.com/53532851/93903821-c8e85a80-fd16-11ea-80fe-1977c3271230.jpeg" alt ="registration" width="200"/>
<img src="https://user-images.githubusercontent.com/53532851/93903829-ca198780-fd16-11ea-8a36-d0ab562e45ea.jpeg" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"mentorship relation was accepted successfully"


##### ACTUAL RESULT: 
"mentorship relation was accepted successfully"


> ### **negative**


#### *test 5.2: accepting more than one mentor relation:*

<img src="https://user-images.githubusercontent.com/53532851/93901808-8b82cd80-fd14-11ea-991f-5566b8fddbe2.gif" alt ="registration" width="200"/>

##### EXPECTED RESULT: 
"you are currently involved in a mentorship relation"


##### ACTUAL RESULT: 
"you are currently involved in a mentorship relation"
