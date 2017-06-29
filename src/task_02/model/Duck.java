package task_02.model;

import java.util.logging.Logger;

import task_02.behavior.FlyBehavior;

/**
 * Утка обыкновенная. Обладает умением.
 * <p>Умением может быть, например "Летать".<br>
 * "Не летать" может быть проявлением умения "летать".
 * 
 * @author veselroger
 */
public class Duck {
	private static final Logger LOG = Logger.getLogger(Duck.class.getSimpleName());

	private FlyBehavior flyBehavior;
	private int flightSpeed;

	public Duck(FlyBehavior behavior) {
		LOG.info("Родилась утка с умением:" + behavior.getClass().getSimpleName());
		this.flyBehavior = behavior;
	}

	/**
	 * Указать скорость полёта утки
	 * 
	 * @param speed Скорость в утиных единицах скорости
	 */
	public void setSpeed(int speed) {
		this.flightSpeed = speed;
	}

	public int getSpeed() {
		return this.flightSpeed;
	}
}
