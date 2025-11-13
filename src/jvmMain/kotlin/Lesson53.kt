import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.currentLesson
import data.lessonsLogin
import data.lessonsPassword
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ui.horizontalSnapHelper
import ui.LazyColumn

private interface MailApi {
    @FormUrlEncoded                             // Функция удаления указанного сообщения в почтовом ящике
    @POST("delete")                          // метод POST, кодирование как в веб-форме
    suspend fun delete(
        @Field("id") id: Int,                   // id удаляемого сообщения
        @Field("login") login: String?,         // логин и пароль пользователя
        @Field("password") password: String?
    ): Result                                   // результат действия: success>0 если сообщение успешно удалено.

    data class Result(val success: Int, val error: String?)     // сюда помещается результат.

    // Остальное в интерфейсе - как в предыдущем уроке, попробуйте (по возможности) восстановить всё по памяти.

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LessonDelete(avatars: List<String>) {

}

/*
    Нам снова потребуется авторизация на сервере. Если что-то не так, пройдите авторизацию ещё раз.
    Получите список сообщений и отобразите его карточками в ленивом списке, как в предыдущем задании, для упрощения
    можно не реализовывать в этот раз функцию просмотра полного сообщения.

    Отличия будут следующими: мы сделаем каждую карточку немного прокручивающейся влево для отображения кнопки удаления.

    В продюсере для messages преобразуйте список сообщений в редактируемый: .toMutableStateList(), начальное значение
    в этом продюсере, соответственно, пустой редактируемый список - mutableStateListOf().
    В функции items ленивого столбца добавьте второй параметр - ключ: key = { it.id }. Это рекомендуется делать для
    всех списков, где происходят изменения.

    Внутри блока items объявите переменную scroll = rememberScrollState(), и добавьте к Row внутри карточки два
    модификатора: .horizontalScroll(scroll).horizontalSnapHelper(scroll) (последний не является стандартным для Compose
    и используется только в этом проекте). Самой карточке придайте модификатор анимации для плавного удаления.

    Аватарке добавьте модификатор fillParentMaxWidth(0.18f) (то есть, 18% от ширины ленивого столбца), столбцу с
    заголовками сообщения - аналогично, но 80% ширины. В сумме, 98%, почти вся ширина.
    Правее в этом же ряду добавьте вертикальную черту - это будет Box шириной 1 и высотой 48, светло-серого цвета.
    Правее - кнопка-значок для удаления сообщения. Таким образом, пользователь должен прокрутить карточку влево, чтобы
    увидеть эту кнопку.

    Кнопка должна запустить фоновый поток scope.launch {...} (переменную scope объявите в начале программы).
    В этом потоке вызовите функцию delete нашего API, передав туда id текущего сообщения, а также логин и пароль
    пользователя. Проверьте результат, который возвращает функция, если success > 0, то есть, сообщение успешно удалено
    на сервере, то удалите его и из текущего списка: messages -= it

    Если хотите добавить анимацию остальных элементов при удалении, добавьте карточке модификатор animateItem()
    и, при необходимости, согласитесь с компилятором пометить функцию нашего урока, как использующую экспериментальные
    методы.
 */