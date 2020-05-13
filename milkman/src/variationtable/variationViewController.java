package variationtable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import customers.dbconn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import variationtable.VariationBean;
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  


public class variationViewController {

	Connection con;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combox;
    
    @FXML
    private DatePicker startdate;

    @FXML
    private DatePicker enddate;

    @FXML
    private TableView<VariationBean> table;
    
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

    ObservableList< VariationBean> list;
    
    PreparedStatement rpst=null;
    
    @FXML
    void displaytable(ActionEvent event) {
    	playSound();
    	PreparedStatement pst;
		try {
			pst = con.prepareStatement("select * from variations");
			rpst=pst;
			get(pst);
			table.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doexport(ActionEvent event) {
    	playSound();
    		   short counter=0;
    		   FileOutputStream fileOut = null; 
    		   String filename = "E:/newtable3.xls" ;
    		   File file=new File(filename);
    		   HSSFWorkbook hwb = new HSSFWorkbook();  
    	       HSSFSheet sheet = hwb.createSheet("new sheet");  
    	       System.out.println("where is control");
    	       HSSFRow rowhead=  sheet.createRow((short)0);  
    	       rowhead.createCell((short) 0).setCellValue("Customer Name");//Row Name1  
    	       rowhead.createCell((short) 1).setCellValue("Cow variation");//Row Name2  
    	       rowhead.createCell((short) 2).setCellValue("Buffalo Variation");//Row Name3 
    	       rowhead.createCell((short) 3).setCellValue("Current date");
    	      System.out.println("here it is");
    	       counter+=1;
    	       try {
    			PreparedStatement pst=rpst;
    			ResultSet rs=pst.executeQuery();
    		       while(rs.next()){  
    		           //Insertion in corresponding row  
    		           HSSFRow row=  sheet.createRow((int)counter);  
    		           row.createCell((short) 0).setCellValue(rs.getString("cname"));  
    		           row.createCell((short) 1).setCellValue(rs.getString("cowvar"));  
    		           row.createCell((short) 2).setCellValue(rs.getString("buffvar")); 
    		              		           java.sql.Date jdb=rs.getDate("currdate");
    		           row.createCell((short) 3).setCellValue(jdb.toString());  
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


    
    @FXML
    void dofetchname(ActionEvent event) {
    	playSound();
    	PreparedStatement pst;
		try {
			pst = con.prepareStatement("select * from variations where cname=?");
			pst.setString(1,combox.getSelectionModel().getSelectedItem());
			rpst=pst;
			get(pst);
			table.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void doshowbydate(ActionEvent event) {
    	playSound();
    	PreparedStatement pst;
		try {
			LocalDate ls=startdate.getValue();
			java.sql.Date sqldate=java.sql.Date.valueOf(ls);
			LocalDate ld=enddate.getValue();
			java.sql.Date edate=java.sql.Date.valueOf(ld);
			pst = con.prepareStatement("select * from variations where currdate>=? and currdate<=? and cname=?" );
			pst.setDate(1,sqldate);
			pst.setDate(2,edate);
			pst.setString(3,combox.getSelectionModel().getSelectedItem());
			rpst=pst;
			get(pst);
			table.setItems(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    void get(PreparedStatement pst)
    {
    	list=FXCollections.observableArrayList();
    	try {
			ResultSet tablesqwl=pst.executeQuery();
			while(tablesqwl.next()){
			String cname=tablesqwl.getString("cname");
			float cowv=tablesqwl.getFloat("cowvar");
			float buffv=tablesqwl.getFloat("buffvar");
			java.sql.Date sqldate=tablesqwl.getDate("currdate");
			String date=String.valueOf(sqldate);
			VariationBean obj=new VariationBean(cname, cowv, buffv, date);
			list.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    void fillCombo()
    {
    	ArrayList<String> nameArray=new ArrayList<>();
    	try {
			PreparedStatement pst=con.prepareStatement("select distinct  name from customer");
			ResultSet tablesqwl=pst.executeQuery();
		
		while(tablesqwl.next())
		{
			String name=tablesqwl.getString(1);
			nameArray.add(name);
		}
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    	combox.getItems().addAll(nameArray);
    	
    }
    
    @FXML
    void initialize() {
       con=dbconn.makeconnection();
       fillCombo();
       TableColumn<VariationBean, String> cname=new TableColumn<VariationBean, String>("Customer Name");
     	cname.setCellValueFactory(new PropertyValueFactory<>("cname"));
     	 TableColumn<VariationBean, Float> cq=new TableColumn<VariationBean,Float>("Cow Variation");
      	cq.setCellValueFactory(new PropertyValueFactory<>("cowvar"));
      	TableColumn<VariationBean, Float> bq=new TableColumn<VariationBean,Float>("Buffalo Variation");
      	bq.setCellValueFactory(new PropertyValueFactory<>("buffvar"));
     	TableColumn<VariationBean, String> dos=new TableColumn<VariationBean, String>("Date");
       	dos.setCellValueFactory(new PropertyValueFactory<>("date"));
       	table.getColumns().addAll(cname,cq,bq,dos);
       	}
}
