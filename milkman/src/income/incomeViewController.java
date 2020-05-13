package income;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import customers.dbconn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class incomeViewController {
Connection con;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker dtpstartdate;

    @FXML
    private DatePicker dtpenddate;

    @FXML
    private Label totalincome;

    @FXML
    void dototalincome(ActionEvent event) {
    	
try {
	PreparedStatement pst=con.prepareStatement("select sum(amount) from bill where status=? and dos>=? and doe<=?");
	pst.setInt(1,1);
	LocalDate ls=dtpstartdate.getValue();
	java.sql.Date std=java.sql.Date.valueOf(ls);
	pst.setDate(2,std);
	LocalDate le=dtpenddate.getValue();
	java.sql.Date std1=java.sql.Date.valueOf(le);
	pst.setDate(3,std1);
	ResultSet table=pst.executeQuery(); 
	while(table.next())
	{
		float sum=table.getFloat("sum(amount)");
	totalincome.setText(String.valueOf(sum));	
	}
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    }

    @FXML
    void initialize() {
        con=dbconn.makeconnection();
    }
}
