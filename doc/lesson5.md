# Lesson 05: Object
## Вступление
В мире Java разработка строится внутри парадигмы ООП, в основе которой лежит одна простая мысль: ```Всё есть объект```. С некоторыми оговорками, вроде: "кроме примитивных типов".
В Java объект представляет класс **java.lang.Object**. От него неявным образом наследуются абсолютно все классы, используемые в Java программах.

Object в Java имеет следующие методы:
- getClass
- toString
- equals
- hashCode
- notify
- notifyAll
- wait
- clone (protected)

## getClass
Каждый объект, который встречается в коде имеет свой класс. Описание класса в Java представляет собой класс **java.lang.Class**.
Данный класс предоставляет следующие возможности:
- Узнать название класса
см. stackoverflow: "[What is the difference between canonical name, simple name and class name in Java Class?
](https://stackoverflow.com/questions/15202997/what-is-the-difference-between-canonical-name-simple-name-and-class-name-in-jav)".
- Узнать, является ли класс наследником
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

##toString
Каждый объект в Java можно отобразить в виде строки, т.е. каждый объект имеет своё строковое представление.
По умолчанию в Object toString определён так:
```java
getClass().getName() + "@" + Integer.toHexString(hashCode());
```
Т.е. это имя обекта + "@" + hexadecimal (шестнадцатеричное) представление хэшкода.
Например: ``java.lang.Object@15db9742``
Данный метод может быть переопределён внутри любого класса.

##hashCode
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

##Clone (protected метод)
Object содержит **protected native** метод **clone** для **"поверхностного"** копирования объектов.
Метод помечен protected, чтобы переопределивший его смог расширить область видимости до public и указать интерфейс **Cloneable**. Если вызывать clone без указания интерфейса будет: **java.lang.CloneNotSupportedException**
Хороший пример опасности приведён здесь: [Передача и возврат объектов](http://iais.kemsu.ru:8080/odocs/java/AppendixA.html).
Как видно в примере, ArrayList копирует значения массива при помощи Arrays.copyOf, что влечёт копирование ссылок, а не самих объектов. Поэтому, когда кто-то получит клон - изменения объектов в клоне повлечёт изменение этих объектов в оригинале, т.к. по сути это один и тот же объект. Поэтому более надёжным способом считается сериализация.

## wait, notify, notifyAll
Можно прочитать здесь: [Методы wait и notify](https://metanit.com/java/tutorial/8.5.php)