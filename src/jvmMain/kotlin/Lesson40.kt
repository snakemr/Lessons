import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import ui.LazyColumn

private interface RegionApi {
    // класс для получения данных с сервера: перечень регионов
    data class Region(val name_with_type: String)

    // Самостоятельно опишите функцию regions(), производящую GET-запрос по адресу https://mad.lrmk.ru/kladr/region
    // и возвращающую список регионов, используя класс Region

    companion object {
        fun getApi() = "попробуйте по памяти построить интерфейс RegionApi для API сервера https://mad.lrmk.ru/kladr/"
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LessonRegions() {

}

/*
    Создайте экземпляр нашего API (помните remember!). Создайте продюсер, который получит у нашего API список регионов:
    val regions by produceState(mutableStateListOf()) {
        value = api.regions().toMutableStateList()
    }
    Примечание: исходный список превращается в MutableStateList, чтобы мы могли вносить в него изменения. Начальное
        значение, пока не пришёл ответ сервера - пустой изменяемый список.

    Создайте "ленивый" столбец для отображения элементов списка regions.
        Используйте горизонтальный ряд с вертикальным центрированием для отображения текста и кнопки-значка.
        Отступы текста - на 10. Вес текста - 1, чтобы прижать кнопку вправо.
        Размер кнопки - 32. Цвет значка - MaterialTheme.colorScheme.error

    Кнопка должна удалять текущий элемент списка: regions.remove(it) или просто regions -= it

    Важно: в редактируемом списке очень желательно определить ключ, по которому Compose будет отличать строки списка.
        Нашим ключом будет само название региона. Тогда функцию items нашего ленивого столбца нужно переделать так:
    items(regions, key = { it.name_with_type }) { ... }

    В качестве приятного бонуса добавьте модификатор анимации animateItem() элементу внутри items, чтобы
        обеспечить удаление строк с плавной анимацией
*/