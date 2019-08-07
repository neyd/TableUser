package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Db {
    private static Connection conn = null;
    private static String dbName;
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String createString = "CREATE TABLE users (NAME VARCHAR(32) NOT NULL, ADDRESS VARCHAR(50) NOT NULL)";
//    private Users users = new Users();
    private static ObservableList<User> items =  FXCollections.observableArrayList ();

    public Db(){
        createToDb();
    }

    private static void createToDb() {
        //    Class.forName(driver);
        String connectionURL = "jdbc:derby:" + dbName + ";create=true";
        try {
            conn = DriverManager.getConnection(connectionURL);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
    }

    public void insertToDb(String usernane, String address) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            System.out.println(e);;
        }
        try {
            stmt.executeUpdate(createString);
        } catch (SQLException e) {
            System.out.println(e);
        }

        PreparedStatement psInsert = null;
        try {
            psInsert = conn
                    .prepareStatement("insert into users values (?,?)");
        } catch (SQLException e) {
            System.out.println(e);;
        }

        try {
            psInsert.setString(1, usernane);
            psInsert.setString(2, address);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            psInsert.executeUpdate();
//            users.addUser(usernane,address);
        } catch (SQLException e) {
            System.out.println(e);;
        }
    }

    public ObservableList<User> getAllUsers() throws SQLException {
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt2.executeQuery("select * from users");
        System.out.println("Addressed present in your Address Book\n\n");
        int num = 0;

        while (rs.next()) {
            items.add(new User(rs.getString(1),rs.getString(2)));
            System.out.println(++num + ": Name: " + rs.getString(1) + "\n Address"
                    + rs.getString(2));
        }
        rs.close();
        return items;
    }
}
