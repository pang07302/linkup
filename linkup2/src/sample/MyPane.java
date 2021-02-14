package sample;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MyPane extends StackPane {
    public int row;
    public int column;
    public int number;
    Image[] pokeBallList;
    final public Rectangle rect1 = new Rectangle(0, 0, 50, 50);

    public MyPane(int rows, int columns, int numbers) {
        //final Button button = new Button();
        row = rows;  //x
        column = columns; //y
        number = numbers; //0-10, 0-10...

        rect1.setStroke(Color.TRANSPARENT); //fixed the pic
        rect1.setFill(null);

        Group group = new Group();
        pokeBallList = new Image[10];
        switch (number) {
            case 1:
                pokeBallList[0] = new Image("sample/pic/1.png");
                ImageView pokeBallView1 = new ImageView(pokeBallList[0]);
                pokeBallView1.setFitHeight(40);
                pokeBallView1.setFitWidth(40);
                group.getChildren().add(pokeBallView1);
                break;
            case 2:
                pokeBallList[1] = new Image("sample/pic/2.png");
                ImageView pokeBallView2 = new ImageView(pokeBallList[1]);
                pokeBallView2.setFitHeight(40);
                pokeBallView2.setFitWidth(40);
                group.getChildren().add(pokeBallView2);
                break;
            case 3:
                pokeBallList[2] = new Image("sample/pic/3.png");
                ImageView pokeBallView3 = new ImageView(pokeBallList[2]);
                pokeBallView3.setFitHeight(40);
                pokeBallView3.setFitWidth(40);
                group.getChildren().add(pokeBallView3);
                break;
            case 4:
                pokeBallList[3] = new Image("sample/pic/4.png");
                ImageView pokeBallView4 = new ImageView(pokeBallList[3]);
                pokeBallView4.setFitHeight(40);
                pokeBallView4.setFitWidth(40);
                group.getChildren().add(pokeBallView4);
                break;
            case 5:
                pokeBallList[4] = new Image("sample/pic/5.png");
                ImageView pokeBallView5 = new ImageView(pokeBallList[4]);
                pokeBallView5.setFitHeight(40);
                pokeBallView5.setFitWidth(40);
                group.getChildren().add(pokeBallView5);
                break;
            case 6:
                pokeBallList[5] = new Image("sample/pic/6.png");
                ImageView pokeBallView6 = new ImageView(pokeBallList[5]);
                pokeBallView6.setFitHeight(40);
                pokeBallView6.setFitWidth(40);
                group.getChildren().add(pokeBallView6);
                break;
            case 7:
                pokeBallList[6] = new Image("sample/pic/7.png");
                ImageView pokeBallView7 = new ImageView(pokeBallList[6]);
                pokeBallView7.setFitHeight(40);
                pokeBallView7.setFitWidth(40);
                group.getChildren().add(pokeBallView7);
                break;
            case 8:
                pokeBallList[7] = new Image("sample/pic/8.png");
                ImageView pokeBallView8 = new ImageView(pokeBallList[7]);
                pokeBallView8.setFitHeight(40);
                pokeBallView8.setFitWidth(40);
                group.getChildren().add(pokeBallView8);
                break;
            case 9:
                pokeBallList[8] = new Image("sample/pic/9.png");
                ImageView pokeBallView9 = new ImageView(pokeBallList[8]);
                pokeBallView9.setFitHeight(40);
                pokeBallView9.setFitWidth(40);
                group.getChildren().add(pokeBallView9);
                break;
            case 10:
                pokeBallList[9] = new Image("sample/pic/10.png");
                ImageView pokeBallView10 = new ImageView(pokeBallList[9]);
                pokeBallView10.setFitHeight(40);
                pokeBallView10.setFitWidth(40);
                group.getChildren().add(pokeBallView10);
                break;
        }

        getChildren().addAll(rect1, group);
        final MyPane pane = this;
        startGame ST = new startGame();
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                rect1.setFill(Color.ORANGE);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                rect1.setFill(null);
            }
        });
        setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                rect1.setFill(Color.ORANGE);
                startGame.pressed++;
                if (startGame.pressed == 1) {
                    startGame.x1 = row;
                    startGame.y1 = column;
                    startGame.prePane = pane; //the first choice
                }
                if (startGame.pressed == 2) {
                    startGame.x2 = row;
                    startGame.y2 = column;
                    startGame.curPane = pane; //the second choice
                    ST.startGame();
                }
            }
        });
    }
}
