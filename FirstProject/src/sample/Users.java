package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Users {
    ObservableList<User> usersList = FXCollections.observableArrayList ();
    private Boolean initialize = false;
    Db db = new Db();

    public void initialize(){
        if (initialize){
            return;
        }
        try {
            usersList = db.getAllUsers();
            initialize = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<User> getAll(){
        return usersList;
    }

    public void addUser(String name, String address){
        User user = new User(name,address);
        usersList.add(user);
    }

    public ObservableList<String> getAllToPrint(){
        ObservableList<String> responce = FXCollections.observableArrayList();
        for (final User user : usersList){
            responce.add( user.getName() + "    " + user.getAddress());
        }

        return responce;
    }

}
