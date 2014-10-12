package algorithms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import utilFiles.SinhalaUtil;
import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.dictionary.Dictionary;

public class Lesk_v_1 {

	public    Dictionary dictionary;
	public    FileInputStream inputStream;
	private   IndexWord WORD;
	private   String userContext;
	private   String word;	
	 	
	public Lesk_v_1() throws FileNotFoundException, JWNLException {	
		// relevant path for file_properties.xml file
		inputStream = new FileInputStream("F:\\CSE\\vnb\\University\\7Semester7\\FYP\\FYP Projects Work\\LastYearProject\\WordNet\\Sinhala-WordNet-API-master\\SinhalaWordNetAPI\\src\\extjwnl\\src\\main\\resources\\net\\sf\\extjwnl\\file_properties.xml");
		dictionary = Dictionary.getInstance(inputStream);   
	}

	public static void main(String arg[]) throws FileNotFoundException, JWNLException{	
		
		Lesk_v_1 lesk = new Lesk_v_1();
		lesk.getUserData();
		
		String meaning = lesk.leskSolve();
		System.out.println(meaning);
        
	}
	
	
	public String getUserContext() {
		return userContext;
	}

	public void setUserContext(String userContext) {
		this.userContext = userContext;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public  String leskSolve() throws JWNLException{
		// how to identify it is a noun or verb or etc....
		WORD = dictionary.getIndexWord(POS.NOUN, word);
		
		if(WORD==null){
			return "no match found for word "+word;
		}
		List<String> glosses = indexedWordCheck(WORD);
		String meaning =findBestMatch(glosses,userContext.toLowerCase());		
		
		return meaning;
	}
	
	public  String findBestMatch(List<String> glosses,String userContext){
		// ignore words list
		List<String> ignoreWrds = null;
		try {
			ignoreWrds = new SinhalaUtil().createSkipWordsList();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		// to check  which gloss is the most suitable 
		int sol[] = new int[glosses.size()];
		int i=-1;
		
		// replace other characters and get the user context as a list of Strings
		List<String> context = Arrays.asList(userContext.replaceAll("[|\"]", " ").split(" "));
		
		for(String gloss : glosses){			
			i++;
			List<String> gloss_  = Arrays.asList(gloss.replaceAll("[|\"]", " ").split(" ")); 
			 
				for(String s : context){					
					if(ignoreWrds!= null && ignoreWrds.contains(s))continue;
					for(String glo : gloss_){
						if(!ignoreWrds.contains(glo) && (s.contains(glo) || glo.contains(s))){
							sol[i]++;
						}else{}						
					}
				}			 
		}
		
		System.out.println(Arrays.toString(sol));
		int max =Integer.MIN_VALUE,pos=-1;
		for(i=0;i<sol.length;++i){
			if(max<sol[i] ){
				max=sol[i];
				pos=i;
			}
		}
		return glosses.get(pos);
	}
	public static List<String> indexedWordCheck(IndexWord WORD){
		
		String s = WORD.getLemma();		
		System.out.println("Lemma of the word = "+s);
		List<Synset> list = WORD.getSenses();
		List<String> glosses = new ArrayList<>();
		
		System.out.println("################# Gloss ##################");
		for(Synset syn : list){
			// next line will print all the glosses 
			System.out.println(syn.getGloss());			
			glosses.add(syn.getGloss().toLowerCase());
		}
		System.out.println("##########################################");
		return glosses;
	}
	
	public  void getUserData(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter data : ");
		userContext = sc.nextLine();
		word = sc.nextLine();
		sc.close();
		 
		System.out.println("############### user data ###############");
		System.out.println("Context : "+userContext);
		System.out.println("Word : "+word);
		System.out.println("#########################################");
	}
	
}