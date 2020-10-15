package ehu.isad.controllers;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.utils.Sarea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class XehetasunakKud implements Initializable {

    private Liburuak liburuak;

    public void setMainApp(Liburuak pLiburuak) {
        liburuak = pLiburuak;
    }

    @FXML
    private Label lbl_lib_izenb;
    @FXML
    private Label lbl_lib_arg;
    @FXML
    private Label lbl_lib_orri;
    @FXML
    private ImageView img_lib;

    @FXML
    public void onClick(ActionEvent actionEvent) throws IOException {
        liburuak.liburuakErakutsi();
    }

    public void hasieratu(Book book) throws IOException {
        Sarea s = Sarea.getSarea();

        // liburua APIan bilatu bere datu guztiak lortzeko eta labeletan idatzi
        Book datudunLiburua = s.lortuDatuak(book);
        lbl_lib_izenb.setText(datudunLiburua.getDetails().getTitle());
        lbl_lib_arg.setText(datudunLiburua.getDetails().getPublishers());
        lbl_lib_orri.setText(datudunLiburua.getDetails().getNumber_of_pages());

        // irudia kokatu
        Image irudia = s.lortuIrudia(datudunLiburua.getThumbnail_url());
        img_lib.setImage(irudia);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
