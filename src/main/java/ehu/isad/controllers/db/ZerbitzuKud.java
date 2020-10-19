package ehu.isad.controllers.db;

import ehu.isad.Book;
import ehu.isad.Details;
import ehu.isad.utils.Sarea;
import ehu.isad.utils.Utils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }

    public List<Book> lortuIzenburuak() {

        String query = "select isbn,izenburulaburra from liburuak";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Book> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String iz = rs.getString("izenburulaburra");
                Book b = new Book(iz,isbn);
                emaitza.add(b);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public boolean bilatuLiburua(Book book) {
        String isbn = book.getISBN();
        String query = "select * from liburuak where isbn='" + isbn + "' AND orrikop IS NOT NULL;";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        Boolean b=true;
        try {
            b=rs.next();
        }
        catch(SQLException exception){
            exception.printStackTrace();
        }
        return b;
    }

    public void xehetasunakGehitu(Book b) throws IOException {

        Sarea sarea = Sarea.getSarea();
        Book book = sarea.lortuDatuak(b);

        String path = datuakGehitu(book,b.getISBN());

        Image image = sarea.lortuIrudia(book.getThumbnail_url());
        irudiaGehitu(image,path);

    }

    private String datuakGehitu(Book book, String isbn) {

        Details details = book.getDetails();

        String izenburua = details.getTitle();
        String publishers1 = details.getPublishers();
        String publishers = publishers1.replace("\'", "\'\'");
        String orrikop = details.getNumber_of_pages();
        String path = isbn + ".jpg";

        String query = "UPDATE liburuak SET izenburua='"+izenburua+"', publishers='"+publishers+"', orrikop='"+orrikop+"', path='"+path+"' WHERE isbn='"+isbn+"';";
        System.out.println(query);
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        return path;
    }

    private void irudiaGehitu(Image image, String path) throws IOException {

        Properties properties = Utils.lortuEzarpenak();
        String format = "jpg" ;
        String pathToImages = properties.getProperty("pathToImages");
        String filepath = pathToImages+"/"+path;
        File file = new File(filepath) ;
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), format, file);

    }

    public List<String> lortuDatuak(String isbn) {
        String query = "select izenburua,publishers,orrikop,path from liburuak where isbn='"+isbn+"';";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String iz = rs.getString("izenburua");
                String pub = rs.getString("publishers");
                String orri = rs.getString("orrikop");
                String path = rs.getString("path");

                emaitza.add(iz);
                emaitza.add(pub);
                emaitza.add(orri);
                emaitza.add(path);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void ezabatuLiburua(Book book) {
        String isbn = book.getISBN();
        String query = "delete from liburuak where isbn='" + isbn + "';";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public void gehituLiburua(Book book) {
        String izen = book.getIzenburuLaburra();
        String isbn = book.getISBN();
        String query = "insert into liburuak(isbn,izenburulaburra) values ('"+isbn+"','"+izen+"')";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }
}
