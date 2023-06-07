import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import ui.BackHandler
import ui.LazyColumn


private interface GenreApi {

    // Создайте функцию genres, запрашивающую https://mad.lrmk.ru/media/genres и возвращающую список элементов Genre:
    // suspend fun...
    data class Genre (val id: Int, val name: String)    // числовой код и название жанра фильмов

    @GET(".")   // если адрес запроса совпадает с адресом API ("https://mad.lrmk.ru/media/") указывается просто точка
    suspend fun movies(@Query("genre") genre: Int? = null): List<Movie>              // список фильмов указанного жанра
    // genre - числовой код жанра, фильмы которого нужно показать. Нужный код берётся из Genre (id) предыдущего запроса
    // параметр genre может быть пустым, тогда сервер возвращает фильмы любых жанров.

    data class Movie ( // всё как в прошлом уроке, но добавлены коды стран, в которых происходили съёмки:
        val id: Int, val name: String, val release_date: String, val vote_average: Float, val overview: String,
        val production_countries: String
    )

    companion object {
        fun getApi() = Retrofit.Builder()
            .baseUrl("https://mad.lrmk.ru/media/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create<GenreApi>()
    }
}

@Composable
fun LessonGenres(exit: ()->Unit) {

}

/*
    Как обычно, получите экземпляр API и список всех жанров genres при помощи продюсера (отсортируйте их по алфавиту).
    Объявите переменную genre внутреннего класса Genre нашего интерфейса, значение по умолчанию - null (не выбран):
    var genre: GenreApi.Genre? by remember { mutableStateOf(null) }

    Используйте genre в качестве ключа в продюсере для получения списка фильмов с сервера по идентификатору genre.id:
    val movies by produceState(emptyList(), genre) {
        value = api.movies(genre?.id).sortedByDescending { it.vote_average }
    }
    Если genre является null, сервер возвращает фильмы любых жанров. Фильмы отсортированы по зрительскому рейтингу ↓.

    Объявите переменные scaffoldState = rememberScaffoldState(), scope = rememberCoroutineScope() и
    state = rememberLazyListState() - да, мы будем в этом задании использовать Scaffold. Создайте его ниже.

    Заполните атрибуты Scaffold - scaffoldState, drawerContent, topBar.
    В drawerContent создайте "ленивый" столбец жанров, отступы названий на 10. До отступа задайте модификатор clickable,
    в котором нам надо присвоить идентификатор жанра переменной genre и закрыть боковое меню:
        scope.launch {
            genre = it
            scaffoldState.drawerState.close()
        }
    Управление боковым меню должно происходить в фоновом потоке, поэтому наш блок запускается в отдельном scope.

    В topBar создайте элемент TopAppBar{} с двумя IconButton и одним текстом. Текст должен отображать надпись "Жанр: ",
    сложенную со строкой genre?.name - названием жанра (знак ? нужен, так как genre может быть null).
    Левая кнопка открывает боковое меню - используйте scope и функцию close() объекта scaffoldState.drawerState.
    Правая кнопка закрывает приложение - используйте функцию из входного параметра нашего урока.

    Основной контент Scaffold должен содержать "ленивый" столбец фильмов movies. Каждый элемент на карточке (отступы
    на 5, возвышение и закругление на 10), внутри карточки - столбец с отступами на 10. Внутри столбца:
        название,
        центрированный горизонтальный ряд, вертикальные отступы на 5, содержимое почти как в прошлом уроке,
        разделитель,
        описание фильма, верхний отступ на 5, тёмно-серый шрифт размера 12, выравнивание по ширине.
 */