package ru.github.vastap.model.field.cell;

/**
 * Пустая клетка, по которой был выполнен удар
 */
public class EmptyCell extends FieldCell{

	public EmptyCell() {
		super(null);
	}

	@Override
	public char getRenderSymbol(boolean hide) {
		return '-';
	}

	@Override
	public void processShot() {
		System.out.println("Промах");
	}

}
