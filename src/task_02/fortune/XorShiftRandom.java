package task_02.fortune;

/**
 * Генератор случайных чисел на основе алгоритма XorShift.<br>
 * Чем меньше верхняя граница рандома, тем хуже он работает.
 * 
 * <p>Алгоритм XorShift используется в генерации HashCode объектов с Java8 и выше.<br>
 * Данный пример взят из статьи: <a href="http://www.apofig.com/2014/10/random-xorshift.html">Быстрая реализация Random - алгоритм XORShift</a>
 * @author veselroger
 */
public class XorShiftRandom {
    /** Основа для генерации случайного числа */
	private static long rndBase;

    public XorShiftRandom() {
       if (rndBase==0L){
    	   rndBase=System.currentTimeMillis();
       }
    }

    /**
     * Получить случайное число
     * @param max Максимальное значение (исключая это число)
     * @return
     */
    public int nextInt(int max) {
        rndBase ^= (rndBase << 21);
        rndBase ^= (rndBase >>> 35);
        rndBase ^= (rndBase << 4);
        int out = (int) rndBase % max;     
        return (out < 0) ? -out : out;
    }
}

