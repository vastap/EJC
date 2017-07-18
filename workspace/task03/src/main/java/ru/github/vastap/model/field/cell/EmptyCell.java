package ru.github.vastap.model.field.cell;

/**
 * Empty cell after strike with no result
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
		System.out.println("Miss");
	}

}