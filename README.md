# **The Giphy**

**Developed in Kotlin 100%**



* Used **MVVM Architectural Pattern** as the code can be easily reused and binding makes UI updates easier to handle.
* This **Architecture** makes the code more modular.
* **LiveData** - LiveData to update the UI automatically when the data updates.
* Used **LiveData** extensively to communicate between view and viewmodel. Whenever the API call is success it will update the UI automatically according to our design.
* Used **Coroutines** to perform smoother backgroud tasks.
* **Binding Adapters** used for the Imageview to load Gif using **Glide** library and **CircularProgressDrawable** to show loader in place holder.
* Used **Paging3** library from android to perform pagination for smooth infinte scrolling experiance of trending gifs lists.
* Used **Room Database** provided in jetpack components to store and retrive the fav gifs selected by user.
* Have **Unit Test Cases** for the utils class.
* Have **Instrumented Test cases** for **Room Database, DAO and SQL operations.**
