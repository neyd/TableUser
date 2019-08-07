package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public TextField laFieldTextInput1;
    public Label laLabelError1;
    public TableView<User> laMeTableView;
    public TableColumn<User,String> nameColumn;
    public TableColumn<User,String> addressColumn;
    Users users = new Users();
    public TextField laFieldTextInput;
    public ListView laMeListView;
    public Button showMeMainMenu;
    public Label laLabelError;
    public ListView laMenuListView;
    Db db = new Db();
    @FXML
    Button laBtnMy;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        users.initialize();
        initTableView();
    }

    private void initTableView(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));

        // заполняем таблицу данными
        laMeTableView.setItems(users.getAll());
    }

    public void getAllUsers(){
        users.getAll();
    }

    public void addToUsers() {
        String text = laFieldTextInput.getText();
        String text1 = laFieldTextInput1.getText();
        if (text.isEmpty()) {
            laLabelError.setText("Введіть назву");
            return;
        }
        if (text1.isEmpty()) {
            laLabelError.setText("Введіть назву");
            return;
        }

//        users.addUser(text,text1);
        users.addUser(text, text1);
        getAllUsers();
        laFieldTextInput.setText("");
        laFieldTextInput1.setText("");
    }


    public void showMePage(){

    }
}
