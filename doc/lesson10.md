# Lesson 10: Java IO

## Вступление
Java IO - это API для работы с потоками данных, чтение и запись.
На сайте Oracle есть tutorial: "[I/O Streams](https://docs.oracle.com/javase/tutorial/essential/io/streams.html)".
Представляет из себя блокирующий (синхронный) ввод/вывод. Поэтому, позже как альтернатива, появилось Java NIO (где N - non-blocking).
О их различиях подробнее можно прочитать здесь: "[Основные отличия Java IO и Java NIO](https://habrahabr.ru/post/235585/)"

## Stream - поток данных
В Java IO есть 2 потока данных: для чтения (InputStream) и для записи (OutputStream). Название указывает на направление: входяящий поток - Input, исходящий - Output.
Является closeable, т.е. требует закрытия ресурса после использования:
```java
public abstract class InputStream implements Closeable {
```
InputStream является абстрактным и имеет набор реализаций, более подробно описывающих сущность данных:
![](../img/inputstream.png)
Например, FileInputStream описывает поток байт из файла, ByteArrayInputStream описывает поток байт из массива, ObjectInputStream описывает поток байт из объекта (сериализация), BufferedInputStream описывает буферизируемый поток и т.д.

Пример работы ByteStream описан в Oracle Tutorial: "[Byte Streams](https://docs.oracle.com/javase/tutorial/essential/io/bytestreams.html)".
А так же указано, что стоит использовать буферизацию потоков:
[Buffered Streams](https://docs.oracle.com/javase/tutorial/essential/io/buffers.html). Причина в том, что каждый запрос на чтение/запись обрабатывается непосредственно силами операционной системы. Поэтому каждый запрос чтения/записи приводит к обращению к диску или сетевой активности, что может быть довольно дорого. Поэтому буферизация уменьшает количество таких обращений, используя промежуточное хранилище - буфер.

## Character Streams
Помимо работы с потоками байт есть возможно работать с символьными потоками.
Подробности от Oracle: "[Character Streams](https://docs.oracle.com/javase/tutorial/essential/io/charstreams.html)".
Такие поток управляются потомками классов Reader and Writer:
![](../img/reader.png)

## Освобождение ресурсов
Как ранее было сказано, необходимо не забывать освобождать ресурсы.
Т.к. закрываемые ресурсы реализуют java.io.Closeable (Close + able, т.е. те ресурсы, которые можно закрыть). Этот интерфес предписывает реализовывать метод **close()**.
Интересно, что такой метод **throws IOException**, поэтому всех мучили страшные конструкции try-catch вокруг close, пока в Java7 интерфейс Closeable не сделали наследником от **AutoCloseable**. Данный интерфейс позволяет использовать такие ресурсы в блоке try-with-resources, что позволяет не писать close, т.к. он будет выполнен автоматически после заверешния блока try-with-resources.

Oracle добавили Tutorial на эту тему: "[The try-with-resources Statement](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html)"
А так же можно посмотреть: [Java Core October: Exceptions. Лекция #6](https://www.youtube.com/watch?v=2_ThvTc3X8Y&feature=youtu.be&list=PLwcDaxeEINaemIX9OqrAjilBL6MTNikh8&t=2181)
В видео можно увидеть интересные подробности. Например, про то, что закрытие ресурсов выполняется в блоке, похожем на finally, который выполняется до основного finally.

## Дополнительные материалы
Подробный цикл статей: "[Posts in Category: java io](http://lozenko.blog/category/java-io/page/4/)".
Пример вопросов про java.io: "[Потоки ввода/вывода (вопросы и ответы)](http://javastudy.ru/interview/input-output/)".