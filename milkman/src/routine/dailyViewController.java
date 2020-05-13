package routine;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import customers.dbconn;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class dailyViewController {


    @FXML
    private RadioButton absent;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listbox;

    @FXML
    private TextField textcow;

    @FXML
    private TextField textbuff;

    @FXML
    private Label lblcow;

    @FXML
    private Label lblbuff;

    @FXML
    private DatePicker dtpdob;

    @FXML
    void dodelete(ActionEvent event) {
    	
    	ObservableList<String> si = listbox.getSelectionModel().getSelectedItems();
    	ArrayList< String> str=new ArrayList<>(si);
    	listbox.getItems().clear();
    	listbox.getItems().addAll(str);
    }

    @FXML
    void domark(ActionEvent event) {
textcow.setText("0.0");
textbuff.setText("0.0");
    }
    
    @FXML
    void dosave(ActionEvent event) {
try
{
	if(textbuff.getText().equals("")||textcow.getText().equals("")||dtpdob.getEditor().equals("")){
		Alert a=new Alert(AlertType.NONE);
		a.setAlertType(AlertType.WARNING);
		a.show();
	}else{
	String name=listbox.getSelectionModel().getSelectedItem();
	float cqv=Float.parseFloat(textcow.getText())-Float.parseFloat(lblcow.getText());
	float bqv=Float.parseFloat(textbuff.getText())-Float.parseFloat(lblbuff.getText());
	LocalDate ldob=dtpdob.getValue();
	java.sql.Date sqwdate=java.sql.Date.valueOf(ldob);
	PreparedStatement pst=con.prepareStatement("insert into variations values(?,?,?,?)");
	pst.setString(1,name);
	pst.setFloat(2,cqv);
	pst.setFloat(3,bqv);
	pst.setDate(4,sqwdate );
	pst.executeUpdate();
listbox.getItems().remove(listbox.getSelectionModel().getSelectedIndex());
	lblbuff.setText("");
	lblcow.setText("");
	textbuff.setText("");
	textcow.setText("");
	dtpdob.setValue(null);
	absent.setSelected(false);
	}
}
catch(SQLException e)
{
	e.printStackTrace();
}
    }

    @FXML
    void dofetch(ActionEvent event) {
String name=listbox.getSelectionModel().getSelectedItem();
PreparedStatement pst;
try {
	pst = con.prepareStatement("select * from customer where name=?");
	pst.setString(1,name);
	ResultSet table=pst.executeQuery();
	while(table.next()){
	float cq=table.getFloat("cquan");
	float bq=table.getFloat("bquan");
	lblcow.setText(String.valueOf(cq));
	lblbuff.setText(String.valueOf(bq));
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

    }

    
Connection con=dbconn.makeconnection();
ArrayList<String> nameArray=new ArrayList<>();
    @FXML
    void initialize() {
    	
    	try {
			PreparedStatement pst=con.prepareStatement("select  name from customer");
			ResultSet table=pst.executeQuery();
		
		while(table.next())
		{
			String name=table.getString(1);
			nameArray.add(name);
		}
		listbox.getItems().addAll(nameArray);
		listbox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
}

