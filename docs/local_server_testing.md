Steps To Test The App Using A Local Server



Step 1- Follow the steps to set up the mentorship backend server locally, mentioned in https://github.com/anitab-org/mentorship-backend . After running run.py successfully, note the url of localhost.

https://user-images.githubusercontent.com/46787232/94749711-182b2c80-03a2-11eb-98ae-c07c58eca88d.JPG


Step 2- Open the app in android studio and open the file named 'BaseUrl.kt' 


Step 3- Replace "https://mentorship-backend-temp.herokuapp.com"  with the url noted in step 1.

https://user-images.githubusercontent.com/46787232/94749719-1d887700-03a2-11eb-8eb3-a00889eb1e74.JPG


After this run the app in the emulator. The url replaced above fetches the data from the local server. 
