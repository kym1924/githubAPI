

# githubAPI



<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/101504293-64a07300-39b6-11eb-97df-9adbe447478a.gif" width="350" height="600">
    <img src="https://user-images.githubusercontent.com/63637706/101504913-1770d100-39b7-11eb-965c-af7d85835a95.gif" width="350" height="600">
</div>



# 1. View



<div style="text-align : center">
    <img src="https://user-images.githubusercontent.com/63637706/101503995-0e333480-39b6-11eb-9b3c-24806cd16336.png" width="250" height="500">
    <img src="https://user-images.githubusercontent.com/63637706/101503998-0ecbcb00-39b6-11eb-96c5-3dcdd10691f7.png" width="250" height="500">
    <img src="https://user-images.githubusercontent.com/63637706/101503992-0d020780-39b6-11eb-9920-845f80ee52a9.png" width="250" height="500">
</div>





## 2. Dependencies

<img src="https://user-images.githubusercontent.com/63637706/101503827-df1cc300-39b5-11eb-802d-8640e79fdee8.PNG"><img src="https://user-images.githubusercontent.com/63637706/101503822-ddeb9600-39b5-11eb-84e5-77098355de3c.PNG">



  

## 3. Hilt for DI

```kotlin
@InstallIn(ApplicationComponent::class)
@Module
object DataModule {
    @Provides
    @Singleton
    fun provideSearchDao(application : Application) : SearchDao =
        SearchDatabase.getDatabase(application).searchDao()

    @Provides
    @Singleton
    fun provideRequestInterface(): RequestInterface = RetrofitBuilder.service

    @Provides
    @Singleton
    fun provideSearchRepository(searchDataSourceImpl : SearchDataSource) =
        SearchRepository(searchDataSourceImpl)
}
```

```kotlin
@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModuleBinds {
    @Binds
    abstract fun bindSearchDataSource(searchDataSourceImpl : SearchDataSourceImpl) : SearchDataSource
}
```

```kotlin
@HiltAndroidApp
class RetrofitPracticeApplication : Application() {
}
```



## 4. SearchAdapter for Search History

```kotlin
class SearchAdapter<B : ViewDataBinding>(private val searchViewModel : SearchViewModel) : RecyclerView.Adapter<SearchAdapter<B>.VHolder<B>>(){
    var search = emptyList<Search>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder<B>(LayoutInflater.from(parent.context).inflate(R.layout.item_search_history, parent, false))

    override fun getItemCount() = search.size

    override fun onBindViewHolder(holder: VHolder<B>, position: Int) {
        holder.bind(search[position])
    }

    fun setData(newList : List<Search>) {
        val diffUtilCallBack = SearchDiffUtil(search, newList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallBack)
        this.search = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class VHolder<B : ViewDataBinding>(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val binding : B = DataBindingUtil.bind(itemView)!!

        fun bind(search : Search) {
            binding.setVariable(BR.search, search)
            binding.setVariable(BR.searchViewModel, searchViewModel)
        }
    }
}
```



## 5. ResultAdapter for Search Result

```kotlin
class ResultAdapter<B : ViewDataBinding, I>(private val layout : Int) : RecyclerView.Adapter<ResultAdapter<B, I>.VHolder<B, I>>(){
    private var list = emptyList<I>()

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int)
            = VHolder<B, I>(LayoutInflater.from(parent.context).inflate(layout, parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: VHolder<B, I>, position: Int) {
        holder.bind(list[position])
    }

    internal fun setData(list : List<I>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class VHolder<B : ViewDataBinding, I>(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val binding : B = DataBindingUtil.bind(itemView)!!

        fun bind(result : I) {
            binding.setVariable(BR.result, result)
        }
    }
}
```



## 6. SearchDiffUtil

```kotlin
class SearchDiffUtil (private val oldList : List<Search>, private val newList : List<Search>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
    	= oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)
    	= oldList[oldItemPosition].search == newList[newItemPosition].search
}
```



## 7. BindingAdapter

```kotlin
@BindingAdapter("visibleResetButton")
@JvmStatic
fun visibleResetButton(view : View, search : String) {
    view.visibility = if (search.isEmpty()) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("setListItem")
@JvmStatic
fun setListItem(recyclerView : RecyclerView, searchList : List<Search>?) {
    if (recyclerView.adapter != null) with(recyclerView.adapter as SearchAdapter<*>) {
        searchList?.let{ setData(it) } }
}

@BindingAdapter("setRepoItem")
@JvmStatic
fun setRepoItem(recyclerView: RecyclerView, reposList: List<ReposItems>?) {
    if (recyclerView.adapter != null) with(recyclerView.adapter as ResultAdapter<*, ReposItems>) {
        reposList?.let { setData(it)} }
}

@BindingAdapter("setUserItem")
@JvmStatic
fun setUserItem(recyclerView : RecyclerView, usersList : List<UsersItems>?) {
    if (recyclerView.adapter != null) with(recyclerView.adapter as ResultAdapter<*, UsersItems>) {
    	usersList?.let{ setData(it) } }
}
```



## 8. SearchViewModel for DI

```kotlin
class SearchViewModel @ViewModelInject constructor(
    private val searchRepository: SearchRepository
): ViewModel() {
	...
}
```



## 9. Retrofit for GitHubAPI Search

```kotlin
object RetrofitBuilder {
    private const val baseURL = "https://api.github.com/search/"

    private var retrofit = Retrofit.Builder().baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service : RequestInterface = retrofit.create(RequestInterface::class.java)
}
```

```kotlin
interface RequestInterface {
    @GET("users")
    suspend fun requestUsers(
        @Query("q") q : String
    ) : UsersData

    @GET("repositories")
    suspend fun requestRepos(
        @Query("q") q : String
    ) : ReposData
}
```



## 10. Room for Search History

```kotlin
@Database(entities = [Search::class], version = 1, exportSchema = false)
abstract class SearchDatabase : RoomDatabase() {
    companion object {
        @Volatile private var INSTANCE : SearchDatabase? = null

        fun getDatabase(context : Context) : SearchDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        SearchDatabase::class.java, "searchDb"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
    abstract fun searchDao() : SearchDao
}
```

```kotlin
@Dao
interface SearchDao {
    @Query("SELECT * from searchDb ORDER BY createdAt DESC LIMIT 6")
    fun getSearch() : LiveData<List<Search>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search : Search)

    @Delete
    suspend fun delete(search : Search)
}
```

```kotlin
@Parcelize
@Entity(tableName = "searchDb")
data class Search (
    @PrimaryKey
    val search : String,
    val createdAt : String = System.currentTimeMillis().toString()
) : Parcelable
```