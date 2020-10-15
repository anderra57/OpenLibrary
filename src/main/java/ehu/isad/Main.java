package ehu.isad;

import ehu.isad.controllers.AukeratuController;
import ehu.isad.controllers.LiburuController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

  private Parent liburuUI;
  private Parent aukeratuUI;

  private Stage stage;

  private AukeratuController aukeratuController;
  private LiburuController liburuController;

  public static ObservableList<Book> getList() {
    ObservableList<Book> books = FXCollections.observableArrayList();
    books.addAll(
            new Book("1491910399", "R for Data Science"),
            new Book("1491946008", "Fluent Python"),
            new Book("9781491906187", "Data Algorithms")
    );
    return books;
  }

  @Override
  public void start(Stage primaryStage) throws Exception{

    stage = primaryStage;

    FXMLLoader loaderAukeratu = new FXMLLoader(getClass().getResource("/aukeratu.fxml"));
    aukeratuUI = (Parent) loaderAukeratu.load();
    aukeratuController = loaderAukeratu.getController();
    aukeratuController.setMainApp(this);



    stage.setTitle("Hello World");
    stage.setScene(new Scene(aukeratuUI, 600, 400));
    stage.show();
  }

  public void liburuaErakutsi() throws IOException {
    FXMLLoader loaderLiburu = new FXMLLoader(getClass().getResource("/liburu.fxml"));
    liburuUI = (Parent) loaderLiburu.load();
    liburuController = loaderLiburu.getController();
    liburuController.setMainApp(this);

    stage.setScene(new Scene(liburuUI));
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
