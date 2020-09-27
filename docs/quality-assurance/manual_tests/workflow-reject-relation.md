# Document Workflow: To test reject mentorship relation

### Table of contents


<!--ts-->
   1. [Registration/Sign-Up](#registration)
   2. [Login](#login)
   3. [Finding and viewing the profile](#finding-and-viewing-the-profile)
   4. [Sending request](#sending-request)
   5. [Rejecting request](#rejecting-request)
<!--te-->

##  Registration
   #### Negative Test Cases
* Test 1.1 : Leaving the fields empty

![Capture](https://user-images.githubusercontent.com/31548778/94349055-b8a9e580-005e-11eb-831a-7840a2b5f062.PNG)

 Expected Result : Raise an error that all fields are mandatory.
 
 Actual Result : The actual result meets the expected result.Raised an error that mentions all fields are mandatory.
 
 * Test 1.2 : Wrong email format

![wrong_email](https://user-images.githubusercontent.com/31548778/94367057-a466f680-00f9-11eb-9421-6b7ecbf6cef3.PNG)

Expected Result : Raise an error that the format is invalid.

Actual Result :The actual result meets the expected result.Raised an error that the format is invalid.

* Test 1.3 : Entering a weak password

![weak_password](https://user-images.githubusercontent.com/31548778/94367164-7e8e2180-00fa-11eb-8fa8-b3dc544eda24.jpg)

Expected Result : Raise a warning that the password should be a mix of alphanumeric and uppercase letter.

Actual Result : The actual result meets the expected result.Raised a warning that the password should be a mix of alphanumeric and uppercase letter.

   #### Positive Test Cases

* Test 1.1 : Correctly entering the details

![correct_details](https://user-images.githubusercontent.com/31548778/94367390-0aed1400-00fc-11eb-8cbf-8b00c9c610eb.PNG)

![email](https://user-images.githubusercontent.com/31548778/94367476-ad0cfc00-00fc-11eb-9e93-fa49f36b9bd2.PNG)

![success_email](https://user-images.githubusercontent.com/31548778/94367404-1dffe400-00fc-11eb-8fc5-9e24ac4d0553.PNG)

Expected Result : User account is created and the confirmation email is sent on the registered email address to which user has to click the link for confirming.

Actual Result : Successful creation of user account and the email is sent on the registered email address which on confirming returns a JSON message that the user is registered.

##  Login
   #### Negative Test Cases
   
* Test 1.1 : Login before verification of registered email address

![alert](https://user-images.githubusercontent.com/31548778/94368088-fe6aba80-00ff-11eb-90f0-86923135755f.JPG)

Expected Result : Alert the user to verify the email address inititally before login.

Actual Result : The actual result meets the expected result.Alerted the user to verify the email before login.

   #### Positive Test Cases

* Test 1.1 : Login after verification of email address

![logging](https://user-images.githubusercontent.com/31548778/94368331-2b6b9d00-0101-11eb-873b-87eb7b90547e.JPG)

![usert_page](https://user-images.githubusercontent.com/31548778/94369122-8acbac00-0105-11eb-99e7-e0538623856c.JPG)

Expected Result : Redirect the user to his/her profile page.

Actual Result : Successfully redirects the user to his/her profile page.

##  Finding and Viewing the profile 

* Test 1.1 : Viewing Members Page for a registered user

![members](https://user-images.githubusercontent.com/31548778/94370527-2d882880-010e-11eb-985c-97975c19e744.PNG)

Expected Result : A registered user must be able to view the members page.

Actual Result : The actual result meets the expected result.The registered user is be able to view the members page.

##  Sending Request

   #### Negative Test Cases
   
* Test 1.1 : Sending a request to a member who is not available as a mentor

![failed_req](https://user-images.githubusercontent.com/31548778/94370334-05e49080-010d-11eb-9f1b-fcd932b72f6f.PNG)

Expected Result : Should get an error message stating that the person is not available for mentoring.

Actual Result : The actual result meets the expected result.Successfully showed an error message stating that the person is not available for mentoring.

   #### Positive Test Cases
   
* Test 1.1 : Sending a request to a member who is available as a mentor

![success_req](https://user-images.githubusercontent.com/31548778/94370765-755b7f80-010f-11eb-993f-c107cb6264b8.PNG)

![success_1](https://user-images.githubusercontent.com/31548778/94370763-71c7f880-010f-11eb-82ea-451e337ffec5.PNG)

Expected Result : Should get a message stating that the request is sent successfully and the changes should be reflected on the user's profile page.

Actual Result : The actual result meets the expected result.Successfully showed a message stating that the request is sent successfully and the changes are be reflected on                   the user's requests page.

##  Rejecting Request

* Test 1.1 : Rejecting the request from a mentee

![relation_ment](https://user-images.githubusercontent.com/31548778/94371224-a38e8e80-0112-11eb-8cf4-f5d09978075c.PNG)

![date_notif](https://user-images.githubusercontent.com/31548778/94371219-9e314400-0112-11eb-9e8d-37fc7f4ac49e.PNG)

![mess_rej](https://user-images.githubusercontent.com/31548778/94371223-a25d6180-0112-11eb-8784-0354fa549bf1.PNG)

![reject](https://user-images.githubusercontent.com/31548778/94371287-f7997300-0112-11eb-808f-2da168a34237.PNG)

Expected Result : After rejecting the request , a message must be shown stating the rejection and must reflect on the Mentor's relation page.

Actual Result : Successfully rejected and showed a message stating the rejection and changes were reflected on the Mentor's relation page.
