import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class msPaint extends Application
{
	/**
	 * Instance Data
	 */
	private ColorPicker colorPicker;
	private Group aditList;
	private Button panav;
	private HBox boxy;
	/**
	 * Start Method
	 */
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		aditList = new Group(new Polyline());

		colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(this::processColorChoice);

        panav = new Button("Clear");
        panav.setOnAction(this::processButtonPress);

        boxy = new HBox(panav, colorPicker);
        boxy.setAlignment(Pos.TOP_CENTER);
        boxy.setSpacing(30);

        Pane pain = new Pane(boxy, aditList);
        pain.setStyle("-fx-background-color: #000000");

        Scene scene = new Scene(pain, 750, 500);
        scene.setOnMousePressed(this::processMousePressed);
        scene.setOnMouseDragged(this::processMouseDragged);
        scene.setOnMouseReleased(this::processMouseReleased);


        primaryStage.setTitle("CF Paint");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
	/**
	 * Draws a line where the mouse is when the mouse is dragged
	 * @param e - MouseEvent
	 */
	public void processMouseDragged(MouseEvent e)
	{
		System.out.println("Mouse X: " + e.getX() + " Mouse Y: " + e.getY());
		((Polyline) aditList.getChildren().get((aditList.getChildren().size() - 1))).getPoints().addAll(new Double[]{e.getX(), e.getY()});
	}
	/**
	 * Begins drawing a line when the mouse is pressed
	 * @param e - MouseEvent
	 */
	public void processMousePressed(MouseEvent e)
	{
		System.out.println("Mouse X: " + e.getX() + " Mouse Y: " + e.getY());
		((Polyline) aditList.getChildren().get((aditList.getChildren().size() - 1))).getPoints().addAll(new Double[]{e.getX(), e.getY(), e.getX(), e.getY()});
	}
	/**
	 * Stops drawing current line and creates a new one
	 * @param e - MouseEvent
	 */
	public void processMouseReleased(MouseEvent e)
	{
		System.out.println("Mouse was released");
		aditList.getChildren().add(new Polyline());
		((Polyline) aditList.getChildren().get((aditList.getChildren().size() - 1))).setStroke(colorPicker.getValue());
	}
	/**
	 * Sets the color of the line to the color of the color picker
	 * @param e - ActionEvent
	 */
	public void processColorChoice(ActionEvent e)
    {
		System.out.println("Color Picker is used");
		((Polyline) aditList.getChildren().get((aditList.getChildren().size() - 1))).setStroke(colorPicker.getValue());
    }
	/**
	 * Clears all lines from the screen when button is pressed
	 * @param e - ActionEvent
	 */
	public void processButtonPress(ActionEvent e)
	{
		System.out.println("Button is Pressed");
		aditList.getChildren().clear();
		aditList.getChildren().add(new Polyline());
		((Polyline) aditList.getChildren().get((aditList.getChildren().size() - 1))).setStroke(colorPicker.getValue());
	}
}
