package ru.github.vastap;

import ru.github.vastap.fortune.Fortuna;
import ru.github.vastap.model.Duck;
import ru.github.vastap.model.Player;

import java.util.Scanner;

/**
 * Представляет из себя непосредственно игровой процесс
 */
public class Play {
	private int duckCount;
	private int roundCount;
	private Player currentPlayer;
	private Duck[] ducks;
	/** Размер ставки */
	private final int BET_SIZE=100;

	public Play(int duckCount, int roundCount, Player player){
		this.duckCount = duckCount;
		this.roundCount = roundCount;
		this.currentPlayer = player;

		ducks = new Duck[duckCount];
		for (int i=0;i<duckCount;i++){
			ducks[i]=Fortuna.getRandomDuck();
		}
	}

	/**
	 * Изменить утиную скорость для переданного набора уток
	 */
	private void initializeNewSpeed(){
		for(Duck duck : ducks){
			Fortuna.setDuckSpeed(duck,Duck.getMinSpeed(),Duck.getMaxSpeed());
		}
	}

	/**
	 * Обработать данные текущего раунда.
	 * <p>Определяется утка победитель, раздаются деньги
	 * @param selection Выбранный пользователем номер утки (или выбранный автоматически, если пользователь ошибся)
	 */
	private void processRound(String selection){
		int maxResult=0;
		int duckWinner=0;
		int current=0;

		for (int i=0;i<this.duckCount;i++){
			current = ducks[i].fly();
			if (current > maxResult){
				maxResult = current;
				duckWinner=i;
			}
		}
		System.out.println("Утка победить: "+duckWinner);

		if (selection.equals(String.valueOf(duckWinner))){
			System.out.println("Невероятно, но Вы выйграли этот раунд!");
			currentPlayer.deposit(BET_SIZE*2);
		}else{
			System.out.println("Очень жаль, но этот раунд Вы проиграли.");
			currentPlayer.withdraw(BET_SIZE);
		}
	}

	/** Начать игру */
	public void begin(){
		Scanner scanner = new Scanner(System.in);
		for (int i=1;i<this.roundCount+1;i++){
			System.out.printf("[Раунд %d] Пожалуйста, укажите номер утки, от 1 до 5 :\t", i);
			String selection = scanner.nextLine();
			if (!selection.matches("\\d") || Integer.valueOf(selection)>this.duckCount){
				selection = String.valueOf( Fortuna.getDuckNumber(this.duckCount) );
				System.out.println("Вы указали неверный номер. Поэтому мы выбрали за вас утку номер "+selection);
			}

			initializeNewSpeed();
			processRound(selection);

			System.out.println("Средств на счету: "+currentPlayer.getMoney());
			if (currentPlayer.getMoney()==0){
				System.out.println("Сожалеем, но вы потратили все деньги. Увидимся в следующий раз");
				break;
			}
		}
		System.out.println("Это была отличная игра, спасибо за участие.");
	}
}
