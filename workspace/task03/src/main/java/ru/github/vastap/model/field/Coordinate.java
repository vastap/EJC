package ru.github.vastap.model.field;

/**
 * Coordinate with X and Y value.
 */
public class Coordinate {
	private int coordinateX;
	private int coordinateY;

	public Coordinate(int coordinateX, int coordinateY){
		this.coordinateX = coordinateX;
		this.coordinateY = coordinateY;
	}

	/**
	 * Get value of coordinate X
	 * @return
	 */
	public int getX() {
		return this.coordinateX;
	}

	/**
	 * Get value Получить значение координаты по оси Y
	 * @return
	 */
	public int getY() {
		return this.coordinateY;
	}

	/**
	 * Get the next coordinate moving in the specified direction.
	 *
	 * @param direction Direction for calculating a new coordinate
	 * @return Object which represents a coordinate.
	 */
	public Coordinate getNextCoordinate(Direction direction){
		switch(direction) {
			case UP:
				return new Coordinate(coordinateX,coordinateY-1);
			case DOWN:
				return new Coordinate(coordinateX,coordinateY+1);
			case LEFT:
				return new Coordinate(coordinateX-1,coordinateY);
			case RIGHT:
				return new Coordinate(coordinateX+1,coordinateY);
		}
		throw new IllegalArgumentException("Wrong direction.");
	}

	@Override
	public String toString() {
		return "Coordinate{" +
				"x:=" + coordinateX +
				", y:=" + coordinateY +
				'}';
	}

}