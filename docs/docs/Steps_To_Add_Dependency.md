# :rocket: Overview
1. The Gradle build system in Android Studio helps us to include all the external dependencies in our project.

2. Let's say if we want to display an image in our app, we can add a library called `Picasso` or `Glide` Library.
 
3. There are some libraries that are default in every project that is mostly predefined by android when we create our project but there are some of the libraries we want to include in our project but they are not available in android studio.

4. So in that case we add external libraries to execute the required task. 

# :rocket: Steps to add dependency in Android Studio

- Open Android Studio.

- Click on Open an existing Android Studio Project.

![image](https://user-images.githubusercontent.com/76740999/178974122-f4c1c6c6-9b56-4ae1-8cf5-62ac6d442cd2.png)

- Browse to the directory where you cloned the project.

- Select the `mentorship-android` directory and click open.

- Android Studio will download the libraries required to run the project and open it in a new window through build gradle process.

- Once the gradle process is done click on the `Project` view on left side and change it to `Android` view if not done already. 

![123](https://user-images.githubusercontent.com/76740999/178977948-906642ec-a63b-424b-baf9-f37bb0abb13f.png)
 
- Once the project is opened in `Android` view head over to `Gradle Scripts > build.gradle.kts (Module: mentorship-android.app)`. All the external dependencies used in the project are included in this file.
 
![6](https://user-images.githubusercontent.com/76740999/178975230-30076def-9099-4ae4-8f7d-5c438c6df704.png)

- Let's say if we need to add `implementation(Dependencies.retrofit)` dependency to our project, we will paste the dependency under the `dependencies` section and `click` on the `Sync now` button at the top right so that it can fetch the library files and add it into our project. Once it's done we can add codes related to the library.

![7](https://user-images.githubusercontent.com/76740999/178977117-dd9c7161-1130-40a8-ab61-6fe4370f531e.png)

![8](https://user-images.githubusercontent.com/76740999/178977151-4e52133b-f1e1-4c50-972f-ad31547c410f.png)

- You can follow the same process to add any library you want in your project.

  Happy Coding ! 🎉
