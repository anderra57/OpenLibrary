package ehu.isad;

import ehu.isad.controllers.ui.LiburuKud;
import ehu.isad.controllers.ui.XehetasunakKud;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Liburuak extends Application {

    private Stage stage;
    private Parent liburuUI;
    private Parent xehetasunakUI;
    private LiburuKud liburuKud;
    private XehetasunakKud xehetasunakKud;
    private Scene liburuScene;
    private Scene xehetasunakScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        hasieratu();

        stage.setTitle("OpenLibrary APIa aztertzen");
        stage.getIcons().add(new Image(Liburuak.class.getResourceAsStream("/book.png")));
        stage.setResizable(false);
        stage.setScene(liburuScene);
        stage.show();
    }

    private void hasieratu() throws IOException {

        FXMLLoader loaderLiburu = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
        liburuUI = (Parent) loaderLiburu.load();
        liburuKud = loaderLiburu.getController();
        liburuKud.setMainApp(this);
        liburuScene = new Scene(liburuUI);

        FXMLLoader loaderXehetasunak = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
        xehetasunakUI = (Parent) loaderXehetasunak.load();
        xehetasunakScene = new Scene(xehetasunakUI);
        xehetasunakKud = loaderXehetasunak.getController();
        xehetasunakKud.setMainApp(this);

    }

    public void xehetasunakErakutsi(Book book) throws IOException {
        xehetasunakKud.hasieratu(book);
        stage.setScene(xehetasunakScene);
        stage.show();
    }

    public void liburuakErakutsi() {
        stage.setScene(liburuScene);
        stage.show();
    }

    public String[] lortuDatuak (){
        return null;
    }

    public static ObservableList<Book> getList() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(
                new Book("Blockchain: Blueprint for a New Economy","9781491920497"),
                new Book("R for Data Science","1491910399"),
                new Book("Fluent Python","1491946008"),
                new Book("Natural Language Processing with PyTorch","1491978236"),
                new Book("Data Algorithms","9781491906187")
        );
        return books;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
