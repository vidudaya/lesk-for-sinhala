package algorithmTestSinhala;

import java.io.FileInputStream;
import java.io.FileNotFoundException; 
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.data.list.PointerTargetTree;
import net.sf.extjwnl.dictionary.Dictionary;

public class HyponymyAndHypernymyTest {

	public    Dictionary dictionary;
	public    FileInputStream inputStream;
	private  static IndexWord WORD;
	
	public HyponymyAndHypernymyTest() throws FileNotFoundException, JWNLException {	
		// relevant path for file_properties.xml file
		inputStream = new FileInputStream("F:\\CSE\\vnb\\University\\7Semester7\\FYP\\FYP Projects Work\\LastYearProject\\WordNet\\Sinhala-WordNet-API-master\\SinhalaWordNetAPI\\src\\extjwnl\\src\\main\\resources\\net\\sf\\extjwnl\\file_properties.xml");
		dictionary = Dictionary.getInstance(inputStream);  
		//WORD = dictionary.getIndexWord(POS.NOUN, "cat"); 
		WORD = dictionary.lookupIndexWord(POS.NOUN, "ස්වාමියා");
	}
	
	public static void main(String arg[]) throws FileNotFoundException, JWNLException{	
		
		HyponymyAndHypernymyTest hyponymyAndHypernymyTest = new HyponymyAndHypernymyTest();
		
		hyponymyAndHypernymyTest.demonstrateListOperation(WORD);
		System.out.println("###################################");
		hyponymyAndHypernymyTest.demonstrateTreeOperation(WORD);
		
	}
	
	 public void demonstrateListOperation(IndexWord word) throws JWNLException {
	        // Get all of the hypernyms (parents) of the first sense of <var>word</var>
	        PointerTargetNodeList hypernyms = PointerUtils.getDirectHypernyms(word.getSenses().get(0));
	        System.out.println("Direct hypernyms of \"" + word.getLemma() + "\":");
	        hypernyms.print();
	 }
	 public void demonstrateTreeOperation(IndexWord word) throws JWNLException {
	        // Get all the hyponyms (children) of the first sense of <var>word</var>
	        PointerTargetTree hyponyms = PointerUtils.getHyponymTree(word.getSenses().get(0));
	        System.out.println("Hyponyms of \"" + word.getLemma() + "\":");
	        hyponyms.print();
	  }
}
