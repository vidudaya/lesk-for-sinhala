package algorithmTestSinhala;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.dictionary.Dictionary;

public class GlossesAndSensesCheck {
	public    Dictionary dictionary;
	public    FileInputStream inputStream;
	private  static IndexWord WORD;
	
	public GlossesAndSensesCheck() throws FileNotFoundException, JWNLException {	
		// relevant path for file_properties.xml file
		inputStream = new FileInputStream("F:\\CSE\\vnb\\University\\7Semester7\\FYP\\FYP Projects Work\\LastYearProject\\WordNet\\Sinhala-WordNet-API-master\\SinhalaWordNetAPI\\src\\extjwnl\\src\\main\\resources\\net\\sf\\extjwnl\\file_properties.xml");
		dictionary = Dictionary.getInstance(inputStream);  
		
		WORD = dictionary.getIndexWord(POS.NOUN, "මුව");
		//WORD = dictionary.lookupIndexWord(POS.NOUN, "මුව");
		
	}
	
	public static void main(String arg[]) throws FileNotFoundException, JWNLException{	
		
		GlossesAndSensesCheck glossesAndSensesCheck = new GlossesAndSensesCheck();		
		 
		glossesAndSensesCheck.demonstrateGlosses(WORD);		 
		
	}
	
	 public void demonstrateGlosses(IndexWord word) throws JWNLException {
		 	List<Synset> list = WORD.getSenses();
			System.out.println(list.toString()); 
			System.out.println("################# Gloss ##################");
			for(Synset syn : list){
				// next line will print all the glosses 
				System.out.println(syn.getGloss());						 
			}
			System.out.println("##########################################");
	 }
	
}
