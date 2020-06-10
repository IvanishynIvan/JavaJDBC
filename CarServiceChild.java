/** 
 * Клас нащадок класа автосервіса з параметрами <b>ID</b>, <b>id</b>.
 * @autor Іван Іванішин
 * @version 1.0
*/
public class CarServiceChild extends CarService 
{
    private static int ID = 0;
    private int id = 0;
    /**
     * Конструктор з параметрами.
     */
    public CarServiceChild(String line) throws Exception {
        super(line);
        id = ++ID;
    }
    /**
     * Гетер поля id
     */
    public int getId() {
        return id;
    }
    /**
     * Функція з інформацією про ID обєкт класа CarService
     */
    @Override
    public String toString() {
        return "ID: " + id + " " + super.toString();
    }
    /**
     * Функція з інформацією про ID обєкт класа CarService для Json
     */
    public String toJson(){
        return "   \"ID\": "+id+",\n"+super.toJson();
    }
    
}
