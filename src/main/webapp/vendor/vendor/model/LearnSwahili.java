package Animation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LearnSwahili extends Application {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub

        Button selectButton = new Button("Select Words");

        TextArea textArea = new TextArea();
        textArea.setEditable(true);

        selectButton.setOnAction(e -> {
            Map<String, String> map = null;
            try {
                map = new MySqlAccess().readDataBase();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String words = "\n";
            if (map != null) {
                for (String key : map.keySet()) {
                    words = words + key + " ---  " + map.get(key) + "\n\n";
                }
                textArea.setText(words);
            }
        });

        HBox hbox = new HBox(30);
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e->{
            textArea.setText("");
        });
        Button exitButton = new Button("Exit");

        hbox.getChildren().addAll(clearButton, exitButton);

        VBox vbox = new VBox(20);
        // vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(selectButton, textArea, hbox);

        Scene scene = new Scene(vbox);

        exitButton.setOnAction(e -> {
            scene.getWindow().hide();
        });
        stage.setTitle("Learn Kiswahili");
        stage.setScene(scene);
        stage.show();

    }

    class MySqlAccess {

        private Connection connect = null;
        private Statement statement = null;
        private ResultSet resultSet = null;

        final private String host = "localhost";
        final private String user = "root";
        final private String passwd = "";

        public Map<String, String> readDataBase() throws Exception {
            try {
                // This will load the MySQL driver, each DB has its own driver
                Class.forName("com.mysql.jdbc.Driver");

                // Setup the connection with the DB called dictionary
                connect = DriverManager.getConnection(
                        "jdbc:mysql://" + host + "/dictionary?" + "user=" + user + "&password=" + passwd);

                // Statements allow to issue SQL queries to the database
                statement = connect.createStatement();
                // Result set get the result of the SQL query
                resultSet = statement.executeQuery("select word, meaning from words order by RAND() limit 5");
                // writeResultSet(resultSet);
                Map<String, String> map = new HashMap<>();
                while (resultSet.next()) {
                    String word = resultSet.getString(1);
                    String meaning = resultSet.getString(2);
                    map.put(word, meaning);
                }

                return map;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                connect.close();
            }
        }

    }
}

