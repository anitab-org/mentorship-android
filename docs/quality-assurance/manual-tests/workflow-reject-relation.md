# Test and Document workflow - Reject mentorship relation request

### *Tests in :-*
1. Registration/SignUp in app
2. Email Confirmation
3. Log In in app
4. Find members and Send Mentorship relation request to member
5. View request and Reject request
6. After reject the mentorship relation request

### Test 1 - Registration/ Sign Up

Register/ Sign Up yourself in App 

### *Negative Test Scenarios**

* **Test 1.1:-** sign up with empty fields.

<img src = "https://user-images.githubusercontent.com/42871956/93993691-abfe6680-fdac-11ea-8385-79bc61dc89f2.gif" width="315" height="620">

##### **EXPECTED RESULT:** 
Show an error according to related input fields are empty.
##### **ACTUAL RESULT:**
Show an error according to related input fields are empty.


* **Test 1.2:-** Enter name with incorrect format (like - contains digits, special symbols)

<img src = "https://user-images.githubusercontent.com/42871956/93993891-ea942100-fdac-11ea-8d32-44a8a47bb3a5.jpg" width="315" height="620">

##### **EXPECTED RESULT:** 
Must Show an error - name is invalid.
##### **ACTUAL RESULT:**
Which are same as expected result, it shows name is invalid.

* **Test 1.3:-** Enter username already exists.

<img src = "https://user-images.githubusercontent.com/42871956/93994022-0dbed080-fdad-11ea-84e4-b015e63859dd.gif" width="315" height="620">

##### **EXPECTED RESULT:** 
Must show username already exists.
##### **ACTUAL RESULT:**
username already exists.

* **Test 1.4:-** Enter username less than 5 characters.

<img src = "https://user-images.githubusercontent.com/42871956/93994248-4eb6e500-fdad-11ea-8e38-532fd779add2.gif" width="315" height="620">

##### **EXPECTED RESULT:**
Must show an error- username must longer than 4 characters.
##### **ACTUAL RESULT:**
Username must longer than 4 character and shorter than 26 characters

* **Test 1.5:-** Enter invalid emial id 

<img src = "https://user-images.githubusercontent.com/42871956/93994486-90e02680-fdad-11ea-96b0-090caa4bb2f1.gif" width="315" height="620">

##### **EXPECTED RESULT:**
Must Show an error- Email is invalid
##### **ACTUAL RESULT:**
Email is invalid.

* **Test 1.6:-** Enter emial id which is already registered. 

<img src = "https://user-images.githubusercontent.com/42871956/93994680-c7b63c80-fdad-11ea-936d-448609dddbe4.gif" width="315" height="620">

##### **EXPECTED RESULT:**
 Must Show an error- Email already exists.
##### **ACTUAL RESULT:**
 email already exists.

* **Test 1.7:-** Enter weak password

<img src = "https://user-images.githubusercontent.com/42871956/93994819-f6ccae00-fdad-11ea-840a-d8307e3b6a90.jpg" width="315" height="620">

##### **EXPECTED RESULT:** 
Must show an error- password is too weak
##### **ACTUAL RESULT:**
 shows password is too weak.

* **Test 1.8:-** Enter different confirm password

<img src = "https://user-images.githubusercontent.com/42871956/93994957-0f3cc880-fdae-11ea-8518-2a3d31662516.jpg" width="315" height="620">

##### **EXPECTED RESULT:** 
Must show an error- password didn't match
##### **ACTUAL RESULT:**
 show an error- password didn't match.



### *Postive Test Scenarios**

* **Test 1.9:-** Enter valid input in the fields like:-  unique username,email, Strong password must conatin (atlest 1 lower & upper case, digit, special character and longer than 7 characters) 

<img src = "https://user-images.githubusercontent.com/42871956/93995063-2a0f3d00-fdae-11ea-9d3c-fd407935d7b3.gif" width="315" height="620">

##### **EXPECTED RESULT:** 
Successfull regeatraion/signUp
##### **ACTUAL RESULT:**
 Got successfully register in app (shows successful toast)
 

### Test 2 - Email Confirmation

After getting registered the confirmation mail is sent to the registered email ID of the user to confirm and then the link will redirect to the confirmation page in a browser 

<img src = "https://user-images.githubusercontent.com/42871956/93995284-70649c00-fdae-11ea-86c8-2f5b2cd0e780.jpg" width="315" height="620"><img src = "https://user-images.githubusercontent.com/42871956/93995303-7490b980-fdae-11ea-865d-014c93d91f48.jpg" width="300" height="620">

##### **EXPECTED RESULT:** 
Displaying the email confirmation
##### **ACTUAL RESULT:**
Displaying Email confirmation but not in proper format



### Test 3 - Login in app

### *Negative Test Scenarios**

* **Test 3.1:-** Try to login without email verification

<img src="https://user-images.githubusercontent.com/42871956/93995595-d5b88d00-fdae-11ea-8567-b2032313edaf.jpg" width="300" height="620"/>

