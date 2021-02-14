package sample;

import java.util.ArrayList;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class startGame{
    private static int row = 10;     //the total rows of the ball
    private static int column = 10;  //the total columns of the ball
    private static int height = 50;  //the height of the pic
    private static int width = 50;   //the weight of the pic
    public static int x1,y1,x2,y2;
    private static int [][] path1;
    private static int [][] path2;
    private static int [][] RandomArray;
    private static int number;
    public static MyPane prePane;
    public static MyPane curPane;
    public static int pressed = 0;

    public Group begin(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i =0; i<10;i++){
            for (int j = 0; j<10; j++){ //add the number (1-10) ten times
                list.add(j+1);
            }
        }

        RandomArray = new int [row][column];
        Group group = new Group();

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                int ID = (int) (Math.random() * list.size()); //get an ID randomly
                MyPane pane = new MyPane(i, j, list.get(ID));
                RandomArray[i][j] = list.get(ID);
                list.remove(ID); //remove the ID from arraylist which is already added to RandomArray
                pane.setLayoutX(width*j); //the position of the balls
                pane.setLayoutY(height*i);
                group.getChildren().add(pane);
            }
        }
        return group;
    }
    public void startGame(){
        path1 = new int [row+2][column+2]; //additional 4 line -- up bottom left right for finding the path
        path2 = new int [row+2][column+2];
        number = 100;
        if( RandomArray[x1][y1] == RandomArray[x2][y2] && (x1 != x2 || y1!= y2 )){ //Same ID but not the same element in array
           /* for (int i = 0; i < row+2 ; i++){
                for (int j = 0; j < column+2 ; j++)
                    path1[i][j] = 0;
            }*/
            for (int i = 0; i < row; i++){ //10
                for (int j = 0; j < column; j++){ //10
                    if( RandomArray[i][j] !=0)
                        path1[i+1][j+1] = 1;
                }
            }
            path1[x1+1][y1+1] = 0;
            path1[x2+1][y2+1] = 0;
            findPath( x1 + 1 ,y1 + 1 , x2 + 1 , y2 + 1, -1 , 0 );//Judge whether the path is okay
            if( number < 100 ){
                FadeTransition ft = new FadeTransition(Duration.millis(500), prePane); //The first choice
                ft.setToValue(0);
                ft.play();
                FadeTransition ft1 = new FadeTransition(Duration.millis(500), curPane); //The second choice
                ft1.setToValue(0);
                ft1.play();
                RandomArray[x1][y1] = 0;
                RandomArray[x2][y2] = 0;
                pressed = 0; //pressed will back to 0
                number = 100;
            }else{
                pressed = 1;
                //prePane.rect1.setFill(Color.BLUE);
                x1 = curPane.row;
                y1 = curPane.column;
                prePane = curPane;
            }
        }else{
            pressed = 1;
            //prePane.rect1.setFill(Color.BLUE);
            x1 = curPane.row;
            y1 = curPane.column;
            prePane = curPane;
        }
        if(ifEmpty()){ //all the balls are eliminated
            play();
        }
    }
    public void play(){
        Main.root = begin(); //run again
        Main.root.setLayoutX(150);
        Main.root.setLayoutY(50);
        Main.root.getChildren().addAll(backMainMenuG());
        Pane pane = new Pane();
        pane.setBackground(background());
        pane.getChildren().addAll(Main.root);
        Main.window.setScene(new Scene(pane,800,800));
    }

    public static boolean ifEmpty(){
        boolean flag = true;
        for( int i = 0 ; i < row ; i++){
            for( int j = 0 ; j < column ; j++ ){
                if(RandomArray[i][j]!=0)
                    flag = false;
            }
        }
        return flag;
    }

    public void findPath(int x1 , int y1 , int x2 , int y2 , int direction ,  int count){
        for(int i = 0 ; i < 4; i++){
            int sum = count;
            int d = direction;
            int x = x1 , y = y1;
            switch(i){                     // x  y    x2 y2
                case 0 : x++; break; //right [1][0] = [0][0]
                case 1 : y++; break; //down  [0][1] = [0][0]
                case 2 : x--; break; //left  [0][0] = [1][0]
                case 3 : y--; break; //up    [0][0] = [0][1]
            }
            if( in_range(x, y)){ //0-11,0-11
                if( d != i )  // (d=-1)!=0 d->0 ... (d=2)!=3 d->3
                    sum ++; //make sure only have 4 directions
                d = i;  //case 0,1,2,3
                if(path1[x][y] == 0 && sum < 4){  //when it is no ball in that position. if !=0(have something), for loop will next turn then change the direction to down
                    path1[x][y] = 2;
                    if( x == x2 && y == y2 )
                        print();
                    else //if not same, change pos and find again
                        findPath( x , y ,x2 ,y2 , d , sum );
                    path1[x][y] = 0;
                }
            }
        }
    }
    public void print(){
        int a = 0;
        for( int i = 0 ; i < row+2 ; i++ ){
            for ( int j = 0 ; j < column+2 ; j ++ ){
                if( path1[i][j] == 2)
                    a++;
            }
        }
        if ( a < number ){
            for( int i = 0 ; i < row+2 ; i++ ){
                for ( int j = 0 ; j < column+2 ; j ++ )
                    path2[i][j] = path1[i][j];
            }
            number = a;
        }
    }
    boolean in_range( int x , int y ){
        if( x >= 0 && x < row+2 && y >= 0 && y < column+2 )
            return true;
        else
            return false;
    }

    public ImageView labelPic() {
        Image labelPic = new Image("/sample/pic/Label.png");
        ImageView labelPicView = new ImageView(labelPic);
        labelPicView.setFitHeight(30);
        labelPicView.setFitWidth(30);
        return labelPicView;
    }

    public ImageView buttonPic(){
        Image picBtnMenu = new Image("/sample/pic/menu.png");
        ImageView picBtnMenuView = new ImageView(picBtnMenu);
        picBtnMenuView.setFitHeight(30);
        picBtnMenuView.setFitWidth(30);
        return picBtnMenuView;
    }

    public Background background(){
        Image backgroundImg = new Image("sample/pic/Background.png");
        BackgroundImage Background = new BackgroundImage(
                backgroundImg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(800,800,false,false,false,false)
        );
        Background background = new Background(Background);
        return background;
    }

    public Button goGamePage(){ //Button - Start game
        Button btnGoGamePlay = new Button("Play", buttonPic());
        btnGoGamePlay.setOnAction(e -> {
            play();
        });
        btnGoGamePlay.setMaxSize(250,200);
        return btnGoGamePlay;
    }
    public Button goGameRule(){
        Button btnGameRule = new Button("Rule", buttonPic());
        btnGameRule.setOnAction(e -> Main.window.setScene(Main.rulesPage));
        btnGameRule.setMaxSize(250,200);
        return btnGameRule;
    }
    public Button backMainMenuG(){ //Button - Go back to Menu from gamePage
        Button btnBackMainMenuG = new Button("Menu",buttonPic());
        btnBackMainMenuG.setPrefSize(200.0,100.0);
        btnBackMainMenuG.setOnAction(e -> Main.window.setScene(Main.mainMenu));
        btnBackMainMenuG.setLayoutX(150);
        btnBackMainMenuG.setLayoutY(650);
        return btnBackMainMenuG;
    }
    public Button backMainMenuR(){ //Button - Go back to Menu from rules
        Button btnBackMainMenuR = new Button("Menu", buttonPic());
        btnBackMainMenuR.setPrefSize(200.0,100.0);
        btnBackMainMenuR.setOnAction(e -> Main.window.setScene(Main.mainMenu));
        btnBackMainMenuR.setLayoutX(300);
        btnBackMainMenuR.setLayoutY(700);
        return btnBackMainMenuR;
    }
}