package sample;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }
    public static Group root;
    public static Stage window;
    public static Scene mainMenu, rulesPage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        startGame ST = new startGame(); //get method
        root = ST.begin();

        //The label in menu and rulesPage
        Label label_Menu = new Label("Menu",ST.labelPic());
        label_Menu.setFont(new Font("Arial", 24));
        Label label_Rules = new Label("These are the paths that can be connected",ST.labelPic());
        label_Rules.setFont(new Font("Arial", 36));

        //Layout mainMenu
        VBox layout_mainMenu = new VBox(10);
        layout_mainMenu.getChildren().addAll(label_Menu, ST.goGamePage(), ST.goGameRule());
        layout_mainMenu.setAlignment(Pos.CENTER);
        layout_mainMenu.setBackground(ST.background());
        mainMenu = new Scene(layout_mainMenu, 800,800);

        //rulesPage background
        Image rules = new Image("sample/pic/rulesPage.png");
        BackgroundImage rulesBackground = new BackgroundImage(
                rules,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(800,800,false,false,false,false)
        );
        Background rulesBack = new Background(rulesBackground);

        //Layout rulesPage
        Pane layout_rulesPage = new Pane();
        layout_rulesPage.setBackground(rulesBack);
        layout_rulesPage.getChildren().addAll(label_Rules,ST.backMainMenuR());
        label_Rules.setLayoutX(20);
        label_Rules.setLayoutY(0);
        rulesPage = new Scene(layout_rulesPage, 800,800);


        window.setScene(mainMenu);
        window.setTitle("Link Up");
        window.setResizable(false);
        window.show();
    }
}