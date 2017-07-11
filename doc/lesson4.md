# Lesson 04: Arrays
## Вступление
Одна из саммых распространённых структур данных в Java является массив. Он везде. Даже строки являются на самом деле надстройкой над массивом символов, т.е. [Char](https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html). Так же широко используется в буферах (массивы байт) и в **Java Collection Framework**.

## Общее
Массивы представляют из себя структуру данных в виде набора элементов, расположенных в памяти последовательно, т.е. непосредственно друг за другом. Обращение к элементам происходит по их индексу за константное время. У Oracle указано, что:
``An array is a container object that holds a fixed number of values of a single type.``
Т.е. массив - это прежде всего **ОБЪЕКТ**, который является контейнером для объектов одного и того же типа.

Размерность массива может быть указана как у типа массива, так и у названия переменной. Но размерность массива является частью описания типа переменной, поэтому правильным считается следующее описание массива:
```java
String[] anArrayOfStrings;
```
Подробнее см. [Oracle Tutorials - Arrays](https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html)


Сколько же может хранится объектов в массиве? Это ограничение равносильно предельному значению Integer, т.е. значение Integer ограничено 4 байтами (32 бита), а это 2 147 483 647 (два миллиарда сто сорок семь миллионов). Связано это как раз с тем, что массив - это объект. А у каждого Java объекта есть свой заголовок с метаинформацией. На эту тему можно прочитать [What is in java object header
](https://stackoverflow.com/questions/26357186/what-is-in-java-object-header) и [Размер Java объектов](https://m.habrahabr.ru/post/134102/) и на слайдах [JavaOne 2013: Memory Efficient Java](https://www.slideshare.net/cnbailey/memory-efficient-java).
Чтобы узнать длинну массива, необходимо использовать ``<массив>.length``.

Массивы могут быть не только одномерными, но и двумерными, трёхмерными и т.д. Размер, т.е. длинна при этом должна быть явно определена только для строк массивов:
```java
int[][] array =  int[5][];
```


Массивы имеют фиксированный размер, указываемый при созднании. Что естественным образом вытекает из того, что массив - это единый участок памяти. И этот участок в памяти выделяется в области памяти, называемый кучей (Heap).
Простой эксперимент для подтверждения:
```java
public class Main {
	private static long last;

	public static void main(String[] args) {
		measure();
		int[] a = new int[100000];
		measure();
	}

	private static void measure() {
		long heapFreeSize = Runtime.getRuntime().freeMemory();
		if (last!=0) System.out.println(last - heapFreeSize);
		last = heapFreeSize;
	}
}
```
В примере видно, что мы создаём массив int'ов. Размер 1 примитивного integer равен 4 байтам. Итого ожидаем 100000 * 4. С небольшой погрешностью получим ожидаемый результат (погрешность из-за того, что используется не только объявление массива, но и различные вспомогательные операции для вычисления используемого объёма хипа). Мы можем такое увидеть потому что массив инициализирует каждый свой элемент значением по умолчанию. Для объектов это null, для примитивов это нулевое значение.

## Копирование
Единственным способом получить увеличенный массив - создать его копию.
Для этого существует несколько вариантов:
- Arrays.copyOf
```java
int[] original = new int[6];
int[] extended = Arrays.copyOf(original, original.length*2);
```
- System.arraycopy
```java
int[] original = new int[6];
int[] target = new int[original.length*2];
System.arraycopy(original, 0, target, 0, original.length);
```
Разницы между ними нет в реализации, т.к. Arrays.copyOf вызывает System.arraycopy. Единственная разница в том, что Arrays.copyOf создаёт массив, а System.arraycopy копирует в уже созданный ранее массив.

## Другое
Для выполнения различных действий над массивами существует утильный/утилитный (Util) класс java.util.Arrays. При помощи него можно:
- Arrays.fill
- Arrays.sort
- Arrays.binarySearch
Для двоичного (поиска делением) поиска необходимо, чтобы массив был отсортирован ascending, т.е. по восходящей.

Сдвиг элементов массива уже реализован и его можно подсмотреть в ArrayList.remove. Выполняется он при помощи ``System.arraycopy(<массив>, index+1, <массив>, index, numMoved)``. Количество сдвигаемых элементов, numMoved вычисляется как ``size - index - 1``. Последний элемент выставляется при этом в null.

## Материалы
kostin.ws - Статья: "[Массивы в Java](http://kostin.ws/java/java-arrays.html)"
alexanderklimov - Статья: "[Массив](http://developer.alexanderklimov.ru/android/java/array.php)"
Спецификация: [jls JSE7](http://docs.oracle.com/javase/specs/jls/se7/html/jls-10.html#jls-10.4)