# Lesson 19: Static, Abstract & Interfaces

## Static
Статические члены классов хранятся в области памяти **Permanent Generation** (с Java8 **MetaSpace**). Что логично, т.к. статические члены классов являются частью метаданных классов. Это также предотвращает сборку данных полей Garbage Collector'ом.
На эту тему есть отличный ответ: "[Как в java хранятся статические поля?](https://ru.stackoverflow.com/questions/466504/Как-в-java-хранятся-статические-поля)".
Статические поля не требуют создания Instance класса.
Интересно, что внутренние **Enum** всегда являются static: "[JLS - Enum](http://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#jls-8.9)".
Так как поле является метаданными класса, то важно учитывать тип, через который происходит обращение к public static полям.

Подробнее можно почитать статью: "[10 заметок о модификаторе Static в Java](http://info.javarush.ru/translation/2014/04/15/10-заметок-о-модификаторе-Static-в-Java)".

Так же есть статические блоки: "[Что такое статический блок и статическая инициализация в Java?](http://javaway.info/chto-takoe-staticheskij-blok-i-staticheskaya-initsializatsiya-v-java/)".

## Abstract
Абстрактные классы - классы, instance которых нельзя создать и являются каркасом для классов, которые будут созданы на основе Abstract классов.
Могут содержать абстрактные методы, т.е. методы, тело которых не определено. Такие методы обязаны быть определены в не абстрактных наследниках.

Подробнее: "[Абстрактные классы и методы](http://kostin.ws/java/java-abstract-and-interfaces.html)".
Oracle имеет даже tutorial: "[Abstract Methods and Classes](https://docs.oracle.com/javase/tutorial/java/IandI/abstract.html)".

В абстрактных классах можно определять статические методы, но нельзя объявлять статический абстрактный метод. И это логично, т.к. метод вызывается у класса, а не у объекта.

Абстрактные классы не могут иметь абстрактные конструкторы. Но объявленный в асбтрактном предке необстрактный конструктор должен быть вызван из неабстрактного предка.

Интересный вопрос для понимания: "[Почему абстрактные классы имеют конструктор?](https://stackoverflow.com/questions/2170500/why-do-abstract-classes-in-java-have-constructors)".
Ещё один вопрос про коснтруктор: "[Can abstract class have Constructor in Java - Interview Question](http://www.java67.com/2013/02/can-abstract-class-have-constructor-in-java.html)".

## Интерфейсы
Интерфейс - представляет описание контракта класса, который реализует (implements, имплементирует) данный интерфейс.
Материал: "[Интерфейсы](https://metanit.com/java/tutorial/3.7.php)"

Интерфейсы в Java 8 изменились.
Более подробно: "[Java 8 Interface](http://www.journaldev.com/2752/java-8-interface-changes-static-method-default-method)".
Появились default методы: "[Java 8 explained: Default Methods](https://zeroturnaround.com/rebellabs/java-8-explained-default-methods/)".
Важно, что если есть 2 default метода, которые конфликтуют, то ни один из них не будет вызван и нужно указать свою реализацию, о чём написано в статье выше.

Важно помнить, что все поля в интерфейсах неявно static и final: "[Why are all fields in an interface implicitly static and final?](https://stackoverflow.com/questions/1513520/why-are-all-fields-in-an-interface-implicitly-static-and-final)".