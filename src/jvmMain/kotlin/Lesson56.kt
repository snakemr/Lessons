import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import lessons.User
import ui.LazyVerticalGrid

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LessonFriends(users: List<User>) {

}

/*
    В этом разделе будут задачи повышенной сложности на построение плавных пользовательских интерфейсов.

    users - список всех пользователей. Создайте также список друзей - он по умолчанию будет пустым:
        val friends = remember { mutableStateListOf<User>() }
    Ещё у нас будет список "сотрудников" - пользователей, не являющихся друзьями. Получить его можно вычитанием:
        val coworkers by remember { derivedStateOf { users - friends } }
    (оба списка будут автоматически обновляться при таких действиях: friends += user и friends -= user)

    Создайте ленивую вертикальную сетку с фиксированным количеством столбцов GridCells.Fixed(3). Внутри будут два
    блока items - для списка друзей и списка сотрудников. Вторым параметром обоих блоков укажите ключ - здесь он нужен,
    так как оба списка будут меняться. Так как фото у каждого пользователя уникально, ключом будет фото, например:
        items(friends, { it.photo }) { friend -> ... }

    Внутри обоих блоков - карточка (отступы на 5). Добавьте также модификатор анимации animateItem.
    Внутри карточки - столбец с горизонтальным выравниванием по центру. Внутри - фото пользователя
    (отступы 5 и обрезка с закруглением на 10) и имя пользователя серого цвета (нижний отступ на 5).

    Столбцу придайте модификатор щелчка, который будет добавлять пользователя в друзья, либо удалять его - в зависимости
    от того, в каком из items мы находимся.

    Проверьте работу программы. Карточки друзей должны менять цвет и плавно переноситься в начало списка и обратно.

    Теперь надо добавить разделители групп. Перед каждым items добавьте по одиночному элементу item, который будет
    появляться, только если соответствующий список не пуст, например:
        if (friends.isNotEmpty()) item("friends", { GridItemSpan(3) }) { ... }

    Ключ второго item будет, соответственно, "coworkers". GridItemSpan(3) означает, что элемент будет занимать три
    столбца, то есть, всю ширину нашей сетки. Внутри item - карточка с теми же параметрами, что у пользователей.
    Не забудьте придать ей модификатор анимации. Внутри карточек - горизонтальный ряд с отступами на 5,
    горизонтальным распределением "пропуски внутри" и вертикальным центрированием. В ряду разместите два значка и текст.

    Осталось сделать функцию сворачивания группы при нажатии на заголовок. Для этого создайте две remember-переменные
    логического типа: showFriends и showCoworkers, значение по умолчанию - истина. Затем объявите две переменные типа:
        val friendMaxHeight by animateDpAsState(if (showFriends) 150.dp else 0.dp)
    Это автоматически анимированное значение, которое зависит от логической переменной в скобках. Причём, при изменении
    этой переменной, значение будет в течении доли секунды плавно увеличиваться от 0 до 150 единиц, либо обратно.

    В обоих блоках items добавьте карточке пользователя первым модификатором .heightIn(max = friendMaxHeight)
    (и coworkerMaxHeight соответственно). Теперь размер всех карточек будет зависеть от двух логических переменных,
    причём будет меняться плавно. Осталось сделать переключатель. Горизонтальному ряду обоих заголовков добавьте первым
    модификатором clickable, который будет менять значение соответствующей переменной на противоположное.
*/