package sample;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

/**
 * Клас який використовується для журналювання з параметрами
 * <b>FileName</b>,<b>exc</b>.
 * 
 * @author Іванішин Іван
 * @version 1.0
 */
public class Exception extends RuntimeException {

	static File FileName = null;
	private String exc;

	/**
	 * Конструктор з параметром.
	 */

	public Exception(String message) {
	    super(message);
	}
	
	/**
	 * Функція - записує рядок у файл для логів
	 */
	static public void ErrInfo(String exc) {
		System.out.println(exc);
		if (FileName != null) {
			try {
				FileWriter log = new FileWriter(FileName, true);
				log.write(exc);
				log.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
