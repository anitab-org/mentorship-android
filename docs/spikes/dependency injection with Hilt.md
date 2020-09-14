# **DEPENDENCY INJECTION WITH HILT**
### Dependency injection
[Dependency injection](https://developer.android.com/training/dependency-injection) (DI) is a technique widely used in programming and well suited to Android development. By following the principles of DI, you lay the groundwork for good app architecture.

Implementing dependency injection provides you with the following advantages:

- Reusability of code
- Ease of refactoring
- Ease of testing



**[Dagger](https://dagger.dev/)** is a popular dependency injection library for Java, Kotlin, and Android that is maintained by Google. Dagger facilitates using DI in the app by creating and managing the graph of dependencies . It provides fully static and compile-time dependencies addressing many of the development and performance issues of reflection-based solutions such as Guice.

### **HILT**


Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in a project. Doing manual dependency injection requires you to construct every class and its dependencies by hand, and to use containers to reuse and manage dependencies.

Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.

#### Why use Hilt?(ADVANTAGES)

- Reduced boilerplate
- Decoupled build dependencies
- Simplified configuration
- Improved testing
- Standardized components

**Reduced boilerplate**

>The goal of Hilt is to enable users to focus on the Dagger binding definitions and usages without needing to worry about the rest of the Dagger setup. This means hiding things like component definitions with module and interface lists, code to create and hold on to components at the right points in the lifecycle, interfaces and casts to get the parent component, etc.
>Some of the simplicity also comes from Hilt using monolithic components (i.e. using a single component for all activities, a single component for all fragments, etc). Hilt tries to encourage an essentially global binding namespace so that it is easy to know what binding definition is being used without having to trace back which activity or fragment you were injected from. 

**Decoupled build dependencies**
>A naive usage of Dagger may introduce build problems if code references the Dagger component directly. These problems occur because the Dagger component has references to all of the modules installed. This can lead to bloated dependencies that slow down builds. The natural way to solve this involves interfaces and unsafe casts. This is a tradeoff though because these can introduce runtime errors. For example, introducing a new injector interface avoids directly depending on the component but then forgetting to make your component extend the injector interface results in a cast exception.
By code generating the interfaces, unsafe casts, and module/interface lists under the hood, Hilt makes these runtime unsafe casts safe due to the guarantees of the code generation and module/entry point discovery.

**Configuration**
>Apps often have different builds configurations like a production or development build that has different features. These different sets of features often mean a different set of Dagger modules. In a normal Dagger build, a different set of modules requires having a separate component tree (a separate component for every scope) with usually lots of portions repeated. Because Hilt installs modules via build dependencies and code generates the components, creating a different flavor of your build is as simple as compiling with an added or removed dependency.

**Testing**
>Testing with Dagger can be hard, due to the configuration issue mentioned above. Hilt similarly makes changing out test modules and bindings easier due to the code generation of components. Hilt has specific test utilities built-in to make managing modules and providing test bindings easier so that tests can use Dagger. Using Dagger in tests helps reduce boilerplate in tests and makes tests more robust by instantiating code in the same way it is instantiated in production.

**Standardization**
>Hilt standardizes the component hierarchy. This means that libraries that integrate with Hilt can easily add or consume bindings from these known components. This allows for more complex libraries to be built that can integrate cleanly and more simply into any Hilt app.


### INCORPORATING DEPENDENCY INJECTION WITH HILT

__Adding dependencies__
First, add the hilt-android-gradle-plugin plugin to project's root build.gradle file:
 
    buildscript {
    ...
    dependencies {
        ...
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.28-alpha'
    }}
Then, apply the Gradle plugin and add these dependencies in app/build.gradle file:
   
    ...
    apply plugin: 'kotlin-kapt'
    apply plugin: 'dagger.hilt.android.plugin'
    android {
    ...
    }

    dependencies {
    implementation "com.google.dagger:hilt-android:2.28-alpha"
    kapt "com.google.dagger:hilt-android-compiler:2.28-alpha"
    }
__Hilt application class__

All apps that use Hilt must contain an Application class that is annotated with `@HiltAndroidApp`

`@HiltAndroidApp` triggers Hilt's code generation, including a base class for the application that serves as the application-level dependency container.

     @HiltAndroidApp
     class ExampleApplication : Application() { ... }
This generated Hilt component is attached to the Application object's lifecycle and provides dependencies to it. Additionally, it is the parent component of the app, which means that other components can access the dependencies that it provides.

__Inject dependencies into Android classes__

Once Hilt is set up in the Application class and an application-level component is available, Hilt can provide dependencies to other Android classes that have the `@AndroidEntryPoint` annotation:

    @AndroidEntryPoint
    class ExampleActivity : AppCompatActivity() { ... }
Hilt currently supports the following Android classes:

- Application (by using @HiltAndroidApp)
- Activity
- Fragment
- View
- Service
- BroadcastReceiver

If you annotate an Android class with `@AndroidEntryPoint`, then you also must annotate Android classes that depend on it.

__Define Hilt bindings__

To perform field injection, Hilt needs to know how to provide instances of the necessary dependencies from the corresponding component. A binding contains the information necessary to provide instances of a type as a dependency.

One way to provide binding information to Hilt is constructor injection. Use the `@Inject` annotation on the constructor of a class to tell Hilt how to provide instances of that class:
   
    class AnalyticsAdapter @Inject constructor(
    private val service: AnalyticsService
    ) { ... }
    
The parameters of an annotated constructor of a class are the dependencies of that class.

__Hilt modules__
Sometimes a type cannot be constructor-injected. This can happen for multiple reasons. For example, you cannot constructor-inject an interface. You also cannot constructor-inject a type that you do not own, such as a class from an external library. In these cases, you can provide Hilt with binding information by using Hilt modules.
A Hilt module is a class that is annotated with @Module. Like a Dagger module, it informs Hilt how to provide instances of certain types. Unlike Dagger modules, you must annotate Hilt modules with @InstallIn to tell Hilt which Android class each module will be used or installed in.

__Predefined qualifers in Hilt__
Hilt provides some predefined qualifiers. For example, as you might need the Context class from either the application or the activity, Hilt provides the @ApplicationContext and @ActivityContext qualifiers.


#### __detailed documentaion : [using hilt](https://codelabs.developers.google.com/codelabs/android-hilt/#1)__


### BEST PRACTICES

- First, out of all Hilt’s Components, the “core” components are ApplicationComponent, ActivityComponent and ServiceComponent.

- Try to avoid use of nested fragments where there is a need of FragmentComponent.

- Don’t use ViewComponent and ViewWithFragmentComponent at all, unless you migrate messed-up legacy project to Hilt.

- using @Inject annotated constructors is also a valid implementation choice. However, for scoped dependencies, It is recommended using this explicit approach exclusively. There is real maintenance benefit in being able to open a couple of Modules and get a full picture of scoped dependencies in the app.

- It is recommended to use @Reusable annotation. 

- There is also a section titled “extensions” in Hilt docs which seem to suggest that Google envisions third-party libraries that will depend on Hilt and integrate into apps. Don’t make the libs depend on any DI framework.

- most importantly, Dependency Injection is a complex architectural pattern that has tremendous effect on the long-term maintainability of your project. DI frameworks provide a template and some conventions, but the quality of your DI doesn’t automatically increase if you use a framework.
 
 ### DRAWBACKS
 
- Dagger is notoriously bad in context of build times, especially for Kotlin and Kapt.Dagger Hilt adds additional layer of annotation processing and code generation on top of standard Dagger. If this addition will increase the build times in a noticeable way, integration of Dagger Hilt would be very questionable move.
-  Hilt only supports activities that extend ComponentActivity, such as AppCompatActivity.
- Hilt only supports fragments that extend androidx.Fragment.
- Hilt does not support retained fragments. (setRetainInstance = true)
-  If you annotate a service/fragment/broadcast receiver/etc as @AndroidEntryPoint any other component(the listed ones) using them would also have to declare themselves as @AndroidEntryPoint. In simple words, If a fragment is marked as @AndroidEntryPoint, the activity using it should also be annotated.

sources:

- https://dagger.dev/hilt/
- https://developer.android.com/training/dependency-injection/hilt-android
- https://www.techyourchance.com/dagger-hilt/
- https://medium.com/@kiitvishal89/i-was-extremely-happy-with-dagger-but-was-utterly-disappointed-in-dagger-android-297b3d056af
- https://medium.com/androiddevelopers/dependency-injection-on-android-with-hilt-67b6031e62d

 
 
 
 







