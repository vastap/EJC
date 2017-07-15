package ru.github.vastap;

import ru.github.vastap.model.field.Coordinate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Вспомогательные утилитные методы. Например, работа с конфигурационным файлом.
 */
public class Utils {

	/**
	 * Получить настройки из файла конфигурации
	 * @return Настройки приложения
	 */
	public static Properties getProperties(){
		Properties props = new Properties();
		try(InputStream is = Utils.class.getResourceAsStream("/conf.properties")){
			props.load(is);
		} catch (IOException e) {
			throw new IllegalStateException("Ошибка чтения конфигурации из файла настроек");
		}
		return props;
	}

	/**
	 * Парсер координаты из полученной от пользователя строки
	 * @param data Введённая пользователем строка
	 * @return Координата ячейки, которую указал пользователь
	 */
	public static Coordinate inputParser(String data){
		String[] str = data.split(" ");
		if (str.length != 2){
			return null;
		}
		if (str[0].length()>1 || !Character.isDigit(str[0].charAt(0))){
			return null;
		}
		if (str[1].length()>1 || !Character.isDigit(str[1].charAt(0))){
			return null;
		}
		return new Coordinate(Integer.valueOf(str[0]), Integer.valueOf(str[1]));
	}

}
