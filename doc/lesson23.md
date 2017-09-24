# Lesson 23: Date

## Date
Работа с датой и временем - одна из наиболее часто встречаемых задач. Вход и выход пользователя, дата рождения, срабатывание события - всё это требует работы с датой. И в Java существует класс **java.util.Date** для этого.
Единственный не Deprecated конструктор предназначен для указания текущей даты:
```java
Date date = new Date();
```
Если мы посмотрим, то увидим, что это ничто иное, как ```System.currentTimeMillis()```, т.е. количество миллисекунд, прошедших между текущим моментом времени и датой ```January 1, 1970 UTC```.
Чтобы указать какую-то определённую дату, нам нужно указать её в определённом формате. Поэтому, в Java существует абстрактный класс **java.text.DateFormat** и его наследник - **java.text.SimpleDateFormat**. С его помощью можно получить какую-то определённую дату:
```java
SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
try {
	Date date = format.parse("2012-07-23");
} catch (ParseException e) {
	e.printStackTrace();
}
```
К сожалению, без try блока не обойтись, поэтому запись не слишком удобна.
К сожалению, арсенал доступных действий не так велик:
- Получить количество миллисекунд из указанной даты (с 1 января 1970 года)
- идёт ли дата, чей метод вызывается, после/до другой Date
- сравнить с другой Date
Остальные методы являются deprecated.

По этому причине, для такой задачи, как подсчёт количества дней между датами, придётся использовать вспомогательные средства, например: **java.util.concurrent.TimeUnit**. Несмотря на расположение в пакете concurrent у данного класса есть множество применений и вне многопоточности.
Подробнее можно прочитать тут: [The Highly Useful Java TimeUnit Enum](https://www.javaworld.com/article/2074114/core-java/the-highly-useful-java-timeunit-enum.html).
Пример:
```java
SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
try {
	Date bornDate = format.parse("1951-05-26");
	Date deathDate = format.parse("2012-07-23");
	long diff = deathDate.getTime() - bornDate.getTime();
	System.out.print ("Days: ");
    System.out.println (TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
} catch (ParseException e) {
	e.printStackTrace();
}
```
Получение из даты таких величин, как номер дня, года и т.п. deprecated. На замену им пришёл начиная с Java 1.1 **java.util.Calendar**. Причина: ```not amenable to internationalization```.
Подробнее можно прочитать так же тут: [Java и время: часть первая](https://habrahabr.ru/post/274811/).

## Calendar
Класс **java.util.Calendar** был введён на замену Date. Именно в нём реализованы методы получения значения текущего дня, месяца и т.п.
```java
Calendar calend = Calendar.getInstance();
System.out.println("Day: " + calend.get(Calendar.DAY_OF_MONTH));
System.out.println("Month: " + (calend.get(Calendar.MONTH)+1));
```
А так же при помощи его реализации **java.util.GregorianCalendar**:
```java
SimpleDateFormat format = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
Calendar calendar = new GregorianCalendar(2013,0,31);
System.out.println(format.format(calendar.getTime()));
```
Так же Date можно превратить в календарь:
```java
Calendar calendarDate = Calendar.getInstance();
calendarDate.setTime(new Date());
```
благодаря календарю, задачу с количеством дней в пределе одного года можно решить так:
```java
calendarDate.get(Calendar.DAY_OF_YEAR)
```
данный метод возвращает количество дней в **текущем** году. Задачу, указанную выше, но для разных годов, решить не выйдет. И остаётся всё тот же способ с рассчётом времени, получим количество миллисекунд через ```getTimeInMillis()```
Подробнее можно прочитать здесь: [книга Learning Java, глава Dates and Times](goo.gl/5zb9Ks).

## Java 8 Date Time API
В Java 8 наконец-то решили проблемы с работой с датой и временем при помощи класса **java.time.LocalDateTime**.
Например, получение текущего месяца:
```java
LocalDateTime now = LocalDateTime.now();
System.out.println(now.getMonth().getValue());
String name = now.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
System.out.println(name);
```
Так же есть свой форматтер, как DateFormat, только **java.time.format.DateTimeFormatter**:
```java
String now = "2016-11-09 10:30";
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
LocalDateTime dateTime = LocalDateTime.parse(now, formatter);
System.out.println(dateTime);
```
Ну и форматированный вывод:
```java
LocalDateTime now = LocalDateTime.now();
System.out.println("Before : " + now);
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
String formatDateTime = now.format(formatter);
System.out.println("After: " + formatDateTime);
```
Ну и чтобы совсем хорошо, наша задача про разницу в днях, решается красиво благодаря классу **java.time.temporal.ChronoUnit**:
```java
LocalDate bornDate = LocalDate.of(1951,05,26);
LocalDate deathDate = LocalDate.of(2012,07,23);
int daysCount = (int)ChronoUnit.DAYS.between(bornDate , deathDate);
System.out.println("Days: " + daysCount);
```
Подробнее про DateTime Api: [Introduction to the Java 8 Date/Time API](http://www.baeldung.com/java-8-date-time-intro).

## Сторонние библиотеки
До Java 8 активно использовалась библиотека Joda Time.
Пример использования: [Joda-Time Tutorial](http://www.softwaregeek.net/2012/12/joda-time-tutorial_7.html).

## Дополнительно
[Java и время: часть I](https://habrahabr.ru/post/274811/)

[Java и время: часть II](https://habrahabr.ru/post/274905/)

