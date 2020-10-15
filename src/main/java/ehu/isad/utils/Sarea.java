package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.Book;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {

    private static Sarea nSarea = null;

    public static Sarea getSarea(){
        if (nSarea==null)
            nSarea=new Sarea();
        return nSarea;
    }

    private Book readFromUrl(String isbn) throws IOException {
        URL openlibrary = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+isbn+"&jscmd=details&format=json");
        URLConnection yc = openlibrary.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine = in.readLine();
        in.close();
        String[] zatiak = inputLine.split("ISBN:"+isbn+"\":");
        inputLine = zatiak[1].substring(0, zatiak[1].length()-1);
        Gson gson = new Gson();
        return gson.fromJson(inputLine, Book.class);
    }


    private Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }

    public Image lortuIrudia(String url) throws IOException {
        String smallURL = url;
        String mediumURL = smallURL.replaceAll("-S.","-M.");
        return createImage(mediumURL);
    }


    public Book lortuDatuak(Book book) throws IOException {
        return readFromUrl(book.getISBN());
    }
}
