# Lesson 13: Algorithms: Quick and Radix Sort

## Быстрая сортировка (Quick Sort)
Одной из самых эффективных сортировок является быстрая сортировка, она же сортировка Хоара. Не зря именно она используется в Arrays.sort.
Интересно, что алгоритм был придуман Хоаром во время его пребывания в Советском Союзе, где он обучался в Московском университете компьютерному переводу и занимался разработкой русско-английского разговорника.

Отличительной особенностью является то, что в отличии от более простых сортировок, даёт среднее время выполнения (complexity) не O(n^2), а O(n log n).
Реализуется при помощи **рекурсии**.
Так же используется такое понятие, как опорный элемент, называемый **pivot**.
Относится к так называемым **"Divide and Conquer"** (разделяй и властвуй), т.к. каждый раз делит массив на два подмассива - до pivot и после.

**Видео:**
"[Фоксфорд: Быстрая сортировка](https://www.youtube.com/watch?v=Xgaj0Vxz_to)" - здесь очень понятно объясняется, почему такая сложность и как это зависит от условий.
**Материал: **
Cтатья на medium.com: "[Информатика в JavaScript: Быстрая сортировка (Quicksort)](https://medium.com/devschacht/nicholas-c-zakas-computer-science-in-javascript-quicksort-afa07c0a47f0)".
**Сложность:** O(n log n) в среднем. Может деградировать до квадратичной сложности в случае неудачного входного набора данных (если уже отсортирован).

**Реализация:**
```java
public static void quickSort(int[] source, int leftBorder, int rightBorder) {
	int leftMarker = leftBorder;
	int rightMarker = rightBorder;
	int pivot = source[(leftMarker + rightMarker) / 2];
	do {
		// Move left marker to the right until element less than pivot element
		while (source[leftMarker] < pivot) {
			leftMarker++;
		}
        // Move right marker to the left until element larger than pivot element
		while (source[rightMarker] > pivot) {
			rightMarker--;
		}
		// If <= then we done all swaps and reach last step
		if (leftMarker <= rightMarker) {
			// Left marker will be less than right only if we have to swap elements
			if (leftMarker < rightMarker) {
				int tmp = source[leftMarker];
				source[leftMarker] = source[rightMarker];
				source[rightMarker] = tmp;
			}
			// Move markers on one position to form new bounds
            leftMarker++;
			rightMarker--;
		}
	} while (leftMarker <= rightMarker);

    // If left marker less than end border
	if (leftMarker < rightBorder) {
		quickSort(source, leftMarker, rightBorder);
	}
	// If right marker bigger than start border
	if (leftBorder < rightMarker) {
		quickSort(source, leftBorder, rightMarker);
	}
}
```
**Суть:**
Берём массив, выставляем два указателя L и R на левую и правую границу сортируемого участка (первый проход - начало и конец массива). Определяем опорный элемент pivot (например, по центру). Наша задача - переместить значения, меньшие чем pivot, слева, а большие - справа.
Для этого сначала двигаем указатель L пока не найдём значение, большее чем pivot. Если меньшее значения не нашли, то L совпадёт с pivot.
Потом двигаем указатель R пока не найдём меньшее чем pivot значение.
Если большее значение не нашли, то R совпадёт с pivot.
Далее, если указатель L находится до указателя R или совпадает с ним, то пытаемся выполнить обмен элементов, если элемент L меньше, чем R.
Далее L сдвигаем вправо на 1 позицию, R сдвигаем влево на одну позицию.
Когда левый маркер L окажется за правым маркером R это будет значить, что обмен закончен, слева от pivot меньшие значения, справа от pivot большие значения.

После этого рекурсивно вызываем такую же сортировку для участков массива от начала сортируемого участка до правого маркера и от левого маркера до конца сортируемого участка.

