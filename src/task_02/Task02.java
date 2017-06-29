package task_02;

import task_02.fortune.Fortuna;
import task_02.model.Duck;
import task_02.model.Player;

public class Task02 {
	
	
	public static void main(String[] args) {
		//Inline для вывода из простейшего логгера
		System.setProperty("java.util.logging.SimpleFormatter.format", 
	            "%1$tF %1$tT %4$s %2$s %5$s%6$s%n");
		//1 - Подготовка к игре
		Duck[] ducks = new Duck[5];
		initDucks(ducks);
		//2 - Сама игра (раунды)
		Player player = new Player();
		int roundCount=10;
		for (int i=0;i<roundCount;i++){
			
		}
		
	}
	
	/** Инициализируем всех уток и их поведение */
	private static void initDucks(Duck[] ducks){
		for (int i=0;i<ducks.length;i++){
			ducks[i]=Fortuna.getDuck();
			Fortuna.setDuckSpeed(ducks[i], 10, 100);
		}
	}
}
