package ehu.isad.controllers.ui;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.controllers.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    private Liburuak liburuak;
    private Book book;
    private ZerbitzuKud zerbitzuKud;

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
        //ObservableList<Book> items = Liburuak.getList();
/**/
        zerbitzuKud=ZerbitzuKud.getInstance();
        List<Book> itemsL = zerbitzuKud.lortuIzenburuak();
        System.out.println(itemsL.toString());
        ObservableList<Book> items = FXCollections.observableArrayList(itemsL);
        combo_lib_haut.setItems(items);
    }


}
