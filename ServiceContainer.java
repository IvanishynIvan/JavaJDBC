package sample;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
/** 
 * Клас контейнер з параметрамом <b>container</b>.
 * @autor Іван Іванішин
 * @version 1.0
*/
public class ServiceContainer {
	
	List<CarService> container = new ArrayList<CarService>();
	 private String exceptionInfo = "";	
	 String date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(new Date());
    /**
     * Конструктор по-замовчуванню.
     */
    public ServiceContainer() {}

    public List<CarService> getArray() {
    	return container;
    }
    
    public String getExcInfo() {	
    	return exceptionInfo;
    
    }
    
    /**
     * Читає файл з обєктами CarService i зберігає допустимі значення
     * @param fName назва файла
     * @return 
     * @return 
     * @throws IOException помилка при неправильному типі даних
     */
    public void readFromFile(File fName) throws Exception {
        //створити обєкт "FileReader" для обєкта "File"
        try {
            FileReader fileRead = new FileReader(fName);
            //створити "BufferedReader" для читання рядка по одному
            BufferedReader buffRead = new BufferedReader(fileRead);
            //читати перший рядок
            CarService.IndexCols(buffRead.readLine());
            Exception fileReadingException = null;
            String line;
            int lineNo = 1;
            line = buffRead.readLine();
            while (line != null) {
                try {
                    container.add(new CarService(line));
                    line = buffRead.readLine();
                    lineNo++;
                } catch (Exception exc) {
                    line = buffRead.readLine();
                    fileReadingException = new Exception(date + " :  Incorrect attribute in line " + lineNo + "\n");
                }
            }
            fileRead.close();
            if (fileReadingException != null) {
                throw fileReadingException;
            }
        } catch (IOException exc) {
            throw new Exception(exc.getLocalizedMessage());
        }
    }
    
    /**
     * Функція виводу інформації з контейнера
     */
    public void showAll() {
    	System.out.format("\nAll data available:\n");
        for (int i = 0; i < container.size(); i++) {
            System.out.println(container.get(i).toString());
        }
    }
    /**
     * Запис інформації у файл у форматі Json
     * @param fileOut назва файла
     */
    public void toJson(ObservableList<CarService> observableCarList, File fileOut) throws Exception {
        try {
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(fileOut));
            outWriter.write("[\n");
            int i=0;
            for(CarService CarServices : observableCarList) {
            	String s =" {\n"+ CarServices.toJson()+ " }";
            	if (i < observableCarList.size() - 1) {
                    s = s + ",";
                }
            	i+=1;
                outWriter.write(s);
                outWriter.newLine();
            }
            outWriter.write("]");
            System.out.println(observableCarList.toString());
            outWriter.close();
        } catch (IOException e) {
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "OOPS!", ButtonType.OK);
            ioAlert.setContentText("Sorry. An error has occurred.");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK) {
                ioAlert.close();
            }
    }
}
}