package Animation;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.event.ActionListener;

public class MilesToKm extends Application {

    Stage window;
    @Override
    public void start(Stage primaryStage) {
        window=primaryStage;
        GridPane gridPane=new GridPane();
        Label label=new Label("LENGTH CONVERTER");
        Label label1=new Label("Miles");
        Label label2=new Label("Kilometers");

        TextField textField=new TextField();
        TextField textField1=new TextField();
        textField1.setStyle("-fx-background-color: grey");

        Button button=new Button("CONVERT");
        button.setStyle("-fx-background-color: grey");

        //button.setOnAction(event -> conversion());
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int one=Integer.parseInt(textField.getText());
                String ans=String.valueOf(one/0.62137119);
                textField1.setText(ans);
            }
        });
        GridPane.setConstraints(label,3,1);
        GridPane.setConstraints(label1,2,2);
        GridPane.setConstraints(button,3,4);
        GridPane.setConstraints(label2,2,3);
        GridPane.setConstraints(textField,3,2);
        GridPane.setConstraints(textField1,3,3);
        gridPane.setVgap(30);
        gridPane.setHgap(30);
        gridPane.getChildren().addAll(label,label1,button,label2,textField,textField1);
        Scene scene=new Scene(gridPane,700,700);
        window.setTitle("Distance Converter");
        window.setScene(scene);
        window.show();



    }


    public static void main(String[] args){
        launch(args);
    }
}
