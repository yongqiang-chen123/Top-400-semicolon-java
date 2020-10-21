package hw_8;
import java.io.*;
import java.util.Scanner;
import java.net.URL;
import java.net.URLConnection;

public class Semicolon {
	final static int TOO_LONG=2000;
	public static void main(String []args) throws Exception{
		String path="/Users/chenyuwang/Desktop/Top500-semicolon.csv";
		File f=new File(path);
		PrintWriter fw=new PrintWriter(new File("errors.csv"));
		String[]line = null;
		String name="";
		String urlName="";
		if (f.isFile()) {
			Scanner infile= new Scanner(f);
			while(infile.hasNextLine()) {
				line=infile.nextLine().split(";");
				for(int i=0;i<line.length;i+=2) {
					name=line[i];
				}
				for(int i=1;i<line.length;i+=2) {
					urlName=line[i];
				}
				try {
					URL url=new URL("http://"+urlName);
					URLConnection conn=url.openConnection();
					conn.setConnectTimeout(TOO_LONG);
					conn.setReadTimeout(TOO_LONG);
					InputStream inputStream=conn.getInputStream();
					inputStream.close();
					}
				catch(Exception e){
					fw.println(name+"; "+e.getMessage());
				}
				
			}
			fw.close();
		}
			
	}
}
