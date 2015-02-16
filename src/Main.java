import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * An example class to show how the load style utility works.
 * 
 * Disclaimer: The code here for the GUI is poorly written (everything is done in this class in
 * code), and is merely to demonstrate the function calls of the load style utility.
 * 
 * @author Jonathan Tseng
 */
public class Main extends Application {
    @Override
    public void start (Stage primaryStage) {
        try {
            primaryStage.setTitle("Object Binding Example Application");
            BorderPane borderPane = new BorderPane();

            SimpleObjectProperty<ObservableList<String>> words = 
                new SimpleObjectProperty<>(FXCollections.observableArrayList());
            ComboBox<String> wordListBox = new ComboBox<>();
            wordListBox.itemsProperty().bind(words);
            BorderPane.setAlignment(wordListBox, Pos.CENTER);
            borderPane.setCenter(wordListBox);

            TextField inputText = new TextField();
            inputText.setOnKeyReleased(e -> {
                if (e.getCode().equals(KeyCode.ENTER)) {
                    words.getValue().add(inputText.getText());
                    inputText.setText("");
                }
            });

            BorderPane.setAlignment(inputText, Pos.CENTER);
            borderPane.setTop(inputText);

            Scene scene = new Scene(borderPane, 600, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        launch(args);
    }
}
