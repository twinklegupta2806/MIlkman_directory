package payment;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import customers.dbconn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class paymentViewController {

	Connection con;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblcow;

    @FXML
    private Label lblbuff;

    @FXML
    private Label lblamt;

    @FXML
    private Label lblsdate;

    @FXML
    private Label lbledate;

    @FXML
    private ComboBox<String> combox;


    @FXML
    void dogetvalues(ActionEvent event) {
String name=combox.getSelectionModel().getSelectedItem();
try {
	PreparedStatement pst=con.prepareStatement("select * from bill where status=? and name=?");
	pst.setInt(1, 0);
	pst.setString(2, name);
	ResultSet table=pst.executeQuery();
	while(table.next())
	{
		float amount=table.getFloat("amount");
		float bqtotal=table.getFloat("bqtotal");
		float cqtotal=table.getFloat("cqtotal");
		lblamt.setText(String.valueOf(amount));
		lblbuff.setText(String.valueOf(bqtotal));
		lblcow.setText(String.valueOf(cqtotal));
		java.sql.Date sd=table.getDate("dos");
		java.sql.Date ed=table.getDate("doe");
		lblsdate.setText(sd.toString());
		lbledate.setText(ed.toString());
		
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

    }

    
    @FXML
    void doset(ActionEvent event) {
String name=combox.getSelectionModel().getSelectedItem();
try{
PreparedStatement pst=con.prepareStatement("update bill set status=? where status=? and name=?");
	pst.setInt(2, 0);
		pst.setString(3, name);
		pst.setInt(1, 1);
		pst.executeUpdate();
		String r=combox.getSelectionModel().getSelectedItem();
		combox.getItems().remove(r);
		lblamt.setText(null);
		lblbuff.setText(null);
		lblcow.setText(null);
		lbledate.setText(null);
		lblsdate.setText(null);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
    }
    void fillCombo()
    {
    	ArrayList<String> nameArray=new ArrayList<>();
    	try {
			PreparedStatement pst=con.prepareStatement("select  name from bill where status=0");
			ResultSet table=pst.executeQuery();
		
		while(table.next())
		{
			String name=table.getString(1);
			nameArray.add(name);
		}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	combox.getItems().addAll(nameArray);
    	
    	System.out.println();
    }
    	
    @FXML
    void initialize() {
       con=dbconn.makeconnection();
       fillCombo();
    }
}
