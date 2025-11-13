import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import ui.ElevatedButton // не удалять!

private val buttons = listOf(   // это может вам пригодиться:
    "Обычная кнопка", "Кнопка с окантовкой", "Приподнятая кнопка", "Тональная кнопка", "Текстовая кнопка",
    "Кнопка-значок", "Кнопка-значок с окантовкой", "Кнопка-значок с фоном", "Тональная кнопка-значок",
    "Вспомогательный чип", "Приподнятый чип"
)

@Composable
fun LessonButtons() {

}

/*
    Тема - различные виды кнопок.
    Создайте столбец, в круглых скобках укажите выравнивание по центру (horizontalAlignment = Alignment.CenterHorizontally)
    затем, в фигурных - различные типы кнопок.

    Во-первых, создайте текстовую переменную через связку remember / mutableStateOf, как это требует Jetpack Compose:
    var text by remember { mutableStateOf("Нажмите любую кнопку:") }
    и следующей строкой выведите этот Text.

    Первая кнопка - обычная, Button(первый параметр) { последний параметр }
        Первый параметр - что эта кнопка делает, например, ({ text = buttons[0] })
        Последний параметр - что написано/нарисовано на кнопке:
            это значок "домик": Icon(Icons.Default.Home, "Домой")
            и Text для кнопки, например, buttons[0]

    Вторая кнопка - аналогично первой, только со значком Icons.Default.Call и надписью buttons[1],
        только называется она теперь не просто Button, а OutlinedButton

    Третья кнопка - ElevatedButton вместо Button, значок Star
    Четвёртая кнопка - FilledTonalButton, значок Search
    Пятая - TextButton, без значка

    Шестая - IconButton, без текста, значок Favorite покрасьте основным цветом темы, добавив в Icon третий параметр:
        tint = MaterialTheme.colorScheme.primary

    Седьмая - OutlinedIconButton, значок Settings, tint не нужен
    Восьмая - FilledIconButton, значок ExitToApp
    Девятая - FilledTonalIconButton, значок Face

    Далее идут так называемые "чипы" - элементы интерфейса, похожие на кнопки

    Десятый - AssistChip, внутри круглых скобок - три параметра. Первый - { Действие }, второй - { Text(...) },
        а также укажите третий с уточнением: leadingIcon = { Icon(...) }, значок Check

    И, наконец, последний элемент - ElevatedAssistChip, аналогично предыдущему, значок Clear
    Справка:
    https://metanit.com/kotlin/jetpack/4.2.php
 */