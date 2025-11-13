import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import lessons.User
import ui.AnimatedVisibility
import ui.ElevatedCard
import ui.LazyColumn

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LessonUserList(allUsers: List<User>) {

}

/*
    Весь дизайн можно взять из задания №22, только требуются изменения в логике построения списка:
    allUsers - это исходный, полный, список пользователей.

    Надо создать переменную users, где будут храниться только пользователи, которых ещё не удалили:
    val users = remember { allUsers.toMutableStateList() }
    обратите внимание, что список преобразован в мутируемый (изменяемый) список состояний MutableStateList.

    Также создайте переменную, которая будет определять какой из пользователей в данный момент редактируется:
    var edit: User? by remember { mutableStateOf(null) }
    начальное состояние null, то есть, никакой, все карточки в обычном состоянии.

    Отображайте пользователей с помощью LazyColumn и его items.
    Перед items добавьте одиночный элемент item {...}, в который поместите текст подсказки (отступы на 6).

    Горизонтальному ряду внутри карточки добавьте модификатор: combinedClickable(onLongClick = { ... }) { ... }
        первый блок - действие по долгому нажатию элемента. Присвойте здесь переменной edit текущий элемент списка,
        второй блок - действие по обычному нажатию элемента. Присвойте здесь переменной edit значение null
    Долгое нажатие будет запускать редактирование, обычное - отменять.

    Измените структуру: внутри карточки - Box, а уже внутри него - всё остальное, что у вас есть.
    Внутри этого Box, но снаружи горизонтального ряда, на одном с ним уровне добавьте объект AnimatedVisibility(...){}
        первый параметр - "edit == user", то есть объект будет виден только для редактируемого в данный момент элемента,
        второй параметр - модификатор выравнивания (align) по правому нижнему углу карточки (Alignment.BottomEnd),
        последний параметр {} - блок, содержащий кнопку-значок "корзина",
            размер значка 32,
            цвет - MaterialTheme.colors.error
        Кнопка-значок должна делать два действия:
            удалить текущего пользователя из списка: users.remove(user) или просто: users -= user
            обнулить переменную edit: edit = null

    В целом готово, но хотелось бы, чтобы пользователи "уходили красиво", то есть, с плавной анимацией. Для этого нужно
    сделать две вещи. Во-первых, дать каждому элементу списка уникальный ключ. Кстати, это желательно делать всегда,
    когда мы работаем с редактируемыми списками. Система будет всегда точно знать, какой элемент отображается в данном
    месте экрана, даже если он изменился, или поменялся порядок элементов. Но нужно точно убедиться, что ключ уникален,
    то есть, не повторяется у разных элементов списка. Обычно это некий порядковый id элемента из базы данных, но в
    нашем примере id в классе User отсутствует. Фамилии и имена могут повторяться и не годятся в качестве ключа. Зато
    у нас есть поле photo - имя файла с фотографией пользователя, и это фото точно у всех разное.

    Итак, найдите функцию items(users) { ... }, которая перечисляет пользователей, и добавьте в неё ключ user'а:
    items(users, key = { it.photo }) { ... }
    Работа с редактируемым списком после этого становится более стабильной, и, в качестве бонуса, мы теперь можем
    добавить анимацию. Карточке, которая находится непосредственно внутри items, придайте дополнительный модификатор
    animateItem() без параметров. Всё, анимация готова.
*/