# Lesson 07: Hashtable, HashMap, HashSet

## Вступление
Существует структура данных, основанная на использовании HashCode для определения места размещения элемента. Это позволяет достичь быстрой вставки и получения элемента.

Начинать стоить, как мне кажется, со статьи: "[Введение в хеш-таблицы](https://bitsofmind.wordpress.com/2008/07/28/introduction_in_hash_tables/amp/)".
В Java есть своя реализация: **java.util.Hashtable**. Является имплементацией интерфейса **Map**, что делает хэштаблицы частью Collection Framework, но не Сollection.
Важно, что методы работы с хэш таблицей синхронизированы (**synchronized**), поэтому требуют некоторый объём ресурсов на поддержание синхронизации.

Hashtable состоит из массива Entry: ```Entry tab[]```, называемых корзинами (bucket).
На основе hashCode вычисляется индекс корзины:
```java
int hash = hash(key);
int index = (hash & 0x7FFFFFFF) % tab.length;
```
По умолчанию, содержит 11 корзин.

Так же имеет параметр **load factor** - коэффициент загрузки.
На основе него вычисляется **threshold** - максимальное число элементов, после которого происходит увеличение внутреннего массива корзин и перераспределение элементов:
```oldCapacity << 1) + 1```, т.е. размер становится в 2 раза больше.
Вычисляется **threshold** так: ```сapacity * loadFactor```
Т.е. при стандартном capacity 11 мы увеличим размер при 11*0.75, т.е. при 8 элементе.

## HashMap
Альтернативная структура данных - HashMap.
Она похожа на Hashtable, но её методы не синхронизированны, поэтому работает она быстрее.
Далее стоит ознакомиться с супер статьёй "[Структуры данных в картинках. HashMap](https://habrahabr.ru/post/128017/)". Стоит отметить, что это справедливо только для Java 7, но об этом чуть позже.
Изначальный размер массива корзин: ```1 << 4; // aka 16```, т.е. 2^4 или 16.
При достижении границы, определённой так же как и в Hashtable (т.е. Capacity * loadFactor) размер массива корзин так же увеличивается в 2 раза.

## Коллизии
Не случайно используется в Hashtable и HashMap понятие Entry. Каждый Entry имеет ссылку на следующий элемент. Что за ссылка и зачем?
В процессе работы с HashMap могуть возникать коллизии - когда для разных элементов совпадает хэш, но разный equals. Чтобы решить коллизию, такие элементы собираются в цепочки.
И тут в Java8 сделали оптимизацию, про которую нужно знать.
В Java8 появился параметр TREEIFY_THRESHOLD, которвый равен 8.
Если корзины Node образуют цепочку в 8 элементов, то такая цепочка будет заменена со связанного списка на дерево. Это обеспечит более быстрый доступ, хоть и более медленное добавление. Причём это изменение коснулось только HashMap, а не Hashtable.

## Варианты HashMap
- LinkedHashMap:
[Структуры данных в картинках. LinkedHashMap](https://habrahabr.ru/post/129037/).
Данная структура поддерживает двухсвязный список и, следовательно, управление порядком добавления
- IdentityHashMap
[Коллекции. IdentityHashMap](http://proselyte.net/tutorials/java-core/collections-framework/identity-hashmap/).
Сравнение только по System.identityHashCode, без участия hashCode и equals.
- ConcurrentHashMap
[Как работает ConcurrentHashMap](https://habrahabr.ru/post/132884/)
HashMap для работы в многопоточном окружении. Блокируется только корзина, а не весь HashMap.
- WeakHashMap
[WeakHashMap](http://www.quizful.net/question/R8HQemisvZG9)
[What is a WeakHashMap and when to use it?}](https://stackoverflow.com/questions/5511279/what-is-a-weakhashmap-and-when-to-use-it)

## Дополнительно
[Java collection framework - вопросы и ответы](https://jsehelper.blogspot.ru/2016/01/java-collections-framework-3.html)

## HashSet
На основе HashMap строится и HashSet - структура данных, в которой ключи уникальны.
Соответственно, их уникальность будет вычислена по HashCode по той же схеме, что и для HashMap.
Может возникнуть вопрос, а что же делать со значением? И тут всё просто:
```java
// Dummy value to associate with an Object in the backing Map
private static final Object PRESENT = new Object();
```
То есть вместо значение в такой Map хранится один и тот же Object - заглушка.
По сути - HashSet - это обёртка над HashMap, которая позволяет работать с Map по контракту Set, что делает её Collection.