package signup;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.xml.ws.handler.MessageContext;

import customers.dbconn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class signViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textid;

    @FXML
    private PasswordField textpassword;
    
    @FXML
    private Label message;


    @FXML
    void dosave(ActionEvent event) {
    	if(textid.getText().equals("")||textpassword.getText().equals(""))
		{
			Alert a=new Alert(AlertType.NONE);
			a.setAlertType(AlertType.WARNING);
			a.show();
		}
		else{
    	try {
    		System.out.println("whre is control");
    		PreparedStatement pst1=con.prepareStatement("select * from user where userid=?");
    		pst1.setString(1,textid.getText());
    		boolean jasus=false;
    		ResultSet table=pst1.executeQuery();
    		if(table.next()){
    			jasus=true;
    			message.setText("USER ID ALREADY EXIST");
    		}
    		else{
    			System.out.println("my name is");
    		PreparedStatement pst=con.prepareStatement("insert into user values(?,?)");
    		pst.setString(1,textid.getText());
    		pst.setString(2,textpassword.getText());
    		//System.out.println("abcdfgh");
    		pst.executeUpdate();
    		//System.out.println("saved.");
    		message.setText("SIGNED UP SUCCESSFULLY");
    		System.out.println(textpassword);
    		System.out.println("signed up successfullyyyy");
    		}
    		
    		} catch (Exception e) {
    			
    			}
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("login/loginView.fxml")); 
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
    }
    Connection con=dbconn.makeconnection();
    @FXML
    void initialize() {
       System.out.println("twinkle gupta");
    }
}

