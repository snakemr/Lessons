import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.BackHandler
import ui.ElevatedButton // не удалять!

@Composable
fun LessonBack() {

}

/*
    Создайте текстовую переменную file, по умолчанию равную null (то есть, ничто, отсутствие какого-либо значения):
    var file: String? by remember { mutableStateOf(null) }
    Обратите внимание, что здесь мы явно указали тип данных как "строка текста", которая может быть null (знак ?)

    Содержимое будет внутри центрированного столбца, сначала идёт текст заголовка с отступами на 4

    Ниже поместите конструкцию file?.let { блок1 } ?: блок2
    блок1 отображается если file не null,
        поместите сюда Image, отображающий painterResource от параметра it - он равен строке file, но уже точно не null;
    блок2 отображается если file == null,
        поместите сюда горизонтальный ряд, внутри которого - 3 кнопки, как в задании №6.
    Разбивайте всю эту конструкцию на несколько строк по необходимости

    Суть этого задания - в слежении за аппаратной кнопкой (или жестом) "возврата" устройства.
    По её нажатии пользователь будет возвращаться к выбору из 3 картинок.
    Для этого напишите выше или ниже основной конструкции команду обработки:
    BackHandler(file != null) { file = null }
    то есть, если file чем-то заполнен, то есть, пользователь сделал выбор, кнопка возврата делает откат к null;
    в противном случае кнопка работает, как обычно - закрывает приложение.
*/