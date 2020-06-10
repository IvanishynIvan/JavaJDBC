package sample;
/** 
 * Клас автосервіс з параметрами <b>numberofcar</b>, <b>marka</b>,<b>probig</b>,<b>maister</b>,<b>price</b>.
 * @autor Іван Іванішин
 * @version 1.0
*/

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.*;

public class CarService {//implements Comparable<CarService> {
	/**
     * Атрибут - номер автомобіля
     */
    private IntegerProperty numberofcar = new SimpleIntegerProperty(this, "numberofcar", -1);
    /**
     * Атрибут - марка автомобіля
     */
    //protected String marka;
    private StringProperty marka = new SimpleStringProperty(this, "marka", "");
    /**
     * Атрибут - пробіг автомобіля
     */
    private IntegerProperty probig = new SimpleIntegerProperty(this, "probig", -1);
    /**
     * Атрибут - майстер, який виконував ремонт
     */
    private StringProperty maister = new SimpleStringProperty(this, "maister", "");
    /**
     * Атрибут - вартість ремонту
     */
    private IntegerProperty price = new SimpleIntegerProperty(this, "price", -1);
  
    public static final String[] title = {"Numberofcar", "Marka", "Probig", "Maister", "Price"};
    public static int[] ColINDX = {0, 1, 2, 3, 4};
    String date = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").format(new Date());
    /**
     * Функція - індексація стовпців
     */
    public static void IndexCols(String fLine) throws Exception {
        if (fLine == null) {
            throw new Exception("File is empty!");
        }
        String pNames[] = fLine.split("\t");
        if (pNames.length != title.length) {
            throw new Exception("Number of colums is wrong: "+pNames.length + " insead of "+ title.length);
        }
        for (int i = 0; i < title.length; i++) {
            int j = pNames.length - 1;
            for (; j >= 0; j--) {
                if (title[i].equalsIgnoreCase(pNames[j])) {
                    ColINDX[i] = j;
                    break;
                }
                if (j == -1) {
                    throw new Exception("no col " + title[i]);
                }
            }
        }
    }
    /** 
     * Конструктор - створення нового обєкта з визначеними значенями
     * @param Data - інформація
     * @see CarService#CarService()
     */
    public CarService(String Data) throws Exception {
        String cols[] = Data.split("\t");
        int colNo = -1;
        setNumberofCar(cols[ColINDX[++colNo]]);
        setMarka(cols[ColINDX[++colNo]]);
        setProbig(cols[ColINDX[++colNo]]);
        setMaister(cols[ColINDX[++colNo]]);
        setPrice(cols[ColINDX[++colNo]]);
    }

    /**
     * Конструктор по-замовчуванню.
     */
    public CarService() {
    }
    /**
     * Конструктор з параметрами.
     */
    public CarService(IntegerProperty numberofcar, StringProperty marka, 
    		IntegerProperty probig, StringProperty maister, IntegerProperty price) {
        this.numberofcar = numberofcar;
        this.marka = marka;
        this.probig = probig;
        this.maister = maister;
        this.price = price;
    }
    /**
     * Сетер поля {@link CarService#numberofcar}.
     */
    
    public void setNumberofCar(int numberofcar) {
        this.numberofcar.set(numberofcar);
    } 
    /**
     * Сетер поля numberofcar для String
     */
    public void setNumberofCar(String numberofcar)  throws Exception
    {
        try
        {
            this.setNumberofCar(Integer.parseInt(numberofcar));
        }
        catch(NumberFormatException exception)	
        {
            throw new Exception(date + " :  Incorrect CarServiceNumb\n");
        }
    }
    /**
     * Сетер поля {@link CarService#marka}.
     */
    
    public void setMarka(String marka) {
    	if (marka.isEmpty()) throw new NullPointerException("Data is empty");
        this.marka.set(marka);
    }
    
    /**
     * Сетер поля {@link CarService#probig}.
     */
    public void setProbig(int probig) {
        this.probig.set(probig);
    }
    /**
     * Сетер поля probig для String
     */
    public void setProbig(String probig)  throws Exception
    {
        try
        {
            this.setProbig(Integer.parseInt(probig));
        }
        catch(NumberFormatException exception)
        {
            throw new Exception(date + " :  Incorrect Probig\n");
        }
    }
    /**
     * Сетер поля {@link CarService#maister}.
     */
    public void setMaister(String maister) {
    	if (maister.isEmpty()) throw new NullPointerException("Data is empty");
        this.maister.set(maister);
    }
    /**
     * Сетер поля {@link CarService#price}.
     */
    public void setPrice(int price) {
        this.price.set(price);
    }
    /**
     * Сетер поля price для String
     */
    public void setPrice(String price)  throws Exception
    {
        try
        {
            this.setPrice(Integer.parseInt(price));
        }
        catch(NumberFormatException exception)
        {
            throw new Exception(date +" :  Incorrect Price\n");
        }
    }
    
    /**
     * Гетер поля {@link CarService#numberofcar}
     */
    public int getNumberofcar() {
        return numberofcar.get();
    }
    public IntegerProperty numberofcarProperty() {
        return numberofcar;
    }
    
    /**
     * Гетер поля {@link CarService#marka}
     */
    
    public String getMarka() {
        return marka.get();
    }
    public StringProperty markaProperty() {
        return marka;
    }
    
    /**
     * Гетер поля {@link CarService#probig}
     */
    public int getProbig() {
        return probig.get();
    }
    public IntegerProperty probigProperty() {
        return probig;
    }
    
    /**
     * Гетер поля {@link CarService#maister}
     */
    
    public String getMaister() {
        return maister.get();
    }
    public StringProperty maisterProperty() {
        return maister;
    }
   
    /**
     * Гетер поля {@link CarService#price}
     */
    
    public int getPrice() {
        return price.get();
    }
    public IntegerProperty priceProperty() {
        return price;
    }
    
    /**
     * Функція з інформацією про обєкт класа CarService
     */
    public String toString() {
    	return "Number of car: " + getNumberofcar() + " | Marka: " + getMarka() +
    			" | Probig: " + getProbig() + "km" + " | Maister: " + getMaister() + " | Price: " 
    			+ getPrice() +"$";
    
    }
    /**
     * Функція з інформацією про обєкт класа CarService у форматі Json
     */
    public String toJson() {
        String s = "   \"" + "Number of car" + "\": \"" + getNumberofcar() + "\",\n"
                + "   \"" + "Marka" + "\": \"" + getMarka() + "\",\n"
                + "   \"" + "Probig" + "\": \"" + getProbig() + "\",\n"
                + "   \"" + "Maister" + "\": \"" + getMaister() + "\",\n"
                + "   \"" + "Price" + "\": \"" + getPrice() + "\",\n";
        return s;
    }
    /**
     * Порівняння двух обєктів класа CarServiceChild за пробігом
     * @param carservice2 обєкт класа CarServiceChild
     */
    /*@Override
   	public int compareTo(CarService carservice2) {
        return probig - carservice2.probig;
    }*/

}
