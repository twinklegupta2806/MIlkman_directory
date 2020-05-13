package login;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;

public class loginViewController {
	Connection con=null;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textuserid;

    @FXML
    private PasswordField textpasswsord;

    @FXML
    private javafx.scene.control.Button log;
    
    @FXML
    private Label message;

    @FXML
    void dosignup(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("signup/signView.fxml")); 
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			/*//to hide the opened window
			 Stage scene1=(Stage)log.getScene().;
			  // Scene scene1=(Scene)log.getScene();
			   scene1..hide();
			 
*/
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    
    @FXML
    void dogetpassword(ActionEvent event) {
    	String lws=textuserid.getText();
    	try {
    		PreparedStatement pst=con.prepareStatement("select * from user where userid=?");
    		pst.setString(1,lws);
    		ResultSet table=pst.executeQuery();
    		
    		boolean jasus=false;
    		while(table.next())
    		{
    			jasus=true;
    			String password=table.getString("password");
    			
    		textpasswsord.setText(password);	
    			}
    		if(jasus==false)
    			message.setText("INVALID USERID");
    		else
    			message.setText("YOUR RIGHT PASSWORD");
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @FXML
    void dologin(ActionEvent event) {
    	if(textpasswsord.getText().equals("")||textuserid.getText().equals(""))
    	{
    		Alert a=new Alert(AlertType.NONE);
    		a.setAlertType(AlertType.WARNING);
    		a.show();
    	}
    	else{
String lws=textuserid.getText();
try {
	PreparedStatement pst=con.prepareStatement("select * from user where userid=?");
	pst.setString(1,lws);
	ResultSet table=pst.executeQuery();
	
	boolean jasus=false;
	while(table.next())
	{
		jasus=true;
		String password=table.getString("password");
		String s=textpasswsord.getText();
		System.out.println(s);
		if(!password.equals(s))
		jasus=false;	
		}
	if(jasus==false)
		message.setText("INVALID USERID/PASSWORD");
	else
		message.setText("LOGIN SUCCESSFUL");
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try{
	Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("dashboard/dashboardView.fxml")); 
	Scene scene = new Scene(root);
	Stage stage=new Stage();
	stage.setScene(scene);
	stage.show();
	//Parent root1=FXMLLoader.load(getClass().getClassLoader().getResource("login/loginView.fxml")); 
	//Scene sc=Milkman.getScene();
	//sc.getWindow().hide();
	//sc.getWindow().hi
	//to hide the opened window
	 Stage st1=(Stage)log.getScene().getWindow();
	  // Scene scene1=(Scene)log.getScene();
	   st1.hide();
	 

}
catch(Exception e)
{
	e.printStackTrace();
}
    	}
    }
    
    		
    @FXML
    void initialize() {
        con=dbconn.makeconnection();
     //  textpassword.setText("twinkle");
    }
}



