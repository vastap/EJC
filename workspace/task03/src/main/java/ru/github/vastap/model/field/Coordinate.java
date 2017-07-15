package ru.github.vastap.model.field;

/**
 * Координата по осям X и Y.
 */
public class Coordinate {
	private int xCoord;
	private int yCoord;

	public Coordinate(int xCoord, int yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}

	/**
	 * Получить значение координаты по оси X
	 * @return
	 */
	public int getX() {
		return this.xCoord;
	}

	/**
	 * Получить значение координаты по оси Y
	 * @return
	 */
	public int getY() {
		return this.yCoord;
	}

	/**
	 * Получить следующую координату перемещаясь в заданном направлении
	 * @param direction Направление получения следующей координаты
	 * @return Координата
	 */
	public Coordinate getNextCoordinate(Direction direction){
		switch(direction) {
			case UP:
				return new Coordinate(xCoord,yCoord-1);
			case DOWN:
				return new Coordinate(xCoord,yCoord+1);
			case LEFT:
				return new Coordinate(xCoord-1,yCoord);
			case RIGHT:
				return new Coordinate(xCoord+1,yCoord);
		}
		throw new IllegalArgumentException("Передано неверное направление");
	}

	@Override
	public String toString() {
		return "Coordinate{" +
				"xCoord=" + xCoord +
				", yCoord=" + yCoord +
				'}';
	}

}
