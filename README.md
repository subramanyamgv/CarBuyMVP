# CarBuyMVP ![](http://i.imgur.com/AEBXz2F.png)
Car buy application using MVP, Retrofit2, RxJava2, Dagger2

## Screenshots
![](http://i.imgur.com/XbplWwI.png)

## Model-View-Presenter ([mvp](https://github.com/subramanyamgv/CarBuyMVP/tree/master/mvp))
Java module with no Android dependencies. It contains following packages:

- `.mvp` - a package that contains all classes directly related to Model-View-Presenter architecture. 
	- `.mvp.model` - Model classes
	- `.mvp.presenter` - Presenters. They responds to UI actions,  uses usecases to operate modify model objects and control View elements depending on use case results. It subscribes to observable returned by usecase `execute()` method.
	- `.mvp.view` - View interfaces that should be implemented by Android Activities, Fragments, or any other UI component.
- `.domain` - contains use cases that are used to operate on Model objects. Each use case implements `Usecase` interface that contains `execute()` that returns rxJava `Observable` object. 
- `.repository` - contains `Repository` interface used by use cases to operate on model. Implementation for this interface is provided by Android module using Retrofit.

## Android gradle module.

- `.activity` - Android activities, they can be treated as MVP Views by implementing corresponding `View` interfaces from `mvp` gradle module.
- `.fragment` - Android fragments. Like activities, they can be treated as Views.
- `.adapter` - adapters for RecyclerViews
- `.repository/network` - implementation of mvp `Repository` interface with Retrofit 2 library.
- `.injector` - Dagger 2 related classes
	- `.scope` - Two custom scopes are defined: `PerApplication` and `PerActivity`.
	- `.module` - `ApplicationModule` and `NetworkModule` are `PerApplication` defined modules. `NetworkModule` provides implementation for `Repository` interface. The `CarSelectionModule` module have `PerActivity` scope and provides injections for presenters and use cases defined in `mvp` gradle module.
	- `.component` - contains `dagger` components. `ApplicationComponent` provides `PerApplication` scoped dependencies. Rest of components provide `PerActivity` scoped dependencies.
- `.entity` - entities to pass data for RecyclerViews adapters

## Third Party Libraries
* [Butterknife](https://github.com/JakeWharton/butterknife)
* [Dagger2](https://github.com/square/dagger)
* [Retrofit2](https://github.com/square/retrofit)
* [RxJava2](https://github.com/ReactiveX/RxJava)
* [BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)

## License
Provided under [MIT License](LICENSE)