##### **EXPECTED RESULT:**
Must show an error- please verify the email before login
 ##### **ACTUAL RESULT:**
 please verify the email.</b> 


**Test 3.2:-** Enter wrong username/email

<img src="https://user-images.githubusercontent.com/42871956/93996347-c2f28800-fdaf-11ea-9e4f-2c5b6f864b6b.jpg" width="300" height="620"/>

##### **EXPECTED RESULT:** 
User doesn't exists.

##### **ACTUAL RESULT:**
Something went wrong

**Test 3.3:-** Enter wrong Password

<img src="https://user-images.githubusercontent.com/42871956/93995797-157f7480-fdaf-11ea-9842-28e5bb36fa85.jpg" width="300" height="620"/>

##### **EXPECTED RESULT:** 
Worng Password.

##### **ACTUAL RESULT:**
Something went wrong

### *Postive Test Scenarios**

* **Test 3.4:-** After email verification, Enter the right username/email and password in order to log in.

<img src = "https://user-images.githubusercontent.com/42871956/93996610-195fc680-fdb0-11ea-9190-f964882ae309.gif" width="315" height="620">

##### **EXPECTED RESULT:** 
successful log in
##### **ACTUAL RESULT:**
Log in Successful


### Test 4 - Find Memeber and Send the realtion request

The user searches for another user and views the profile
<img src="https://user-images.githubusercontent.com/42871956/93996842-5cba3500-fdb0-11ea-8c4c-864b6f09113d.gif" width="300"/>

### *Positive test Scenario**
* **Test 4.1:-** Send Request

<img src="https://user-images.githubusercontent.com/42871956/93996945-7a879a00-fdb0-11ea-9e4e-3a16707ad3cd.gif" width="300"/>

##### **EXPECTED RESULT:** 
Mentorship request was sent succesfully 
##### **ACTUAL RESULT:**
 Mentorship request was sent succesfully


###  *Negative test Scenario**

* **Test 4.2:-** The other person is not a mentor

<img src="https://user-images.githubusercontent.com/42871956/93997119-a9057500-fdb0-11ea-94bb-6d93ce21e10f.gif" width="300"/>

##### **EXPECTED RESULT:** 
Mentor User is not available to mentor 
##### **ACTUAL RESULT:**
 Mentor User is not available to mentor


**Test 4.3:-** Send request without selecting your role
<img src="https://user-images.githubusercontent.com/42871956/93997393-faadff80-fdb0-11ea-9ad5-a973fa2beefa.jpg" width="300"/>

##### **EXPECTED RESULT:** 
please select your role for mentorship relation 
##### **ACTUAL RESULT:**
 please select your role for mentorship relation


**Test 4.4:** sending request to the same user again at same day*

<img src="https://user-images.githubusercontent.com/42871956/93997502-16b1a100-fdb1-11ea-9667-0bb5e6f75713.jpg" width="300"/>

##### **EXPECTED RESULT:** 
"mentorship relation request already sent"


##### **ACTUAL RESULT:** 
"mentorship relation request already sent"

* **Test 4.5:-** sending request for short period of time*

<img src="https://user-images.githubusercontent.com/42871956/93997672-48c30300-fdb1-11ea-9bc1-f46818147a2e.jpg" width="300"/>

##### **EXPECTED RESULT:** 
"mentorship relation minimum duration is 4 week"


##### **ACTUAL RESULT:** 
"mentorship relation minimum duration is 4 week"

**Test 4.6:** sending request to a user who is already in relation:*

<img src="https://user-images.githubusercontent.com/42871956/93998230-de5e9280-fdb1-11ea-957c-f0d1ce6e5f22.gif" width="300"/>

##### **EXPECTED RESULT:** 
"mentor user already in relation"


##### **ACTUAL RESULT:**
"mentor user already in relation"

### Test 5 - Viewing request and Reject Request

Viewing pending relation requests

<img src = "https://user-images.githubusercontent.com/42871956/93998541-50cf7280-fdb2-11ea-844d-6d3c56545126.gif" width="300">
<img src = "https://user-images.githubusercontent.com/42871956/93998714-8b390f80-fdb2-11ea-82a9-2ba011de9f37.jpg" width="300">



### *Positive test Scenario**

* **Test 5.1:-** Reject request

<img src="https://user-images.githubusercontent.com/42871956/93998728-8f652d00-fdb2-11ea-931c-bc13d75e9ff5.gif" width="300"/>

##### **EXPECTED RESULT:** 
Mentorship request was rejected succesfully
##### **ACTUAL RESULT:**
 Mentorship request was rejected succesfully

### *Postive test Scenario**
**Test 6 -** After rejecting mentorship realation request
<img src = "https://user-images.githubusercontent.com/42871956/93998855-c20f2580-fdb2-11ea-95f4-af868625bc2c.jpg" width="315" height="620">

##### **EXPECTED RESULT:** 
The Relation should no longer be visible in Mentorship Request
##### **ACTUAL RESULT:**
Relation request is no longer visible in Mentorship Requests