Этот алгоритм более сложный, чем простая сортировка, поэтому его лучше зарисовать.
Возьмём белый лист бумаги, запишем: ``` 4 2 6 7 3```
Pivot по центру, т.е. число 6. Обведём его в круг. Под 4 напишем L, под 3 напишем R.
4 меньше чем 6, 2 меньше чем 6. Итого, L переместился на положение pivot, т.к. по условию L не может уйти дальше, чем pivot.
Напишем снова `` 4 2 6 7 3 ```, обведём 6 вкруг (pivot) и поставим под ним L.
Теперь двигаем указатель R. 3 меньше чем 6, поэтому ставим маркер R на цифру 3.
Так как 3 меньше чем pivot 6 - выполняем swap, т.е. обмен.
Запишем результат: ``` 4 2 3 7 6 ```, обводим 6 вкруг, т.к. он по прежнему pivot.

Указатель L на цифре 3, указатель R на цифре 6.
Мы помним, что двигаем указатели до тех пор, пока L не зайдём за R.
L двигаем на следующую цифру.

Тут хочется разобрать два варианта: если бы предпоследняя цифра была 7 и если бы предпоследняя цифра была не 7, а 1.
**Предпоследня цифра 1**:
Сдвинули указатель L на цифру 1, т.к. мы можем двигать L до тех пор, пока указатель L указывает на цифру, меньшую чем pivot. А вот R мы не можем сдвинуть с 6, т.к. R не мы можем двигать только если указатель R указывает на цифру, которая больше чем pivot.
swap не делаем, т.к. 1 меньше 6.
Записываем положение: ``` 4 2 3 1 6 ```, обводим pivot 6. L сдвигается на pivot и болльше не двигается. R тоже не двигается. Обмен не производим.
Сдвигаем L и R на одну позицию и подписываем цифру 1 маркером R, а L получается вне числа. Т.к. L вне числа - ничего не делаем, а вот часть ``` 4 2 3 1 ``` выписываем снова, т.к. это наша левая часть, меньшая чем pivot 6. Выделяем новый pivot и начинаем всё снова )

**Предпоследняя цифра 6**:
Сдвинули указать L на цифру 7, правый указатель не можем двигать, т.к. он уже указывает на pivot. т.к. 7 больше, чем pivot, то делаем swap.
Запишем результат: ``` 4 2 3 6 7 ```, обводим 6 кружком, т.к. он pivot.
Указатель L теперь сдвигается на цифру 7, а указатель R сдвигается на цифру 3.
Часть от L до конца нет смысла сортировать, т.к. там всего 1 элемент, а вот часть от 4 до указателя R отправляем на сортировку. Выбираем pivot и начинаем всё снова )

Может на первый взгляд показаться, что если расставить много одинаковых с pivot значений, то это сломает алгоритм, но это не так. Можно напридумывать каверзных вариантов и на бумажке убедиться, что всё правильно и подивиться, как такие простые действия предоставляют такой надёжный механизм.
Единственный минус - такая сортировка не является стабильной. Т.к. при выполнении обмена одинаковые элементы могут поменять свой порядок, если один из них встретился до pivot до того, как другой элемент попал в часть до pivot при помощи обмена.

## Сортировка слиянием (Merge Sort)
Ещё одной сортировкой из разряда "Разделяй и властвуй" является сортировка слиянием.

Лекция: "[CS50: Алгоритмы сортировки. Сортировка слиянием](https://javarush.ru/quests/lectures/questharvardcs50.level03.lecture11)"
YoyTube: "[Merge Sort](https://www.youtube.com/watch?v=EeQ8pwjQxTM)"

**Реализация:**
```java
public static void mergeSort(int[] source, int left, int right) {
	int delimiter = left + ((right - left) / 2) + 1;
	// Recursive merge for parts bigger than 2 elements
	if (delimiter > 0 && right > left + 1) {
		mergeSort(source, left, delimiter - 1);
		mergeSort(source, delimiter, right);
	}

	// Compare and merge
	int[] buffer = new int[right - left + 1];
	int cursor = left;
	for (int i = 0; i < buffer.length; i++) {
		if (delimiter > right || source[cursor] < source[delimiter]) {
			buffer[i] = source[cursor];
			cursor++;
        } else {
			buffer[i] = source[delimiter];
			delimiter++;
		}
	}
	System.arraycopy(buffer, 0, source, left, buffer.length);
}
```
**Суть:**
Вся суть сводится к тому, что мы принимаем на вход массив с указанием начала и конца участка для сортировки. При начале сортировки - это начало и конец массива.
Далее мы вычисляем delimiter - положение делителя. Если делитель может разделить на 2 части - значит вызываем рекурсивно сортировку для участков, на которые делитель разбил массив.
Подготавливаем дополнительный буферный массив, в котором мы подготовим отсортированный участок.
Далее, устанавливаем курсор в начало сортируемого участка и начинаем идти по каждому элементу пустого массива, который мы подготовили и заполняем его наименьшими элементами.
Если элемент, на который указывает курсор меньше, чем элемент, на который указывает делитель - помещаем в буферный массив этот элемент и сдвигаем курсор. В противном случае помещаем в буферный массив элемент, на который указывает разделитель и сдвигаем разделитель. Как только разделитель уйдёт за границы сортируемого участка или мы заполним весь массив - указанный диапазон считается отсортированным.

## Сортировка подсчётом (Counting Sort)
Данная сортировка является основой для Radix Sort.
**Видео:** [Counting Sort](https://www.youtube.com/watch?v=_q0OOXo4l7E)
**Материал:** "[geeksforgeeks.org : counting sort](http://www.geeksforgeeks.org/counting-sort/)"
Статья "[Counting Sort Algorithm](https://www.interviewcake.com/concept/java/counting-sort)"
**Реализациия:**
```java
public static int[] countingSort(int theArray[]) {
	// for each value in the source array increment count in indexes array
	int indexes[] = new int[9 + 1];
	for (int i = 0; i < theArray.length; i++) {
		indexes[theArray[i]]++;
    }
	// populate the final sorted array
	int[] sortedArray = new int[theArray.length];
	int currentSortedIndex = 0;
	// for each index in indexes
	for (int index = 0; index < indexes.length; index++) {
		// for the number of times the item occurs
		int count = indexes[index];
		for (int i = 0; i < count; i++) {
			sortedArray[currentSortedIndex] = index;
			currentSortedIndex++;
		}
	}
	return sortedArray;
}
```

## Поразрядная сортировка (Radix Sort)

Материал: "[Поразрядная сортировка :: Radix sort](http://sorting.valemak.com/radix/)"
Geeks for geeks: "[Radix Sort](http://www.geeksforgeeks.org/radix-sort/)"

