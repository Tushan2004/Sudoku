package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.SudokuModel;

public class ViewTest extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Skapa ett Sudoku-spel
        SudokuModel game = new SudokuModel();
        game.initializeBoard();
        int[][] array = game.getBoardState();

        // Skapa ett TilePane för spelbrädet
        TilePane gameBoard = new TilePane();
        gameBoard.setPrefColumns(9);
        gameBoard.setMaxWidth(270); // Ställ in antal kolumner till 9

        // Skapa en TilePane för att arrangera nummerknapparna
        TilePane tilePane = new TilePane();
        tilePane.setPrefColumns(3); // Ställ in kolumner till 3

        VBox chooseNmr = new VBox(5); // 5 pixlar mellan varje knapp

        // Skapa knappar från 1 till 9
        for (int i = 1; i <= 9; i++) {
            Button button = new Button(String.valueOf(i));
            chooseNmr.getChildren().add(button); // Lägg till knappen i VBox
        }
        Button button = new Button(String.valueOf("C"));
        chooseNmr.getChildren().add(button); // Lägg till knappen i VBox

        // Lägg till siffror som etiketter i spelbrädet
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                Label numberLabel;

                if (array[i][j] == 0) {
                    numberLabel = new Label(); // Skapa en tom etikett
                } else {
                    numberLabel = new Label(String.valueOf(array[i][j])); // Skapa etikett för varje siffra
                }

                numberLabel.setPrefWidth(30); // Sätt preferensbredd
                numberLabel.setPrefHeight(30); // Sätt preferenshöjd
                numberLabel.setStyle("-fx-border-color: black; -fx-background-color: lightgray; -fx-alignment: center;"); // Stil
                numberLabel.setAlignment(javafx.geometry.Pos.CENTER); // Centrera text
                gameBoard.getChildren().add(numberLabel); // Lägg till etikett i TilePane
            }
        }

        // Skapa en MenuBar
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem newGameItem = new MenuItem("New Game");
        MenuItem exitItem = new MenuItem("Exit");

        // Lägg till menyobjekt i menyn
        fileMenu.getItems().addAll(newGameItem, exitItem);
        menuBar.getMenus().addAll(fileMenu);


        Button hintButton = new Button("Hint");
        Button checkButton = new Button("Check");

        // Lägg knapparna i en VBox på vänstra sidan
        VBox leftSideButtons = new VBox(10); // 10 pixlar mellan varje knapp

        // Skapa en tom region för att centrera knapparna
        Region spacerTop = new Region();
        Region spacerBottom = new Region();
        VBox.setVgrow(spacerTop, Priority.ALWAYS); // Fyll utrymmet ovanför med spacer
        VBox.setVgrow(spacerBottom, Priority.ALWAYS); // Fyll utrymmet under med spacer

        leftSideButtons.getChildren().addAll(spacerTop, hintButton, checkButton, spacerBottom);


        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(leftSideButtons); // Placera knapparna på vänstra sidan (centrerade)
        borderPane.setCenter(gameBoard); // Placera spelbrädet i mitten
        borderPane.setRight(chooseNmr); // Placera nummerknapparna på högra sidan

        // Skapa en VBox som root
        VBox root = new VBox();
        root.getChildren().addAll(menuBar, borderPane); // Sätt MenuBar högst upp och BorderPane som layout

        // Skapa scenen och visa den
        Scene scene = new Scene(root, 500, 500); // Justera storleken på scenen om nödvändigt
        primaryStage.setTitle("Sudoku med Hint och Check");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
