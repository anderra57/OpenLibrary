package ehu.isad.controllers.ui;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.controllers.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    private Liburuak liburuak;
    private ZerbitzuKud zerbitzuKud;

    public void setMainApp(Liburuak pLiburuak) {
        liburuak = pLiburuak;
    }

    @FXML
    private ComboBox<Book> combo_lib_haut;

    @FXML
    private Button button_ikusi;

    @FXML
    private TextField tf_isbn;

    @FXML
    private TextField tf_tit;

    @FXML
    public void onClick(ActionEvent actionEvent) throws IOException {

        Button click = (Button) actionEvent.getSource();
        Book book = (Book)combo_lib_haut.getValue();
        boolean liburuaSartu = (!tf_isbn.getText().equals("") && !tf_tit.getText().equals(""));
        if (liburuaSartu){book = new Book(tf_tit.getText(), tf_isbn.getText());} // datuak sartu dizkiogu?

        if (click==button_ikusi){ // ikusi botoia
            if (!zerbitzuKud.bilatuLiburua(book)){ // liburua existitzen da?
                if (liburuaSartu) { sartu(book); } // datuak sartu badizkiogu, liburua gehitu
                try { zerbitzuKud.xehetasunakGehitu(book); } // jada liburua baldin bazegoen ez da ifera sartuko, bietarako xehetasunak deskargatu
                catch (Exception e) { if (liburuaSartu){ ezabatu(book); } } // liburua sartzean arazoa baldin badago, sortutako liburu berria ezabatuko da
            }
            liburuak.xehetasunakErakutsi(book.getISBN());
        }

        else{ // ezabatu botoia
            ezabatu(book);
        }

    }

    private void sartu(Book book){
        zerbitzuKud.gehituLiburua(book);
        combo_lib_haut.getItems().add(book);
        tf_isbn.setText("");
        tf_tit.setText("");
    }

    private void ezabatu(Book book){
        zerbitzuKud.ezabatuLiburua(book);
        combo_lib_haut.getItems().remove(book);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zerbitzuKud=ZerbitzuKud.getInstance();
        List<Book> itemsL = zerbitzuKud.lortuIzenburuak();
        ObservableList<Book> items = FXCollections.observableArrayList(itemsL);
        combo_lib_haut.setItems(items);
    }


}
