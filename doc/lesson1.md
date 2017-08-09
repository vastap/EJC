# Lesson 01: Basic Concepts
**Java** - интерпритируемый язык программирования. Поэтому java код перед исполнением должен быть представлен в виде промежуточного формата, который называется байт-кодом, файлы которого имеют расширение **.class**.
Сам же код сохраняется в файлах с расширением **.java**.
Особенность исполнения Java программ в том, что байткод выполняется на виртуальной машине Java (JVM), которая интерпритирует байткод в машинные команды.
Но есть ещё одна интересная особенность. JVM не всегда транслирует байткод в машинные инструкции и исполнять их, т.к. это долго. Когда участок кода часто исполняется, то он компилируется JIT-компилятором, чтобы в следующий раз не выполнять трансляцию, а выполнять скомпилированный в машинный код.
Подробнее можно послушать в докладах "[JVM: краткий курс общей анатомии](https://www.youtube.com/watch?v=JbLClSMRK_I)" и "[ServerSide: Внутренняя кухня JIT-компилятора](https://www.youtube.com/watch?v=HreePIBctXY)"

## JRE и JDK
Java может поставляться как JRE, так и JDK.
- JRE - Java Runtime Environment.
Из названия понятно, что это среда выполнения. Содержит набор стандартных библиотек, виртуальную машину Java (Java Virtual Machine, JVM).
http://www.oracle.com/technetwork/java/javase/jre-8-readme-2095710.html
- JDK - Java Developer Kit
Набор разработчика Java. Соответственно, сюда входят исходный код (src.zip), документация, компилятор (javac), декомпилятор (javap), различные вспомогательные утилиты (например, архиватор jar). И сама JRE.
Более подробно про состав: "[Contents of the JDK](http://www.oracle.com/technetwork/java/javase/jdk-8-readme-2095712.html)".

## JLS
JLS - Java Language Specification
Описывает то, каким образом внутри всё работает. Различные технические нюансы и хитрости. Подробнее: [Java8 JLS](http://docs.oracle.com/javase/specs/jls/se8/html/index.html)

## Распространение программ
Распространение написанного Java приложения зависит от его типа.
JavaSE приложениt: "[Распространение настольных приложений Java](https://netbeans.org/kb/docs/java/javase-deploy_ru.html)".
Java WebStart: [Java WebStart](https://www.java.com/ru/download/faq/java_webstart.xml)
JavaFX приложение: [Native pack](http://docs.oracle.com/javafx/2/deployment/self-contained-packaging.htm).
JavaEE приложения: [Types of J2EE Archive Files](https://docs.oracle.com/cd/E19830-01/819-4712/ablgz/index.html)

## Entry Point
У каждой Java программы есть точка входа - [Entry Point](https://docs.oracle.com/javase/tutorial/deployment/jar/appman.html).
Более подробно см. комментарий к вопросу "[Why is the Java main method static?](https://stackoverflow.com/a/151666)"

## Создание простейшей Java программы
Простейшая Java программа должна удовлетворять следующим требованиям:
- Состоять из файла с расширением **.java**
- В java файле объявить класс с модификатором доступа **public**, т.к. данный класс должен быть доступен снаружи, т.к. JVM должна иметь доступ к нему.
- Класс имеет название файла, с учётом регистра букв.
- Класс содержит **public** метод, который является **static** и называется main. Потому что JVM не будет создавать экземляр класса, поэтому метод статичный.
- Опционально, можно указать размещение в пакете, например: **package task_01;**
Важно, что размещение в пакете "task_01" означает, что java файл находится в каталоге "task_01", относительно корневого каталога исходного кода проекта, например: "src".

## Выполнение простейшей Java программы
Самое первое, что нужно выполнить - скомпилировать (**compile**) класс.
Для этого в состав JDK входит компилятор джава кода: **[javac](http://docs.oracle.com/javase/8/docs/technotes/tools/windows/javac.html)**, т.е. java compiler.
```
C:\EJC>javac -d ./out -sourcepath ./src ./src/task_01/Main.java
```
Таким образом java compiler в **d**иректорию (**-d**) ./out все java классы из каталога исходного кода (**-sourcepath**) ./src и укажет, что главный класс будет ./src/task_01/Main.java.
После этой команды будут найдены все .java файлы в каталоге src и с сохранением структуры будут скомпилированы в байткод в каталог out.

После этого мы можем запустить программу при помощи [java](http://docs.oracle.com/javase/7/docs/technotes/tools/windows/java.html):
```
C:\EJC>java -cp out task_01.Main
```
Укажем, откуда в **c**lass**p**ath загрузить файлы и укажем, какой Main класс использовать.

## Если появляются зависимости
Если у проекта появляются зависимости, например:
```java
package task_01;
import org.joda.time.DateTime;

public class Main{
	public static void main(String args[]){
		DateTime dt = new DateTime();
		System.out.println("Hello, world"+dt);
	}
}
```
При помощи импорта добавляется зависимость от стороннего jar: [joda-time](https://mvnrepository.com/artifact/joda-time/joda-time/2.9.9)
Чтобы мы могли скомпилировать, нам нужно добавить в classpath библиотеки, которые используются в коде. Для этого скомпилировать нужно с добавлением в classpath используемых библиотек (в данном случае lib):
```
C:\EJC>javac -d ./out -cp lib/* -sourcepath ./src ./src/task_01/Main.java
```
И теперь мы запускаем так же с указанием в classpath библиотек:
```
C:\EJC>java -cp "out;lib/*" task_01.Main
```
В Unix системах вместо ";" используется ":"

Отличная статья на хабре: "[Работа с Java в командной строке](https://habrahabr.ru/post/125210)".

## Байткод
Чтобы увидеть, как выглядит скомпилированный файл в байткоде можно попросить дизассемблер джава класс файлов [javap](http://docs.oracle.com/javase/8/docs/technotes/tools/windows/javap.html).

Хороший материал, чтобы примерно представлять, как это работает: "[Java Bytecode Fundamentals](https://habrahabr.ru/post/111456/)" и "[Структура байт-кода виртуальной машины Java](https://habrahabr.ru/post/69797/)". А так же пример можно увидеть здесь: [A Java Programmer’s Guide to Byte Code](https://www.beyondjava.net/blog/java-programmers-guide-java-byte-code/) и даже посмотреть доклад: [Байткод для любознательных](https://www.youtube.com/watch?v=YtFT9vJG2lw).