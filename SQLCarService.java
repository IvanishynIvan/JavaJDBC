import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клас для роботи з MySQL
 *
 * @author Illya Matsuta
 * @version 1.0
 */
public class SQLCarService {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "root";
    private static final String comma = ",";
    private static final String symbol1 = "'";
    private static final char symbol2 = '"';
    private String carnumber = "";
    private String marka = "";
    private String probig = "";
    private String maister = "";
    private String price = "";
    private static Statement stmt;
    private static Connection con;
    private List<CarServiceChild> crList;
    private List<String> dateTime;
    private List<String> errorText;

    /**
     * Підключення до бази даних
     */
    public Connection connectToDB(String db) throws SQLException, ClassNotFoundException {
        String DB_URL = URL + db + "?serverTimezone=Europe/Kiev";
        Class.forName(JDBC_DRIVER);
        con = DriverManager.getConnection(DB_URL, USER, PASS);
        return con;
    }
    /**
     * Створення таблиці CarService у базі даних
     */
    public void createCarServiceTable() throws SQLException {
        String deleteTable = "DROP TABLE IF EXISTS carservicechild";
        String createTable = "CREATE TABLE IF NOT EXISTS carservicechild (\n" +
                "  `carnumber` INT NOT NULL,\n" +
                "  `marka` VARCHAR(45) NOT NULL,\n" +
                "  `probig` INT NOT NULL,\n" +
                "  `maister` VARCHAR(45) NOT NULL,\n" +
                "  `price` INT NOT NULL,\n" +
                "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  UNIQUE INDEX `carnumber_UNIQUE` (`carnumber` ASC));";
        stmt = con.createStatement();
        stmt.executeUpdate(deleteTable);
        stmt.executeUpdate(createTable);
    }
    /**
     * Створення таблиці Log у базі даних
     */
    public void createLogTable() throws SQLException {
        String deleteTable = "DROP TABLE IF EXISTS Log";
        String createTable = "CREATE TABLE IF NOT EXISTS Log (\n" +
                "  `DateTime` DATETIME NOT NULL,\n" +
                "  `Text` LONGTEXT NOT NULL,\n" +
                "  PRIMARY KEY (`DATETIME`))";
        stmt = con.createStatement();
        stmt.executeUpdate(deleteTable);
        stmt.executeUpdate(createTable);
    }
    /**
     * Команда запису об'єкта у таблицю CarService
     */
    public void data(CarServiceChild cr) throws SQLException {
        carnumber = Integer.toString(cr.getNumberofcar());
        marka = cr.getMarka();
        probig = Integer.toString(cr.getProbig());
        maister = cr.getMaister();
        price = Integer.toString(cr.getPrice());
        String insert = "INSERT INTO carservicechild(carnumber,marka,probig,maister,price) VALUES(" + symbol1+
                carnumber + symbol1 + comma + symbol1 + marka + symbol1 + comma + symbol1 + probig + symbol1 +
                comma + symbol1 + maister + symbol1 + comma + symbol1+ price + symbol1 + ");";
        stmt = con.createStatement();
        stmt.executeUpdate(insert);
    }
    /**
     * Загрузка об'єктів до бази даних
     */
    public void loadDataToDB(List<CarServiceChild> bcList) throws SQLException {
        CarServiceChild cr;
        for (int i = 0; i < bcList.size(); i++) {
            cr = bcList.get(i);
            data(cr);
        }
    }
    /**
     * Вигрузка об'єктів таблиці CarService з бази даних
     */
    public void loadFromCarServiceTable() throws Exception, SQLException {
        String MIN = "SELECT MIN(id) FROM carservicechild;";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(MIN);
        int min = 0;
        if (rs.next()) {
            min = rs.getInt(1);
        }
        String MAX = "SELECT MAX(id) FROM carservicechild;";
        stmt = con.createStatement();
        rs = stmt.executeQuery(MAX);
        int max = 0;
        if (rs.next()) {
            max = rs.getInt(1);
        }
        crList = new ArrayList<CarServiceChild>();
        for (int i = min; i <= max; i++) {
            String id = Integer.toString(i);
            String SELECT = "SELECT * FROM carservicechild WHERE id=" + id + ";";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SELECT);
            while (rs.next()) {
                CarServiceChild cr = new CarServiceChild();
                cr.setNumberofCar(rs.getInt("carnumber"));
                cr.setMarka(rs.getString("marka"));
                cr.setProbig(rs.getInt("probig"));
                cr.setMaister(rs.getString("maister"));
                cr.setPrice(rs.getInt("price"));
                cr.setID(rs.getInt("id"));
                crList.add(cr);
            }
        }
    }
    /**
     * Вигрузка об'єктів таблиці Log з бази даних
     */
    public void loadFromLogTable() throws SQLException {
        String COUNT = "SELECT COUNT(*) FROM Log";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(COUNT);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        dateTime = new ArrayList<>();
        errorText = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String OFFSET = Integer.toString(i);
            String select = "SELECT * FROM Log ORDER BY DATETIME DESC LIMIT 1 OFFSET " + OFFSET + ";";
            stmt = con.createStatement();
            rs = stmt.executeQuery(select);
            while (rs.next()) {
                dateTime.add(rs.getString(1));
                errorText.add(rs.getString(2));
            }
        }
    }
    /**
     * Повернення об'єкта з таблиці CarService з бази даних за індексом
     */
    public CarServiceChild getCarService(int index) {
        return crList.get(index);
    }
    /**
     * Повернення кількості об'єктів в таблиці CarService
     */
    public int getCarServiceCount() throws SQLException {
        String COUNT = "SELECT COUNT(*) FROM carservicechild;";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(COUNT);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }
    /**
     * Повернення значення DateTime за індексом
     */
    public String getDateTime(int index) {
        return dateTime.get(index);
    }
    /**
     * Повернення значення ErrorText за індексом
     */
    public String getErrorText(int index) {
        return errorText.get(index);
    }
    /**
     * Повернення кількості об'єктів таблиці Log
     */
    public int getLogCount() {
        return dateTime.size();
    }
    /**
     * Вставка об'єкта в таблицю CarService
     */
    public void insertIntoCarServiceTable(CarServiceChild b) throws SQLException {
        data(b);
        String MAX = "SELECT MAX(id) FROM carservicechild;";
        stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(MAX);
        if (rs.next()) {
            b.setID(rs.getInt(1));
        }
        crList.add(b);
    }
    /**
     * Модифікація об'єкта в таблиці CarService
     */
    public void update(CarServiceChild b) throws SQLException {
        for (CarServiceChild currentCarService : crList) {
            if (currentCarService.getId() == b.getId()) {
                carnumber = "carnumber = " + symbol1 + b.getNumberofcar() + symbol1;
                marka = "marka = " + symbol1 + b.getMarka() + symbol1;
                probig = "probig = " + symbol1 +  b.getProbig() + symbol1;
                maister = "maister = " + symbol1 + b.getMaister() +symbol1;
                price = "price = " +symbol1 + b.getPrice() + symbol1;
                String update = "UPDATE carservicechild SET " + carnumber + comma + marka + comma + probig + comma
                        + maister + comma + price +" WHERE id = " + b.getId() + ";";
                stmt = con.createStatement();
                stmt.executeUpdate(update);
            }
        }
    }
    /**
     * Видалення об'єкта з таблиці CarService
     */
    public void delete(CarServiceChild b) throws SQLException {
        for (CarServiceChild currentCarService : crList) {
            if (currentCarService.getId() == b.getId()) {
                crList.remove(currentCarService);
                String delete = "DELETE FROM carservicechild WHERE id = " + Integer.toString(b.getId()) + ";";
                stmt = con.createStatement();
                stmt.executeUpdate(delete);
                break;
            }
        }
    }
    /**
     * Видалення всіх об'єктів з таблиці CarService
     */
    public void deleteAll() throws SQLException {
        String delete = "DELETE FROM carservicechild";
        stmt = con.createStatement();
        stmt.executeUpdate(delete);
        crList.clear();
    }
    /**
     * Вставка об'єкта в таблицю Log
     */
    public void insertIntoLogTable(String error) throws SQLException {
        String insert = "INSERT INTO Log VALUES(NOW()," + symbol2 + error + symbol2 + ");";
        stmt = con.createStatement();
        stmt.executeUpdate(insert);
    }
}
