package com.example.javafx4gewinnt;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class HelloController {

    @FXML
    GridPane grid;
    int player = 0;
    int anzahlSteine = 0;
    char[][] field = new char [6][7];
    boolean win = false;
    Paint color1;
    Paint color2;
    char [] chars = {'-','+'};

    @FXML
    Button Surrender;

    @FXML
    Button newGame;

    @FXML
    Circle showPlayer;

    @FXML
    Pane startScreen;

    @FXML
    ColorPicker colorPickerPlayer1;

    @FXML
    ColorPicker colorPickerPlayer2;

    @FXML
    public void initialize()
    {
        //Start
        newGame.setDisable(true);

        //Füllt Array zur Spielüberprüfung
        fill();

        //Testausgabe für Konsole
        //output();

        //Generiert Buttons für das Gridpane
        generateButtons();


    }


    public Button createButton(int z)
    {
        //Buttonstyling
        Button button = new Button(Integer.toString(z));
        button.setStyle("-fx-font-size: 40px");
        button.setStyle("-fx-opacity: 0");
        button.setMaxSize(144, 144);

            button.setOnAction(e -> {
                //r steht für die Reihe in welche die Kreise platziert werden
                int r = 5;
                while(field[r][Integer.parseInt(button.getText()) - 1] != '*')
                {
                    //r wird heruntergezählt, wenn in der untersten Reihe etwas platziert ist
                    r--;
                }
                if(r==0)
                {
                    //Wenn r==0 sollte man keine Kreise mehr in der Spalte platzieren Können
                    button.setDisable(true);
                }
                //Macht Zahl der Buttons in einen Integer Wert und benutzt diesen
                place(Integer.parseInt(button.getText()) - 1  , r);
            });
        return button;
    }

    public void place(int eingabe, int reihe)
    {
        anzahlSteine++;
        Circle kreis = new Circle(45);
        kreis.setId("Kreis");

        //Verschiedene Spieler
        if(player == 0)
        {
            kreis.setFill(color1);
            kreis.setStyle("-fx-alignment: center");
            player = 1;
            field[reihe][eingabe] = '+';
        }else{
            kreis.setFill(color2);
            kreis.setStyle("-fx-alignment: center");
            player = 0;
            field[reihe][eingabe] = '-';
        }

        //Fügt Kreis in Gridpane und setzt ihn in die Mitte der Zelle
        grid.add(kreis, eingabe, reihe);
        GridPane.setHalignment(kreis, HPos.CENTER);
        GridPane.setValignment(kreis, VPos.CENTER);
        //Konsolen Ausgabe
        output();

        //Text wechsler für Text zwischen Buttons
        if(player==0) showPlayer.setFill(color1);
        if(player==1) showPlayer.setFill(color2);


        //Schaut ob das Spiel vorbei ist und falls ja gibt es ein Modal für den Entsprechenden Spieler aus
        if(win==false) {
            if (diagonalvictory() || horizontalvictory() || verticalvictory()) {
                win = true;
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(newGame.getScene().getWindow());
                alert.setTitle("Glückwunsch!");
                alert.setHeaderText(null);
                if (player == 1) alert.setContentText("Glückwunsch zum Sieg Spieler 1!");
                if (player == 0) alert.setContentText("Glückwunsch zum Sieg Spieler 2!");
                alert.showAndWait();
                newGame.setDisable(false);
                Surrender.setDisable(true);
            } else if (draw()) // Wenn unentschieden
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(newGame.getScene().getWindow());
                win = true;
                alert.setTitle("Unentschieden.");
                alert.setHeaderText(null);
                alert.setContentText("Das Spiel endet in einem Unentschieden.");
                alert.showAndWait();
                newGame.setDisable(false);
                Surrender.setDisable(true);
            }
        }


    }

    public void generateButtons()
    {
    for(int j=0; j<7; j++)
    {
        grid.add(createButton(j+1),j, 0);
    }
    }

    public void fill()
    {
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<7; j++)
            {
                field[i][j] = '*';
            }
            System.out.println();
        }
    }


    public void output()
    {
        for(int i=0; i<6; i++)
        {
            for(int j=0; j<7; j++)
            {
                System.out.print(field[i][j]+ " ");
            }
            System.out.println("");
        }
        System.out.println();
    }

    public boolean horizontalvictory()
    {
        for(int x = 5; x >= 3; x--){
            for(int y = 0; y <=6; y++) {

                if (field[x][y] == chars[player] && field[x - 1][y] == chars[player] && field[x - 2][y] == chars[player] && field[x - 3][y] == chars[player]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean verticalvictory()
    {
        for(int x = 5; x>=0;x--){
            for(int y = 0; y<=3;y++){
                if (field[x][y] == chars[player] && field[x][y+1] == chars[player] && field[x][y+2] == chars[player] && field[x][y+3] == chars[player]) {
                    return true;
                }

            }
        }
        return false;
    }

    public boolean diagonalvictory()
    {
        for(int x = 5; x >= 3; x--){
            for(int y = 0; y <=3; y++) {

                if (field[x][y] == chars[player] && field[x - 1][y+1] == chars[player] && field[x - 2][y+2] == chars[player] && field[x - 3][y+3] == chars[player]) {
                    return true;
                }
            }

        }
        for(int x = 5; x >= 3; x--){
            for(int y = 6; y >=3; y--) {

                if (field[x][y] == chars[player] && field[x - 1][y-1] == chars[player] && field[x - 2][y-2] == chars[player] && field[x - 3][y-3] == chars[player]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean draw()
    {
        System.out.println(anzahlSteine);
        if(anzahlSteine == 42)
        {
            return true;
        }
        return false;
    }


    public void newGame(ActionEvent actionEvent)
    {
        anzahlSteine=0;
        win=false;
        Surrender.setDisable(false);
        newGame.setDisable(true);
        Node node = grid.getChildren().get(0);
        grid.getChildren().clear();
        grid.getChildren().add(0,node);
        fill();
        generateButtons();
    }

    public void Surrender(ActionEvent actionEvent)
    {
        newGame.setDisable(false);
        Surrender.setDisable(true);
        win = true;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(newGame.getScene().getWindow());
        alert.setTitle("Glückwunsch!");
        alert.setHeaderText(null);
        if(player==0) alert.setContentText("Glückwunsch zum Sieg Spieler 1!");
        if(player==1) alert.setContentText("Glückwunsch zum Sieg Spieler 2!");

        alert.showAndWait();
    }

    public void startGame(ActionEvent actionEvent)
    {
            color1 = colorPickerPlayer1.getValue();
            color2 = colorPickerPlayer2.getValue();
            if(color1.equals(color2))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Farbe bereits vorhanden!");
                alert.initOwner(newGame.getScene().getWindow());
                alert.setHeaderText(null);
                alert.setContentText("Ein Spieler muss die Farbe ändern!");
                alert.showAndWait();
            }
            else
            {
                newGame.setDisable(true);
                Surrender.setDisable(false);
                startScreen.setDisable(true);
                startScreen.setOpacity(0);
                showPlayer.setFill(color1);
            }
    }

    public void backToHome(ActionEvent actionEvent) {
        startScreen.setDisable(false);
        startScreen.setOpacity(25);
        color1 = colorPickerPlayer1.getValue();
        color2 = colorPickerPlayer2.getValue();
        newGame(null);
    }
}