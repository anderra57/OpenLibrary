package ehu.isad.controllers.ui;

import ehu.isad.Liburuak;
import ehu.isad.controllers.db.ZerbitzuKud;
import ehu.isad.utils.Utils;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class XehetasunakKud implements Initializable {

    private Liburuak liburuak;
    private ZerbitzuKud zerbitzuKud;

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

    public void hasieratu(String isbn) throws IOException {

        // liburuaren datuak lortu datu basetik eta labeletan idatzi
        zerbitzuKud=ZerbitzuKud.getInstance();
        List<String> datuak = zerbitzuKud.lortuDatuak(isbn);

        lbl_lib_izenb.setText(datuak.get(0));
        lbl_lib_arg.setText(datuak.get(1));
        lbl_lib_orri.setText(datuak.get(2));

        // irudia kokatu
        Image irudia = irekiIrudia(datuak.get(3));
        img_lib.setImage(irudia);

    }

    private Image irekiIrudia(String path){
        Properties properties= Utils.lortuEzarpenak();
        String pathToImages = properties.getProperty("pathToImages");
        String filepath = pathToImages+"/"+path;
        File irudia = new File(filepath);
        BufferedImage img = null;
        try {
            img = ImageIO.read(irudia);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return SwingFXUtils.toFXImage(img, null);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
