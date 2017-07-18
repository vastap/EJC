package ru.github.vastap;

import ru.github.vastap.model.field.Coordinate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utils methods for the game.
 */
public class Utils {

	/**
	 * Get game properties from configuration file
	 * @return Game properties set
	 */
	public static Properties getProperties() {
		Properties props = new Properties();
		try (InputStream is = Utils.class.getResourceAsStream("/conf.properties")) {
			props.load(is);
		} catch (IOException e) {
			throw new IllegalStateException("Can't read configuration from file");
		}
		return props;
	}

	/**
	 * The parser for coordinates from string
	 *
	 * @param data String with coordinates. Format: "CoordinateX CoordinateY"
	 * @return Object representing coordinates
	 */
	public static Coordinate inputParser(String data) {
		String[] str = data.split(" ");
		if (str.length != 2) {
			return null;
		}
		if (str[0].length() > 1 || !Character.isDigit(str[0].charAt(0))) {
			return null;
		}
		if (str[1].length() > 1 || !Character.isDigit(str[1].charAt(0))) {
			return null;
		}
		return new Coordinate(Integer.valueOf(str[0]), Integer.valueOf(str[1]));
	}

}