import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AnimatedClock extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        DropShadow upperDrop = new DropShadow();
        upperDrop.setOffsetX(0);
        upperDrop.setOffsetY(-15);
        upperDrop.setBlurType(BlurType.THREE_PASS_BOX);
        upperDrop.setColor(Color.rgb(255,255,255,0.05));

        DropShadow belowDrop = new DropShadow();
        belowDrop.setOffsetX(0);
        belowDrop.setOffsetY(15);
        belowDrop.setBlurType(BlurType.THREE_PASS_BOX);
        belowDrop.setColor(Color.rgb(0,0,0,0.3));

        InnerShadow upperInner = new InnerShadow();
        upperInner.setOffsetX(0);
        upperInner.setOffsetY(-15);
        upperInner.setBlurType(BlurType.THREE_PASS_BOX);
        upperInner.setColor(Color.rgb(255,255,255,0.05));

        InnerShadow belowInner = new InnerShadow();
        belowInner.setOffsetX(0);
        belowInner.setOffsetY(15);
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
        anchorPane.getChildren().addAll(circle);
        Scene scene = new Scene(anchorPane,800,600);
        scene.getStylesheets().add("/CSS/Stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}
