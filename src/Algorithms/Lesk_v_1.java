package Algorithms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.dictionary.Dictionary;

public class Lesk_v_1 {

	public static Dictionary dictionary;
	private static  IndexWord WORD;
	private static String context;
	private static String word;	
	
	public static void main(String arg[]) throws FileNotFoundException, JWNLException{		
		
		FileInputStream inputStream = new FileInputStream("F:\\CSE\\vnb\\University\\7Semester7\\FYP\\FYP Projects Work\\LastYearProject\\WordNet\\Sinhala-WordNet-API-master\\SinhalaWordNetAPI\\src\\extjwnl\\src\\main\\resources\\net\\sf\\extjwnl\\file_properties.xml");
        //F:\CSE\vnb\University\7Semester7\FYP\FYP Projects Work\LastYearProject\WordNet\Sinhala-WordNet-API-master\SinhalaWordNetAPI\src\extjwnl\src\main\resources\net\sf\extjwnl
		dictionary= Dictionary.getInstance(inputStream);
        
		getUserData();
		
		String meaning = leskSolve(context, word);
		System.out.println(meaning);
        
	}
	
	public static String leskSolve(String userContext ,String word) throws JWNLException{
		// how to identify it is a noun or verb or etc....
		WORD = dictionary.getIndexWord(POS.NOUN, word);
		List<String> glosses = indexedWordCheck(WORD);
		String meaning =findBestMatch(glosses,userContext.toLowerCase());		
		
		return meaning;
	}
	
	public static String findBestMatch(List<String> glosses,String userContext){
		// ignore below
		List<String> ignoreWrds = Arrays.asList("a","the", "of",  "in", "at","on");
		
		// to check  which gloss is the most suitable 
		int sol[] = new int[glosses.size()];
		int i=-1;
		
		// replace other characters and get the user context as a list of Strings
		List<String> context = Arrays.asList(userContext.replaceAll("[^a-zA-Z ]", "").split(" "));
		
		for(String gloss : glosses){
			i++;
			List<String> gloss_  = Arrays.asList(gloss.replaceAll("[^a-zA-Z ]", "").split(" ")); 
			 
				for(String s : context){
					if(ignoreWrds.contains(s))continue;
					for(String glo : gloss_){
						if(!ignoreWrds.contains(glo) && (s.contains(glo) || glo.contains(s))){
							sol[i]++;
						}
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
	
	public static void getUserData(){
		Scanner sc = new Scanner(System.in);
		context = sc.nextLine();
		word = sc.nextLine();
		sc.close();
		System.out.println("බට");
		System.out.println("############### user data ###############");
		System.out.println("Context : "+context);
		System.out.println("Word : "+word);
		System.out.println("#########################################");
	}
	
}
