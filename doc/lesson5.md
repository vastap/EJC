# Lesson 05: Objects and classes
## Вступление
В мире Java разработка строится внутри парадигмы [ООП](./lesson0.md), в основе которой лежит одна простая мысль: ```Всё есть объект```. С некоторыми оговорками, вроде: "кроме примитивных типов".

В **языке** Java **объект** представляет класс **java.lang.Object**.
От него неявным образом наследуются абсолютно все классы, используемые в Java программах. А это означает, что данные методы есть у всех объектов.

Object в Java имеет следующие методы:
- getClass
- toString
- equals
- hashCode
- clone (protected)
а также:
- notify
- notifyAll
- wait

## getClass
Объекты - являются экземплярами классов. Поэтому, не удивительно, что через объект можно получить данные класса, экземпляром которого является данный объект.
Метод **getClass()** возвращает объект класса **java.lang.Class**, который представляет собой набор метаданных класса объекта, для которого данный метод вызван. Например:
```java
String text = "test";
java.lang.Class clazz = text.getClass();
// java.lang.String
System.out.println("text class is: " + clazz.getCanonicalName() );
// java.lang.CharSequence
System.out.println("with: " + clazz.getInterfaces()[2].getCanonicalName());
// true
try {
	Field field = clazz.getDeclaredField("value");
    System.out.println("Contains array: " + field.getType().isArray());
} catch (NoSuchFieldException e) {
	System.out.println("Can't find field 'value'");
}
```

Непосредственно у класса так же можно получить **java.lang.Class**, например:
```java
String text = "test";
java.lang.Class clazz = text.getClass();
System.out.println("Is string: " + (clazz == String.class));
```

Важно помнить, что getClass возвращает **runtime** класс:
```java
CharSequence text = new String("text");
// Print: java.lang.String
System.out.println(text.getClass().getTypeName());
```

