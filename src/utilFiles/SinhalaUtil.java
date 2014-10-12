package utilFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
 

public class SinhalaUtil {
	
	public   FileInputStream inputStream ;
	public   BufferedReader br;

	 
	public SinhalaUtil() throws FileNotFoundException {
		super();
		this.inputStream = new FileInputStream(new File(".\\src\\utilFiles\\SkipWords"));	
		this.br =new BufferedReader(new InputStreamReader(inputStream));
	}

	public List<String> createSkipWordsList() throws IOException{
		List<String> skipWordsList =  new ArrayList<>();
		String s ="බට";
		while(s!=null){
			s =  br.readLine();
			//System.out.println(s);
			if(s!=null){
				skipWordsList.add(s);
			}
		}
		return skipWordsList;
	}
	
	public static void main(String arg[]) throws IOException {				 
	       List<String> list = new  SinhalaUtil().createSkipWordsList();
	       for(String s : list){
	    	   System.out.println(s);
	       }	       
	}
		
}
