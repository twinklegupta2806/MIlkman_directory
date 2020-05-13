package totaltable;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import customers.dbconn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  


public class tableViewController {

	  URL url;
	   	Media media;
	   	MediaPlayer mediaplayer;
	   	AudioClip audio;
	       void playSong()
	       {
	       	
	       	url=getClass().getResource("bg.mp3");
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
	       void doexporttoexcel(ActionEvent event) {
	    	   playSound();
	         export();
	       }

	       
	Connection con;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<CustomerBean> table;

    @FXML
    private DatePicker dtpdate;

    @FXML
    private RadioButton cow;

    @FXML
    private ToggleGroup groupby;

    @FXML
    private RadioButton buffalo;

   PreparedStatement rpst=null;
    
   @FXML
   void doshowall(ActionEvent event) {
try {
	playSound();
	PreparedStatement pst=con.prepareStatement("select * from customer");
	rpst=pst;
	getAllRecordsFromTable(pst);	
	table.setItems(list);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

   }

    
    @FXML
    void dofetchbydate(ActionEvent event) {
    	try {
    		playSound();
    		LocalDate ldob=dtpdate.getValue();
    		java.sql.Date sqwdob=java.sql.Date.valueOf(ldob);
    		PreparedStatement pst=con.prepareStatement("select * from customer where dos=?");
    		pst.setDate(1, sqwdob);
    		rpst=pst;
    		getAllRecordsFromTable(pst);
    		table.setItems(list);
    		
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

    @FXML
    void dofetchbyquantity(ActionEvent event) {
    	try {
playSound();
    		PreparedStatement pst;
    		if(cow.isSelected())
    			 pst=con.prepareStatement("select * from customer where bquan=?");
    		else 
    			 pst=con.prepareStatement("select * from customer where cquan=?");	      
    		pst.setFloat(1, 0.0f);
    		rpst=pst;
    		getAllRecordsFromTable(pst);
    		table.setItems(list);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

   void export()
   {
	   short counter=0;
	   FileOutputStream fileOut = null; 
	   String filename = "E:/newtable2.xls" ;
	   File file=new File(filename);
	   HSSFWorkbook hwb = new HSSFWorkbook();  
       HSSFSheet sheet = hwb.createSheet("new sheet");  
       System.out.println("where is control");
       HSSFRow rowhead=  sheet.createRow((short)0);  
       rowhead.createCell((short) 0).setCellValue("Name");//Row Name1  
       rowhead.createCell((short) 1).setCellValue("Mobile");//Row Name2  
       rowhead.createCell((short) 2).setCellValue("Address");//Row Name3 
       rowhead.createCell((short) 3).setCellValue("Cow Qty");
       rowhead.createCell((short) 4).setCellValue("Cow Price");
       rowhead.createCell((short) 5).setCellValue("Buff Qty");
       rowhead.createCell((short) 6).setCellValue("Buff Price");
       rowhead.createCell((short) 7).setCellValue("Date of Start");
      // rowhead.createCell((short) 7).setCellValue("Date Of Start");
       rowhead.createCell((short) 8).setCellValue("Image Path");
      // rowhead.createCell((short) 9).setCellValue("Image Path");
      System.out.println("here it is");
       counter+=1;
       try {
		PreparedStatement pst=rpst;
		ResultSet rs=pst.executeQuery();
	       while(rs.next()){  
	           //Insertion in corresponding row  
	           HSSFRow row=  sheet.createRow((int)counter);  
	           row.createCell((short) 0).setCellValue(rs.getString("name"));  
	           row.createCell((short) 1).setCellValue(rs.getString("mobile"));  
	           row.createCell((short) 2).setCellValue(rs.getString("address")); 
	           row.createCell((short) 3).setCellValue(rs.getFloat("cquan")); 
	           row.createCell((short) 4).setCellValue(rs.getFloat("cprice")); 
	           row.createCell((short) 5).setCellValue(rs.getFloat("bquan")); 
	           row.createCell((short) 6).setCellValue(rs.getFloat("bprice")); 
	           java.sql.Date jdb=rs.getDate("dos");
	           row.createCell((short) 7).setCellValue(jdb.toString());  
	           row.createCell((short) 8).setCellValue(rs.getString("image"));  
	           System.out.println("i dont know what to do");
	           counter ++;  
	           try {  
	             //For performing write to Excel file  
	             fileOut = new FileOutputStream(filename);  
	             hwb.write(fileOut);  
	             fileOut.close(); 
	           } catch (IOException e) {  
	             e.printStackTrace();  
	           }  
	           rpst=null; 
	         }  
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
   }

   
   
    ResultSet tablesqwl;
   
    ObservableList<CustomerBean> list;
    void getAllRecordsFromTable(PreparedStatement pst)//2
    {
    	list=FXCollections.observableArrayList();
    	try {
			
    		 tablesqwl=	pst.executeQuery();
		while(tablesqwl.next())
		{
			String name=tablesqwl.getString("name");
			String mobile=tablesqwl.getString("mobile");
			String address=tablesqwl.getString("address");
			float cquan=tablesqwl.getFloat("cquan");
			float cprice=tablesqwl.getFloat("cprice");
			float bquan=tablesqwl.getFloat("bquan");
			float bprice=tablesqwl.getFloat("bprice");
			java.sql.Date sqwldate=tablesqwl.getDate("dos");
			String dos=sqwldate.toString();
			CustomerBean obj=new CustomerBean(name, mobile, address, cquan, cprice, bquan, bprice, dos);
			list.add(obj);	
		}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    } 
    
    
    @FXML
    void initialize() {
    	 con= dbconn.makeconnection();
    	 TableColumn<CustomerBean, String> name=new TableColumn<CustomerBean, String>("Customer Name");
      	name.setCellValueFactory(new PropertyValueFactory<>("name"));
      	TableColumn<CustomerBean, String> mobile=new TableColumn<CustomerBean, String>("Mobile");
      	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
      	TableColumn<CustomerBean, String> address=new TableColumn<CustomerBean, String>("Address");
      	address.setCellValueFactory(new PropertyValueFactory<>("address"));
      	TableColumn<CustomerBean, Float> cq=new TableColumn<CustomerBean, Float>("Cow milk");
      	cq.setCellValueFactory(new PropertyValueFactory<>("cquan"));
      	TableColumn<CustomerBean, Float> cp=new TableColumn<CustomerBean, Float>("C price");
      	cp.setCellValueFactory(new PropertyValueFactory<>("cprice"));
      	TableColumn<CustomerBean, Float> bq=new TableColumn<CustomerBean, Float>("Buffalo milk");
      	bq.setCellValueFactory(new PropertyValueFactory<>("bquan"));
      	TableColumn<CustomerBean, Float> bp=new TableColumn<CustomerBean, Float>("B price");
      	bp.setCellValueFactory(new PropertyValueFactory<>("bprice"));
      	 TableColumn<CustomerBean, String> dos=new TableColumn<CustomerBean, String>("Date Of Start");
       	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));

      	table.getColumns().addAll(name,mobile,address,cq,cp,bq,bp,dos);
      	
    }
}
