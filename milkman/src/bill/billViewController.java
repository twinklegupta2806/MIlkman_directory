package bill;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import customers.dbconn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import sms.SST_SMS;
public class billViewController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ListView<String> listbox;

	@FXML
	private DatePicker startdate;

	@FXML
	private DatePicker enddate;

	@FXML
	private Label lblmobile;

	@FXML
	private Label lblcq;

	@FXML
	private Label lblcp;

	@FXML
	private Label lblbq;

	@FXML
	private Label lblbp;

	@FXML
	private Label varb;

	@FXML
	private Label varc;

	@FXML
	private Label lbltotal;

	void empty()
	{
		lblbp.setText("");
		lblbq.setText("");
		lblcp.setText("");
		lblcq.setText("");
		lbldays.setText("");
		lblmobile.setText("");
		lbltotal.setText("");
		varb.setText("");
		varc.setText("");
		startdate.getEditor().clear();
		enddate.getEditor().clear();
	}
	
	@FXML
	void dofetch(ActionEvent event) {
		empty();
	
		String name = listbox.getSelectionModel().getSelectedItem();
		PreparedStatement pst;
		try {
			
			lbltotal.setText("");
			PreparedStatement pst1=con.prepareStatement("select * from bill where name=?");
			pst1.setString(1,name);
			ResultSet table1=pst1.executeQuery();
			if(table1.next())
			{
				lbltotal.setText(table1.getString("amount"));
			}
			else{
			pst = con.prepareStatement("select * from customer where name=?");
			pst.setString(1, name);
			ResultSet table = pst.executeQuery();
			while (table.next()) {
				float cq = table.getFloat("cquan");
				float cp = table.getFloat("cprice");
				float bq = table.getFloat("bquan");
				float bp = table.getFloat("bprice");
				lblcq.setText(String.valueOf(cq));
				lblbq.setText(String.valueOf(bq));
				lblcp.setText(String.valueOf(cp));
				lblbp.setText(String.valueOf(bp));
				lblmobile.setText(table.getString("mobile"));
				java.sql.Date dob = table.getDate("dos");
				startdate.getEditor().setText(dob.toString());
			}
			//empty();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void doformbill(ActionEvent event) {
		if(enddate.getEditor().equals("")||lbldays.getText().equals(""))
		{
			Alert a=new Alert(AlertType.NONE);
			a.setAlertType(AlertType.WARNING);
			a.show();
		}else
		{
		float cq=Float.parseFloat(lblcq.getText());
		float cp=Float.parseFloat(lblcp.getText());
		float bq=Float.parseFloat(lblbq.getText());
		float bp=Float.parseFloat(lblbp.getText());
		int days=Integer.parseInt(lbldays.getText());
		float vb=Float.parseFloat(varb.getText());
		float vc=Float.parseFloat(varc.getText());
		//System.out.println(cq+"  "+cp+"   ");
		
float totalcquan =cq*days+vc;
float totalbquan=bq*days+vb;
//System.out.println("  "+totalcquan+"   "+ "     "+totalbquan+"    "+vb+"   "+vc+"  ");
		float total=totalcquan*cp+totalbquan*bp;
		lbltotal.setText(String.valueOf(total));
		}
	}

	@FXML
	private Label lbldays;

	@FXML
	void dogetdays(ActionEvent event) {
		LocalDate ldob = startdate.getValue();

		java.sql.Date swdob = null;
		String stwdob = "";
		if (ldob == null) {
			stwdob = startdate.getEditor().getText();
			ldob = LocalDate.parse(stwdob);
		}
		swdob = java.sql.Date.valueOf(ldob);

		//Period dur = Period.between(ldob, enddate.getValue());
		long totaldays=ChronoUnit.DAYS.between(ldob,enddate.getValue());
		//System.out.println(dur);
		lbldays.setText(String.valueOf(totaldays));
	}

	@FXML
	void dosave(ActionEvent event) {
try {
	LocalDate ldob = startdate.getValue();
	float cq=Float.parseFloat(lblcq.getText());
	float cp=Float.parseFloat(lblcp.getText());
	float bq=Float.parseFloat(lblbq.getText());
	float bp=Float.parseFloat(lblbp.getText());
	int days=Integer.parseInt(lbldays.getText());
	float vb=Float.parseFloat(varb.getText());
	float vc=Float.parseFloat(varc.getText());
float totalcquan =cq*days+vc;
float totalbquan=bq*days+vb;
	float total=totalcquan*cp+totalbquan*bp;
	java.sql.Date swdob = null;
	String stwdob = "";
	if (ldob == null) {
		stwdob = startdate.getEditor().getText();
		ldob = LocalDate.parse(stwdob);
	}
	swdob = java.sql.Date.valueOf(ldob);
LocalDate end=enddate.getValue();
java.sql.Date swend=java.sql.Date.valueOf(end);
PreparedStatement pst1=con.prepareStatement("update customer set dos=? where name=?");
pst1.setString(2,listbox.getSelectionModel().getSelectedItem());
	PreparedStatement pst=con.prepareStatement("insert into bill values(?,?,?,?,?,?,?)");
	pst.setString(1,listbox.getSelectionModel().getSelectedItem());
	pst.setDate(2,swdob);
	pst.setDate(3,swend);
	System.out.println(end);
    LocalDate end1=end.plusDays(1);
    System.out.println(end1);
	java.sql.Date swend2=java.sql.Date.valueOf(end1);
	pst1.setDate(1,swend2);
	pst1.executeUpdate();
	pst.setFloat(4,Float.parseFloat(lbltotal.getText()));
	pst.setFloat(5,totalcquan);
	pst.setFloat(6,totalbquan);
	pst.setInt(7,0);
	pst.executeUpdate();
	TextInputDialog dialog = new TextInputDialog("");
	dialog.setTitle("Input Data...");
	dialog.setContentText("Please enter Mobile number:");

	// Traditional way to get the response value.
	Optional<String> result = dialog.showAndWait();
			
		//doAlert(result.get());
	if(result.isPresent())
	{
		String sd=swdob.toString();
		String ed=swend.toString();
		String msg="Your bill has been formed from date"+sd+"till date"+ed+"="+lbltotal.getText()+".Total cow milk taken="+totalcquan+"and total "
				+ "buffalo milk taken ="+totalbquan;
				
		String resp=SST_SMS.bceSunSoftSend(result.get(), msg);	
		System.out.println(resp);
	
		TextInputDialog dialog1 = new TextInputDialog("");
		dialog1.setTitle("Message");
		

		if(resp.contains("successfully"))
		{
			dialog1.setContentText("Message delivered");
			System.out.println("sent..........................");
			
		}
		else{
			if(resp.contains("Unknown"))
				dialog1.setContentText("Check your Internet connection");
			if(resp.contains("Invalid"))
				dialog1.setContentText("Wrong Mobile Number");
		}
		dialog1.show();
	}
	empty();
	
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

	
	
	
	@FXML
	void getvariations(ActionEvent event) {
		try {
			LocalDate ldob = startdate.getValue();

			java.sql.Date swdob = null;
			String stwdob = "";
			if (ldob == null) {
				stwdob = startdate.getEditor().getText();
				ldob = LocalDate.parse(stwdob);
			}
			swdob = java.sql.Date.valueOf(ldob);
			java.sql.Date sqwed = java.sql.Date.valueOf(enddate.getValue());
           // String sd=startdate.getEditor().getText();
           // String ed=enddate.getEditor().getText();
			PreparedStatement pst = con.prepareStatement("select sum(cowvar) as sumc,sum(buffvar) as sumb from variations where cname=? and currdate BETWEEN ? and ?");
			pst.setString(1, listbox.getSelectionModel().getSelectedItem());
			pst.setDate(2,swdob);
			pst.setDate(3,sqwed);
			ResultSet table = pst.executeQuery();
			while (table.next()) {
				float sumb = table.getFloat("sumb");
				float sumc = table.getFloat("sumc");
				varc.setText(String.valueOf(sumc));
				varb.setText(String.valueOf(sumb));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	Connection con = dbconn.makeconnection();

	@FXML
	void initialize() {
		try {
			empty();
			ArrayList<String> nameArray = new ArrayList<>();
			PreparedStatement pst = con.prepareStatement("select  name from customer");
			ResultSet table = pst.executeQuery();
			while (table.next()) {
				String name = table.getString(1);
				nameArray.add(name);
				lblbp.setText("");
				lblbq.setText("");
				lblcp.setText("");
				lblcq.setText("");
				lbldays.setText("");
				lblmobile.setText("");
				lbltotal.setText("");
				
			}
			listbox.getItems().addAll(nameArray);
			listbox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
