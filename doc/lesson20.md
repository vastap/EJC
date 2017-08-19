# Lesson 20: java.util.Collections

## Вступление
Для работы с коллекциями (подробнее см. [Lesson 6](./lesson6.md)) существует класс **java.util.Collections**, который содержит набор из статических методов для работы с коллекциями.

## Добавление элементов
Можно в коллекцию добавлять элементы при помощи addAll:
```java
Set<String> t = new TreeSet<>();
Collections.addAll(t,"First","Second");
System.out.println(t); //return [First, Second]
```

## Изменение коллекции (List)
Следующие методы применимы к коллекциям, реализующие интерфейс List.
Подробнее про реализации List см. [Lesson 17](./lesson17.md).
К этим методам относятся:
- **reverse**
```java
List<String> src = Arrays.asList("John","Max","Tom");
Collections.reverse(src);
System.out.println(src); //Tom, Max, John
```
- **shuffle**
```java
List<String> src = Arrays.asList("John","Max","Tom");
Collections.shuffle(src);
System.out.println(src); // In random order
```
Подробнее: "[Сортировка и перетасовка](http://pro-java.ru/java-dlya-nachinayushhix/sortirovka-i-peretasovka-java/)".
- **fill**
```java
List<String> src = Arrays.asList("John","Max","Tom");
Collections.fill(src,"Max");
System.out.println(src); // [Max, Max, Max]
```
- **swap**
```java
List<String> src = Arrays.asList("John","Max","Tom");
Collections.swap(src,0,1);
System.out.println(src); // [Max, John, Tom]
```
- **replaceAll**
```java
List<String> src = Arrays.asList("John","Max","Tom","John");
Collections.replaceAll(src,"John","Mr.John");
System.out.println(src); // [Mr.John, Max, Tom, Mr.John]
```
- **rotate**
Если представить список как ленту, то это означает "прокрутить" эту ленту на указанное количество элементов. Если элемент выходит за пределы массива, то отсчёт его позиции начинается с нуля.
Пример: [Collection rotate](https://www.tutorialspoint.com/java/util/collections_rotate.htm)
- **copy**
Неудачное название метода. На самом деле это замена элементов одного листа элементами другого листа. Более того, если количество элементов будет разным - будет брошено исключение
```
List<String> src = Arrays.asList("John","Max","Tom");
List<String> target = Arrays.asList(null,null,null);
Collections.copy(target,src);
System.out.println(target); // [John, Max, Tom]
```

## Сортировка и поиск (List)
Из-коробки реализованы сортировка и бинарные поиск:
```java
List<Integer> src = Arrays.asList(5,3,4,1,2);
Collections.sort(src); //TimSort, nLog(n) (Insertion Sort + Merge Sort)
System.out.println(src); // [1, 2, 3, 4, 5]
System.out.println(Collections.binarySearch(src,5)); //Index=4
```
см. подробнее про [TimSort](https://neerc.ifmo.ru/wiki/index.php?title=Timsort)

А так же поиск вхождения подсписка в список:
```java
List<Integer> src = Arrays.asList(5,1,2,3,1,2,3,5);
List<Integer> target = Arrays.asList(1,2,3);
System.out.println(Collections.indexOfSubList(src,target)); //first occurrence
System.out.println(Collections.lastIndexOfSubList(src, target)); //last occurrence
```

## Анализ (Collection)
Предоставляет методы для анализа Collections:
- **max**
- **min**
- **frequency**
```java
List<Integer> src = Arrays.asList(1,2,3,3,4,5);
System.out.println(Collections.max(src)); //5
System.out.println(Collections.min(src)); //1
System.out.println(Collections.frequency(src,3)); //2
```
А так же проверки, пересекаются ли между собой коллекции:
```java
List<Integer> src = Arrays.asList(1,2,3,3,4,5);
List<Integer> target = Arrays.asList(3,8,7,0,6,10);
System.out.println(Collections.disjoint(src,target)); //false
```
Вернёт false, т.к. есть общий элемент 3.

## Comparator
Всё что требует сравнения элементов предоставляет возможность указать Comparator:
Поиск (в том числе минимума/максимума), сортировка, реверс элементов.
```java
List<Integer> src = Arrays.asList(1,2,3,3,4,5);
Collections.sort(src, Collections.reverseOrder());
System.out.println(src); //[5, 4, 3, 3, 2, 1]
```
Здесь Collections.reverseOrder() возвращает компаратор для реверсного расположения.

## Пустые неизменяемые коллекции
Collections предоставляет возможность создавать пустые **неизменяемые** коллекции:
Доступны для следующих типов: Enumeration, Iterator, ListIterator, List, Map, NavigableMap, SortedMap, Set, NavigableSet, SortedSet.
Для создания используется метод c названием empty+тип. Например:
```java
List stubList = Collections.emptyList();
System.out.println(stubList); // []
```
Спрашивается, зачем? Эти методы возвращают static final константы. Т.е. каждый раз не создаётся новая коллекция, а возвращается ссылка на один и тот же объект.

## Неизменяемые коллекции из одного элемента
Одиночный элемент можно обернуть в неизменяему коллекцию.
Поддерживаются только: Set (singleton), List (singletonList), Map (singletonMap)
```java
List<String> list = Collections.singletonList("Singleton");
System.out.println(list); // [Singleton]
```

## Неизменяемый List из n элементов
Пример:
```java
List list = Collections.nCopies(4,"Test");
System.out.println(list); //[Test, Test, Test, Test]
```

## Обёртки (wrappers)
- Проверяемые (**checked**) обёртки.
Они проверяют, что в коллекцию добавляются значения правильного типа. 
Например:
```java
List list = new ArrayList<>();
List wrapper = Collections.checkedList(list, Integer.class);
list.add("string");
try{
	wrapper.add("string2");
} catch (ClassCastException e) {
	System.out.println("Can't insert not an Integer value");
}
```
- Неизменяемые (**unmodifiable**) обёртки
```java
List list = new ArrayList<>();
List wrapper = Collections.unmodifiableList(list);
list.add("string");
try{
	wrapper.add("string");
} catch (UnsupportedOperationException e) {
	System.out.println("Can't modify the list");
}
```
- Синхронизированные (**synchronized**) обёртки
Методы обёрток выглядят примерно так:
```java
public E get(int index) {
	synchronized (mutex) {return list.get(index);}
}
```
где mutex = ```final Object mutex;```

Данные обёртки применимы к:
Collection, List, Map, NavigableMap, SortedMap, Set, NavigableSet, SortedSet
Для checked могут быть ещё и checkedQueue.

## Остальные методы
- newSetFromMap(Map<E,Boolean> map)
Вернуть Set из Map. На момент создания Map должна быть пустой.
Как мы помним, Set построены на том, что key разные, а value - какая-нибудь константа. Здесь же в роли такой константы выступает Boolean (```static final Boolean TRUE```).
```java
//ONLY <E, Boolean> (!)
Map<String, Boolean> map = new HashMap<>();
Set<String> set = Collections.newSetFromMap(map);
set.add("Max");
System.out.println(set); //[Max]
```
- asLifoQueue(Deque<T> deque)
Вернуть view двуконечной очереди как Last-in-first-out (Lifo) очередь

## Enumeration
Для начала стоит прочитать: [What is difference between Enumeration and Iterator in Java?](http://javarevisited.blogspot.ru/2010/10/what-is-difference-between-enumeration.html)
- list(Enumeration<T> e)
Вернуть ArrayList, который содержит элементы, которые возвращает Enumeration
- enumeration(Collection<T> c)
Вернуть Enumeration по указанной коллекции