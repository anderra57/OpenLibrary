package ehu.isad.controllers.db;

import ehu.isad.Book;
import javafx.beans.Observable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }

    public void ezabatuZerbitzua(String zerb){
        String query = "DELETE FROM zerbitzuak.services WHERE izena='"+zerb+"';";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public void gehituZerbitzua(String zerb){
        String query = "INSERT INTO zerbitzuak.services (izena) VALUES ('"+zerb+"');";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }

    public boolean frogatuZerbitzua(String zerb) {
        String query = "select * from services where izena='" + zerb + "';";
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
                System.out.println(iz + ": "+isbn);
                emaitza.add(b);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }
}
