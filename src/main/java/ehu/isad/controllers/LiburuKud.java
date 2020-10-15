package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    private Liburuak liburuak;
    private Book book;

    public void setMainApp(Liburuak pLiburuak) {
        liburuak = pLiburuak;
    }

    public Book getAukeratutakoBook(){
        return book;
    }

    @FXML
    private ComboBox<Book> combo_lib_haut;

    @FXML
    public void onClick(ActionEvent actionEvent) throws IOException {
        book = (Book)combo_lib_haut.getValue();
        liburuak.xehetasunakErakutsi(book);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> items = Liburuak.getList();
        combo_lib_haut.setItems(items);
    }


}
