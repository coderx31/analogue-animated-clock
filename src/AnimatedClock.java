import javafx.animation.*;
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
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

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



        Line hr = new Line(0,0,0,-75);
        hr.setId("hr");
        hr.setTranslateX(410);
        hr.setTranslateY(260);
        Line mn = new Line(0,0,0,-100);
        mn.setId("mn");
        mn.setTranslateX(410);
        mn.setTranslateY(260);
        Line sc = new Line(0,0,0,-125);
        sc.setId("sc");
        sc.setTranslateX(410);
        sc.setTranslateY(260);
        anchorPane.getChildren().addAll(circle,hr,mn,sc,circle1);
        Scene scene = new Scene(anchorPane,800,600);
        scene.getStylesheets().add("/CSS/Stylesheet.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        /*Determine the start time*/
        Calendar calendar = GregorianCalendar.getInstance();
        double secondDegrees = calendar.get(Calendar.SECOND) * (360.0 / 60);
        double minuteDegrees = (calendar.get(Calendar.MINUTE) + secondDegrees / 360.0 ) * (360.0 / 60);
        double hourDegrees = (calendar.get(Calendar.HOUR) + minuteDegrees / 360.0) * (360.0 / 12);

        /*Define the rotations to the map to analogue clock*/
        Rotate hourRotate = new Rotate(hourDegrees);
        Rotate minuteRotate = new Rotate(minuteDegrees);
        Rotate secondRotate = new Rotate(secondDegrees);

        hr.getTransforms().add(hourRotate);
        mn.getTransforms().add(minuteRotate);
        sc.getTransforms().add(secondRotate);

        /*hour hand rotate twice a day*/
        Timeline hourTime =  new Timeline(
                new KeyFrame(
                        Duration.hours(12),
                        new KeyValue(
                                hourRotate.angleProperty(),
                                360+hourDegrees,
                                Interpolator.LINEAR
                        )
                )
        );

        /*Minute hand rotate once a hour*/
        Timeline minuteTime = new Timeline(
                new KeyFrame(
                        Duration.minutes(60),
                        new KeyValue(
                                minuteRotate.angleProperty(),
                                360+minuteDegrees,
                                Interpolator.LINEAR
                        )
                )
        );

        /*Second hand rotate once a minute*/
        Timeline secondTime = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate.angleProperty(),
                                360+secondDegrees,
                                Interpolator.LINEAR
                        )
                )
        );

        /*Time never ends*/
        hourTime.setCycleCount(Animation.INDEFINITE);
        minuteTime.setCycleCount(Animation.INDEFINITE);
        secondTime.setCycleCount(Animation.INDEFINITE);

        /*start the analogue*/
        hourTime.play();
        minuteTime.play();
        secondTime.play();


    }

    public static void main(String[] args) {
        launch();
    }
}
