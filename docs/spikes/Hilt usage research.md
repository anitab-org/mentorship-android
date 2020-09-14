1) Advantages / Solution

	MentorshipApplication class should be annotated with @HiltAndroidApp so that hilt can be used in the app.

	Hilt can be used to reduce the boiler plate code in the viewmodels and the api requests.

	a) An ApiService Class (Interface) can be made with all the required api requests, i.e, @GET, @PUT, @POST, etc. Then to handel the networking related stuff, all we will need is to pass the class as a parameter to the repository and implement the required functions with making use of coroutines (as they would be suspend functions).
	Then an object ApiModule should be created which should be annotated using @Module and @InstallIn(ApplicationComponent::class) and will include provide functions with annotation @Provides and the scope annotation eg(@Singleton) for all the work done in the ApiManager Class, i.e, for setting up the connection with the API using retrofit, and for providing the ApiService Class, thus reducing the code required to make the classes of the datamanager package in the app.
	Moreover this will combine the code of all the classes in the services package into the ApiService Class (Interface).
	The Repository class will have constructor injection of the ApiService class using @Inject annotation before the constructor() in which will include ApiService class object as a parameter of that constructor.
	Another module RepositoryModule should be created which will be annotated with @Module and @InstallIn(ApplicationComponent::class) and will include a function to provide the Repository class.

	b) @ViewModelInject annotation can be used with the viewmodels which will also allow the constructor injection of the repository class inorder to reduce the boiler plate code.There will be no need for creating any ViewModelFactory class. All the fragments which the use the viewmodels along with MainActivity should be annotated with @AndroidEntryPoint, and the viewmodel can be used in them by simply adding "by viewmodels<ViewModel Class name>()" in front of the variable declaration of viewmodel, and the viewmodel will be able to access all the functions made in the classes which were passed as parameters in the ViewModel class constuctor, like repository.

	c) Hilt can also be used in PreferenceManager class to provide shared preferences to the application. Another object AppModule can be created which will contain provide functions other than the networking related ones (if an ApiModule has already been created).The provide function of sharedPreference should include a parameter context of type Context annotated with @ApplicationContext so as to provide context while creating sharedPreferences

	d) If the app uses room database in future then hilt will help reducing it's boiler plate code too. We can create another module for handelling this which will provide all the functions in the DAO and will provide the database as well including the annotations as described above.


2) Best Practices:
	
	a) When scoping a binding within an @InstallIn module, the scope on the binding must match the scope of the component. For example, a binding within an @InstallIn(ActivityComponent.class) module can only be scoped with @ActivityScoped.

	b) Calling setRetainInstance(true) in a Fragment’s onCreate method keeps a fragment instance across configuration changes (instead of destroying and recreating it).
	A Hilt fragment should never be retained because it holds a reference to the component (responsible for injection), and that component holds references to the previous Activity instance. In addition, scoped bindings and providers that are injected into the fragment can also cause memory leaks if a Hilt fragment is retained.

	c) If a module does not have an @InstallIn annotation, the module will not be part of the component and may result in compilation errors.
	Therefore, it should be specified which Hilt Component to install the module in by passing in the appropriate Component type(s) to the @InstallIn annotation. For example, to install a module so that anything in the application can use it, use SingletonComponent

	d) Network / API and the CRUD operations in Room related functions should be called asynchronously, i.e, by making them suspend functions. So whenever we will call those functions, we will do so in a coroutine.


3) Disadvantages / Drawback:
	
	i) Hilt has predefined components for Android that are managed for us. However, there may be situations where the standard Hilt components do not match the object lifetimes or needs of a particular feature. In these cases, we may want a custom component.
	
	Adding a custom component has the following drawbacks:
		a) Each component/scope adds cognitive overhead.
		b) They can complicate the graph with combinatorics (e.g. if the component is a child of the ViewComponent conceptually, two components likely need to be added for ViewComponent and ViewWithFragmentComponent).
		c) Components can have only one parent. The component hierarchy can’t form a diamond. Creating more components increases the likelihood of getting into a situation where a diamond dependency is needed. Unfortunately, there is no good solution to this diamond problem and it can be difficult to predict and avoid.
		d) Custom components work against standardization. The more custom components are used, the harder it is for shared libraries.
		e) Components must be a direct or indirect child of the SingletonComponent.
		f) Components may not be inserted between any of the standard components. For example, a component cannot be added between the ActivityComponent and the FragmentComponent.