package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AukeratuController implements Initializable {

    private Main mainApp;

    public void setMainApp(Main main) {
        mainApp = main;
    }

    @FXML
    private ComboBox combo_lib_haut;

    @FXML
    public void onClick(ActionEvent actionEvent) throws IOException {
        System.out.println(combo_lib_haut.getValue());
        mainApp.liburuaErakutsi();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> items = Main.getList();
        combo_lib_haut.setItems(items);
    }


}
