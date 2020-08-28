import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class AnimatedClock extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DropShadow upperDrop = new DropShadow();
        upperDrop.setOffsetX(-5);
        upperDrop.setOffsetY(-5);
        upperDrop.setBlurType(BlurType.THREE_PASS_BOX);
        upperDrop.setColor(Color.rgb(255,255,255,0.05));

        DropShadow belowDrop = new DropShadow();
        belowDrop.setOffsetX(5);
        belowDrop.setOffsetY(5);
        belowDrop.setBlurType(BlurType.THREE_PASS_BOX);
        belowDrop.setColor(Color.rgb(0,0,0,0.3));

        InnerShadow upperInner = new InnerShadow();
        upperInner.setOffsetX(-5);
        upperInner.setOffsetY(-5);
        upperInner.setBlurType(BlurType.THREE_PASS_BOX);
        upperInner.setColor(Color.rgb(255,255,255,0.05));

        InnerShadow belowInner = new InnerShadow();
        belowInner.setOffsetX(5);
        belowInner.setOffsetY(5);
        belowInner.setBlurType(BlurType.THREE_PASS_BOX);
        belowInner.setColor(Color.rgb(0,0,0,0.3));
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setId("anchor-pane");
        Circle circle = new Circle(180);
        circle.setId("circle");
        circle.setLayoutY(260);
        circle.setLayoutX(410);
       circle.setEffect(upperDrop);
        upperDrop.setInput(upperInner);
        upperInner.setInput(belowDrop);
        belowDrop.setInput(belowInner);

        /*Loading Image and set to the circle background*/
        FileInputStream clockFile = new FileInputStream("Resources/Images/clock.png");
        Image clock = new Image(clockFile);
        circle.setFill(new ImagePattern(clock));

        Circle circle1 = new Circle(10);
        circle1.setId("dot");
        circle1.setLayoutY(circle.getCenterY()+260);
        circle1.setLayoutX(circle.getCenterX()+410);

        Rectangle hr = new Rectangle(6,80);
        hr.setId("hr");
        hr.setLayoutX(circle1.getCenterX()+408);
        hr.setLayoutY(circle1.getCenterY()+180);

        Rectangle mn = new Rectangle(4,100);
        mn.setId("mn");
        mn.setLayoutY(circle1.getCenterY()+160);
        mn.setLayoutX(circle1.getCenterX()+407.6);

        Rectangle sc = new Rectangle(2,120);
        sc.setId("sc");
        sc.setLayoutX(circle1.getCenterX()+408);
        sc.setLayoutY(circle1.getCenterY()+140);
        anchorPane.getChildren().addAll(circle,hr,mn,sc,circle1);
        Scene scene = new Scene(anchorPane,800,600);
        scene.getStylesheets().add("/CSS/Stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
