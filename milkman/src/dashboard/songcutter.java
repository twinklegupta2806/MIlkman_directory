package dashboard;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class songcutter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
copysong();
		}
		catch(IOException ex){}
		//catch(FileNotFoundException rf){}
	}
static void copysong() throws IOException
{
FileInputStream read=null;
FileOutputStream write=null;
	File src=new File("we.m4a");
	read=new FileInputStream(src);
File f=new File("we9.m4a");
	write=new FileOutputStream(f);
	int c;
	int count=0;
	while(true)
	{
		c=read.read();
		               
		System.out.println("in loop"+count);
	
		//System.out.println(c);
		if(c==-1)
			break;
		if(count>300000)
			break;
		if(count<=300000)
			write.write(c);
		count++;
		    }
	System.out.println(count);
	System.out.println("file copied");
	read.close();
	write.close();
}
}

