package customers;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class formViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Label message;
    
    @FXML
    private URL location;

    @FXML
    private TextField textmobile;

    @FXML
    private TextArea textaddress;

    @FXML
    private TextField textcquan;

    @FXML
    private TextField textbquan;

    @FXML
    private TextField textcprice;

    @FXML
    private TextField textbprice;

    @FXML
    private DatePicker dtbdob;

    @FXML
    private ImageView imageset;

    @FXML
    private Label lblpath;

    @FXML
    private ComboBox<String> combox;

    @FXML
    void dochoosepic(ActionEvent event) {
    	try{
    	FileChooser fileChooser = new FileChooser();
    	File file=fileChooser.showOpenDialog(null);
    Image image=new Image(file.toURI().toURL().toString());
    	imageset.setImage(image);
    	lblpath.setText(file.getAbsolutePath());
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    }

    @FXML
    void dodelete(ActionEvent event) {
    	try 
		{
			String name=combox.getSelectionModel().getSelectedItem();
			
			//              									   tableName	
		PreparedStatement pst=	con.prepareStatement("delete from customer where name=?");
		pst.setString(1,name);
		
		int count=pst.executeUpdate();
		message.setText("Record deleted ");
		combox.getSelectionModel().clearSelection();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//in parameters
		
    }

    @FXML
    void dofetch(ActionEvent event) {
message.setText("");
    
    	
    	try {
    		String name=combox.getSelectionModel().getSelectedItem();
			PreparedStatement pst=con.prepareStatement("select * from customer where name=?");
			pst.setString(1, name);
			ResultSet table=pst.executeQuery();
		
		boolean jasus=false;
		while(table.next())
		{
			jasus=true;
			
			String mobile=table.getString("mobile");
			String address=table.getString("address");
			float  cq=table.getFloat("cquan");
			float  cp=table.getFloat("cprice");
			float  bq=table.getFloat("bquan");
			float  bp=table.getFloat("bprice");
			String imagef=table.getString("image");
			java.sql.Date dob=table.getDate("dos");
			//System.out.println(roll+"\t"+per+"\t"+name+"\t"+dob.toString());
			textaddress.setText(address);
			textmobile.setText(mobile);
			dtbdob.getEditor().setText(dob.toString());
			textcquan.setText(String.valueOf(cq));
			textcprice.setText(String.valueOf(cp));
			textbquan.setText(String.valueOf(bq));
			textbprice.setText(String.valueOf(bp));
			lblpath.setText(imagef);
			File file=new File(imagef);
			Image image=new Image(file.toURI().toString());
			imageset.setImage(image);
		}
		
		if(jasus==false)
			message.setText("Invalid Customer Number");
		else
			message.setText("Data is as shown");
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }

    @FXML
    void donew(ActionEvent event) {
    	combox.getSelectionModel().clearSelection();
    	textaddress.clear();
    	textmobile.clear();
    	textbprice.clear();
    	textbquan.clear();
    	textcprice.clear();
    	textcquan.clear();
    	dtbdob.getEditor().clear();
    	message.setText("");
    	imageset.setImage(null);
    lblpath.setText("");
    combox.getItems().clear();
    fillCombo();
    }

    @FXML
    void doupdate(ActionEvent event) {
    	try 
		{
    		String name=combox.getSelectionModel().getSelectedItem();
			float cowq=Float.parseFloat(textcquan.getText());
			float cowp=Float.parseFloat(textcprice.getText());
			float buffq=Float.parseFloat(textbquan.getText());
			float buffp=Float.parseFloat(textbprice.getText());
			
		LocalDate ldob=	dtbdob.getValue();

		java.sql.Date swdob=null;
		String stwdob="";
		if(ldob==null)
			{
				stwdob=dtbdob.getEditor().getText();
				ldob=LocalDate.parse(stwdob);
			}
			swdob= java.sql.Date.valueOf(ldob);
		
			//              									   tableName	
		PreparedStatement pst=	con.prepareStatement("update customer set mobile=?,address=?,cquan=?,cprice=?,bquan=?,bprice=?,dos=?,image=? where name=?");
		pst.setString(9, name);
		pst.setString(1, textmobile.getText());
		pst.setString(2, textaddress.getText());
		pst.setFloat(3,cowq);
		pst.setFloat(4,cowp);
		pst.setFloat(5,buffq);
		pst.setFloat(6,buffp);
		pst.setDate(7, swdob);
		pst.setString(8,lblpath.getText());
		message.setText("Updated..");
		pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//in parameters
		
    }

    @FXML
    void save(ActionEvent event) {
    	try 
		{
    		if(textaddress.getText().equals("")||textbprice.getText().equals("")||textbquan.getText().equals("")||textcprice.getText().equals("")||textcquan.getText().equals("")||textmobile.getText().equals(""))
    		{	
    			Alert a=new Alert(AlertType.NONE);
    			a.setAlertType(AlertType.WARNING);
    			a.show();
    			/*TextInputDialog dialog = new TextInputDialog("");
    			dialog.setTitle("Input Data...");
    			//dialog.setContentText("Please enter Mobile number:");

    			// Traditional way to get the response value.
    			Optional<String> result = dialog.showAndWait();*/
    					
    			
    		}
    		else{
			String name=combox.getSelectionModel().getSelectedItem();
			float cowq=Float.parseFloat(textcquan.getText());
			float cowp=Float.parseFloat(textcprice.getText());
			float buffq=Float.parseFloat(textbquan.getText());
			float buffp=Float.parseFloat(textbprice.getText());
			
		LocalDate ldob=	dtbdob.getValue();
		java.sql.Date swdob= java.sql.Date.valueOf(ldob);
			//              									   tableName	
		PreparedStatement pst=	con.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");
		pst.setString(1,name);
		pst.setString(2, textmobile.getText());
		pst.setString(3,textaddress.getText());
		pst.setFloat(4,cowq);
		pst.setFloat(5,cowp);
		pst.setFloat(6,buffq);
		pst.setFloat(7,buffp);
		pst.setDate(8, swdob);
		pst.setString(9,lblpath.getText());
		pst.executeUpdate();
		message.setText("Saved.. successfully");
    		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//in parameters
		
    }
    void fillCombo()
    {
    	ArrayList<String> nameArray=new ArrayList<>();
    	try {
			PreparedStatement pst=con.prepareStatement("select  name from customer");
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
    	
    }
Connection con=dbconn.makeconnection();
    @FXML
    void initialize() {
      //fillCombo();
textbprice.setText("0.0");
textbquan.setText("0.0");
textcprice.setText("0.0");
textcquan.setText("0.0");
    }
}
