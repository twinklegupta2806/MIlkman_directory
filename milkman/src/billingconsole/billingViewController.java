package billingconsole;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

import customers.dbconn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import variationtable.VariationBean;
import org.apache.poi.hssf.usermodel.HSSFRow;  
import org.apache.poi.hssf.usermodel.HSSFSheet;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  

public class billingViewController {

	Connection con;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combox;

    @FXML
    private TableView<BillingBean> table;
PreparedStatement rpst=null;
    
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
    void doexport(ActionEvent event) {
    	playSound();
    	/* short counter=0;
  	   FileOutputStream fileOut = null; 
  	   String filename = "E:/newtable4.xls" ;
  	   File file=new File(filename);
  	   HSSFWorkbook hwb = new HSSFWorkbook();  
         HSSFSheet sheet = hwb.createSheet("new sheet");  
         System.out.println("where is control");
         HSSFRow rowhead=  sheet.createRow((short)0);  
         rowhead.createCell((short) 0).setCellValue("Name");//Row Name1  
         rowhead.createCell((short) 1).setCellValue("Date of Start");//Row Name2  
         rowhead.createCell((short) 2).setCellValue("Date of end");//Row Name3 
         rowhead.createCell((short) 3).setCellValue("Amount");
         rowhead.createCell((short) 4).setCellValue("Cow Qty Total");
         rowhead.createCell((short) 5).setCellValue("Buff Qty Total");
         rowhead.createCell((short) 6).setCellValue("Status");
         rowhead.createCell((short) 7).setCellValue("Date of Start");
                counter+=1;
         try {
  		PreparedStatement pst=rpst;
  		ResultSet rs=pst.executeQuery();
  	       while(rs.next()){  
  	           //Insertion in corresponding row  
  	           HSSFRow row=  sheet.createRow((int)counter);  
  	           row.createCell((short) 0).setCellValue(rs.getString("name"));  
  	         java.sql.Date jdb=rs.getDate("dos");
	           row.createCell((short) 1).setCellValue(jdb.toString()); 
	           java.sql.Date jdd=rs.getDate("doe");
	           row.createCell((short) 3).setCellValue(rs.getFloat("amount")); 
	           row.createCell((short) 4).setCellValue(rs.getFloat("cqtotal")); 
  	           row.createCell((short) 5).setCellValue(rs.getFloat("bqtotal")); 
  	          
  	           row.createCell((short) 6).setCellValue(rs.getInt("status")); 
  	          
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
         */
    
    	try {
    		
			writeExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    public void writeExcel() throws Exception {
        Writer writer = null;
        try {
        FileChooser chooser=new FileChooser();
   	
        chooser.setTitle("Select Path:");
        
        chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                    
                );
        File file=chooser.showSaveDialog(null);
        String filePath=file.getAbsolutePath();
        if(!(filePath.endsWith(".csv")||filePath.endsWith(".CSV")))
        {
        filePath=filePath+".csv";
        }
        file = new File(filePath);
        
            writer = new BufferedWriter(new FileWriter(file));
            String text="Name,DOS,DOE,C_QTY,B_QTY,Amount,Status\n";
            writer.write(text);
            for (BillingBean p : list)
            {
text = p.getName()+ "," + p.getDos()+ "," + p.getDoe()+ "," + p.getCqtotal()+","+p.getBqtotal()+ "," + p.getAmount()+","+p.getStatus()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally
        {
            writer.flush();
             writer.close();
        }
    }


    
    @FXML
    void doshowall(ActionEvent event) {
    	playSound();
try {
	PreparedStatement pst=con.prepareStatement("select * from bill");
	rpst=pst;
	getdetails(pst);
	table.setItems(list);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
    }

    @FXML
    void doshowbyname(ActionEvent event) {
    	playSound();
String name=combox.getSelectionModel().getSelectedItem();
try {
	PreparedStatement pst=con.prepareStatement("select * from bill where name=?");
	pst.setString(1,name);
	rpst=pst;
	getdetails(pst);
	table.setItems(list);
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}


    }

    @FXML
    void doshowpaid(ActionEvent event) {
    	playSound();
    	try {
    		PreparedStatement pst=con.prepareStatement("select * from bill where status=?");
    		pst.setInt(1,1);
    		rpst=pst;
    		getdetails(pst);
    		table.setItems(list);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}

    }

    @FXML
    void doshowunpaid(ActionEvent event) {
    	playSound();
    	try {
    		PreparedStatement pst=con.prepareStatement("select * from bill where status=?");
    		pst.setInt(1,0);
    		rpst=pst;
    		getdetails(pst);
    		table.setItems(list);
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}


    }
    ObservableList<BillingBean> list;
    
 void getdetails(PreparedStatement pst)
 {
	 try {
		ResultSet tablesqwl=pst.executeQuery();
		list=FXCollections.observableArrayList();
		while(tablesqwl.next())
		{
		String name=tablesqwl.getString("name");
		java.sql.Date sqwldate=tablesqwl.getDate("dos");
		String dos=sqwldate.toString();
		java.sql.Date sqwldateend=tablesqwl.getDate("doe");
		String doe=sqwldateend.toString();
		float amount=tablesqwl.getFloat("amount");
		float cqtotal=tablesqwl.getFloat("cqtotal");
		float bqtotal=tablesqwl.getFloat("bqtotal");
		int statusint=tablesqwl.getInt("status");
		String status=String.valueOf(statusint);
		BillingBean bb=new BillingBean(name, dos, doe, amount, cqtotal, bqtotal, status);
		list.add(bb);
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
			PreparedStatement pst=con.prepareStatement("select  name from bill");
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
    
    @FXML
    void initialize() {
    	con=dbconn.makeconnection();
       fillCombo();
       TableColumn<BillingBean, String> name=new TableColumn<BillingBean, String>("Customer Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));
    	 TableColumn<BillingBean, String> dos=new TableColumn<BillingBean, String>("Date of Start");
     	dos.setCellValueFactory(new PropertyValueFactory<>("dos"));
     	 TableColumn<BillingBean, String> doe=new TableColumn<BillingBean, String>("Date of End");
      	doe.setCellValueFactory(new PropertyValueFactory<>("doe"));
      	 TableColumn<BillingBean, Float> amount=new TableColumn<BillingBean, Float>("Amount");
       	amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
       	TableColumn<BillingBean, Float> cqtotal=new TableColumn<BillingBean, Float>("Total Cmilk");
       	cqtotal.setCellValueFactory(new PropertyValueFactory<>("cqtotal"));
       	TableColumn<BillingBean, Float> bqtotal=new TableColumn<BillingBean, Float>("Total Bmilk");
       	bqtotal.setCellValueFactory(new PropertyValueFactory<>("bqtotal"));
        TableColumn<BillingBean, String> status=new TableColumn<BillingBean, String>("Status");
    	status.setCellValueFactory(new PropertyValueFactory<>("status"));       
    	table.getColumns().addAll(name,dos,doe,amount,cqtotal,bqtotal,status);
    }
}
