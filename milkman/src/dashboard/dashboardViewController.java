package dashboard;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class dashboardViewController {
	 URL url;
	   	Media media;
	   	MediaPlayer mediaplayer;
	   	AudioClip audio;
	       void playSong()
	       {
	       	
	       	url=getClass().getResource("tg1.mp3");
	   		media=new Media(url.toString());
	   		mediaplayer=new MediaPlayer(media);
	   		mediaplayer.play();
	       }
	       void playSound(){
	       	url=getClass().getResource("crash.wav");
	   		audio=new AudioClip(url.toString());
	   		audio.play();
	       }

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    int pausetime;
    
    @FXML
    void dopause(MouseEvent event) {
    	playSound();

mediaplayer.pause();
 pausetime =mediaplayer.getCurrentCount();
System.out.println("MY NAME IS TWINKLE");
    }

    @FXML
    void doplay(MouseEvent event) {
    	playSound();
    	System.out.println("my name");
playSong();
    }

    @FXML
    void doresume(MouseEvent event) {
    	playSound();

mediaplayer.setCycleCount(pausetime);
mediaplayer.play();
    }
    
    
    @FXML
    void billform(MouseEvent event) {
    	playSound();

    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("bill/billView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void billingform(MouseEvent event) {
    	playSound();

    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("billingconsole/billingView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void cutomerform(MouseEvent event) {
    	playSound();

    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("customers/formView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void incomeform(MouseEvent event) {
    	playSound();

    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("income/incomeView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void makepayment(MouseEvent event) {
    	playSound();

    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("payment/paymentView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void routineform(MouseEvent event) {
    	playSound();    	
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("routine/dailyView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void totaltable(MouseEvent event) {
    	playSound();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("totaltable/tableView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void variationconsole(MouseEvent event) {
    	playSound();
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("variationtable/variationView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			//to hide the opened window
			/* 
			   Scene scene1=(Scene)btnComboApp.getScene();
			   scene1.getWindow().hide();
			 */

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }
}