И позволяет даже при помощи reflection инстанциировать объекты:
```java
try {
	Class myString = Class.forName("java.lang.String");
	Object test = myString.getDeclaredConstructors()[12].newInstance("text");
	System.out.println( test.getClass().getTypeName() );
	System.out.println( myString.getTypeName() );
} catch (Exception e) {
	e.printStackTrace();
}
```
Интересное обсуждение в вопросе: [what is the Class object?](https://stackoverflow.com/questions/4453349/what-is-the-class-object-java-lang-class)

Также позволяет узнавать название класса:
см. stackoverflow: "[What is the difference between canonical name, simple name and class name in Java Class?](https://stackoverflow.com/questions/15202997/what-is-the-difference-between-canonical-name-simple-name-and-class-name-in-jav)".

Позволяет проверять, является ли класс наследником:
```java
Integer number = 2;
System.out.println( Number.class.isAssignableFrom(number.getClass()) ); //true
System.out.println( String.class.isAssignableFrom(number.getClass()) ); //not
```
Второй вариант:
```java
Integer number = 2;
System.out.println( Number.class.isInstance(number) );
System.out.println( String.class.isInstance(number) );
```
Оба метода вызывают native метод. Разница между ними в том, что один использует класс как аргумент, а второй - объект. Метод **isInstance** можно считать более безопасным, т.к. объект, передаваемый в данным метод, может быть null. Метод **isAssignableFrom** же в случае получения null в аргументе бросит **NullPointerException**.

Данные методы являются более предпочтительными, чем instanceof:
```java
Integer number = 2;
System.out.println( number instanceof Number );
```
Они выполняются в Runtime, в то время как instanceof определяется на момент компиляции.
Кроме того следует не забывать, что:
```When using the instanceof operator, keep in mind that null is not an instance of anything.```

## toString
Каждый объект в Java можно отобразить в виде строки, т.е. каждый объект имеет своё строковое представление.
По умолчанию в Object toString определён так:
```java
getClass().getName() + "@" + Integer.toHexString(hashCode());
```
Т.е. это имя обекта + "@" + hexadecimal (шестнадцатеричное) представление хэшкода.
Например: ``java.lang.Object@15db9742``
Данный метод может быть переопределён внутри любого класса.
Обзор на эту тему: [10 подсказок по переопределению метода toString() в Java ](http://info.javarush.ru/translation/2013/08/03/10-подсказок-по-переопределению-метода-toString-в-Java-часть-1-.html).
**Дополнительно:** Интересно, что массив в Java является объектом, но не переопределяет hashCode. Таким образом, для примера, массив int[] вернёт в toString значение вида ```[I@22927a81```. Чтобы вывести массив в виде строки необходимо использовать ```Arrays.toString(array)```.

## hashCode
У каждого объекта в Java есть свой hashCode. Это integer значение, являющееся числовым представлением объекта.
Данный метод определён как **native**, хотя может быть переопределён внутри любого класса. Чтобы получить hashCode именно так, как описано в Object, а не так как переопределено в классе наследнике от Object, необходимо использовать метод: ``System.identityHashCode``.

Контракт hashCode определён в [Java API](http://docs.oracle.com/javase/8/docs/api/java/lang/Object.html#hashCode--) так:
- HashCode одинаков между разными обращениями за HashCode в рамках одного запуска приложения.
- Если 2 объекта равны через Equals, то должны вернуть одинаковый HashCode
- Если 2 объекта не равны через Equals, то необязательно, что они неравны по HashCode. Хотя и говорится, что такого стоит избегать для улучшения производительности в структурах данных, основанных на HashCode.

Способ вычисления HashCode менялся с развитием Java. Более подробно можно прочитать в статье "[Откуда растут ноги у hashCode](https://habrahabr.ru/post/165683/)".
Так же будет полезна статья за 2017 год от 7 февраля: "[Как работает hashCode() по умолчанию?](https://habrahabr.ru/company/mailru/blog/321306/)".
**Кратко:**
В Java6 и Java7 это было случайное число по алгоритму "Park-Miller RNG", в Java8 и 9 это "Thread-local Xorshift", т.е. случайное число по алгоритму horshift на основе состояния потока. Данные о хэшкоде хранятся в заголовке объекта. Пример XORShift'a: [Быстрая реализация Random - алгоритм XORShift](http://www.apofig.com/2014/10/random-xorshift.html?m=1).

## Equals
Метод Equals позволяет определить логику сравнения объектов. 
Отвечает на вопрос: Эквивалентны ли объекты.
Эквивалентные объекты не означает, что это один и тот же объект. По умолчанию сравнение происходит по ссылке:
```java
public boolean equals(Object obj) {
   return (this == obj);
}
```
Данный метод может быть переопределён в наследниках Object. Например, в String метод определён таким образом, чтобы сравнивать сами строки, а не ссылки на них.

## Clone (protected метод)
Object содержит **protected native** метод **clone** для **"поверхностного"** копирования объектов.
Метод помечен protected, чтобы переопределивший его смог расширить область видимости до public и указать интерфейс **Cloneable**. Если вызывать clone без указания интерфейса будет: **java.lang.CloneNotSupportedException**
Хороший пример опасности приведён здесь: [Передача и возврат объектов](http://iais.kemsu.ru:8080/odocs/java/AppendixA.html).
Как видно в примере, ArrayList копирует значения массива при помощи Arrays.copyOf, что влечёт копирование ссылок, а не самих объектов. Поэтому, когда кто-то получит клон - изменения объектов в клоне повлечёт изменение этих объектов в оригинале, т.к. по сути это один и тот же объект. Поэтому более надёжным способом считается сериализация.

## wait, notify, notifyAll
Данные методы относятся к многопоточному выполнению.
Казалось бы, почему данные методы объявлены не в **Thread**, **а в Object**.
Начало ответа могут подсказать в "[Why notify, wait, notifyAll methods are defined in Object class in java?](https://www.quora.com/Why-notify-wait-notifyAll-methods-are-defined-in-Object-class-in-java)" и "[Why wait(), notify() and notifyAll() methods are in Object class and not in Thread class](http://netjs.blogspot.ru/2015/07/why-wait-notify-and-notifyall-methods-in-object-class-java-multi-threading.html)".
Данные методы работают на уровне монитора.
Монитор же ассоциируется с объектом Java, адрес монитора содержится в заголовке объекта.

Про сами методы можно прочитать здесь: [Методы wait и notify](https://metanit.com/java/tutorial/8.5.php)

## Заголовок объекта
Каждый объект имеет свой заголовок.
Каждый заголовок для большинства JVM(Hotspot, openJVM) состоит из двух машинных слов. Для 32-х разрядной системы — 8 байт, для 64-х разрядной системы — 16 байт.
Обзор на эту тему: [Размер Java объектов](https://m.habrahabr.ru/post/134102/).
Так же на ту же тему слайд: [Mark Word: слайд 6](https://www.slideshare.net/JeanPhilippeBEMPEL/out-ofmemoryerror-what-is-the-cost-of-java-objects).
Интересно, что заголовок объекта содержит информацию о мониторе:
"[Difference between lock and monitor – Java Concurrency](http://howtodoinjava.com/core-java/multi-threading/multithreading-difference-between-lock-and-monitor/)".
Так же хорошо описано в статье "[How the Java virtual machine performs thread synchronization](http://www.javaworld.com/article/2078602/java-concurrency/jw-archives--how-the-java-virtual-machine-performs-thread-synchronization.html)".

## Загрузка классов
Про загрузку классов можно прочитать в статье "[загрузка классов, Class Loader](http://java-se-learning.blogspot.ru/2013/07/class-loader.html)" и статью на хабре "[Загрузка классов в Java. Теория](https://habrahabr.ru/post/103830/)".
Главная особенность подхода к загрузке классов: загружать классы тем загрузчиком, который максимально близко находится к базовому. Это позволяет сначала загружать более доверенные классы, т.е. JRE, каталог lib, а потом всё остальное.
Данный механизм не обеспечивает решение конфликтов в случае дублированных **default** методов в интерфейсах (default методы в интерфейсах добавлены в **Java8**).
Подробнее про интерфейсы и default методы можно прочитать в статье "[Методы по умолчанию в Java 8: что могут и чего не могут?](https://goo.gl/P9SrAX)".

Про **инициализацию содержимого** класса можно прочитать на stackoverflow:
[Что раньше инициализируется поля класса или конструктор?](https://ru.stackoverflow.com/questions/464028/Что-раньше-инициализируется-поля-класса-или-конструктор)

## Java Pool
Так же интересен тот факт, что в зависимости от типа, объект может быть помещён в пул констант. В java, для оптимизации потребления памяти, существует два пула: String pool и Integer pool.
В String pool помещаются строки, объявленные при помощи литералов или при помощи метода intern():
```java
String pooledLitStr = "text";
String pooledInternedStr = new String("text").intern();
System.out.println(pooledLitStr == pooledInternedStr);
// Вернётся true
```
По похожему принципу работает пул Integer, только для значений от -128 до 127:
```java
Integer first = 1;
Integer second = new Integer(1);
System.out.println(first == second);
// Вернётся false
```
Верхнюю границу можно поменять для Integer Pool при помощи параметра:
**-XX:AutoBoxCacheMax**

## Финализация
У **java.lang.Object** есть ещё один метод - ```protected void finalize()```
Данный метод может быть вызван, когда объект готов для сборки сборщиком мусора.
Однако вызывается он не напрямую, а ставится в очередь, которая выполняется в отдельном потоке.
Это очень неприятное место. Подробнее: "[finalize и Finalizer](https://habrahabr.ru/post/144544/)".