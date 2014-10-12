package algorithmUse;

import java.io.FileNotFoundException;
import java.util.Scanner;

import net.sf.extjwnl.JWNLException;
import algorithms.Lesk_v_1;

public class WSDforSinhala {

	public void solve(Lesk_v_1 lesk) throws JWNLException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the context : ");
		String context = sc.nextLine();
		lesk.setUserContext(context);
		String s = "බට";
		while(true){
			System.out.println("#################################################");
			System.out.println("Enter the word : ");
			String word = sc.nextLine();
			lesk.setWord(word);
			String meaning = lesk.leskSolve();
			System.out.println(meaning);
		}
	}
	
	public static void main(String args[]) throws FileNotFoundException, JWNLException{
		
		Lesk_v_1 lesk = new Lesk_v_1();	
		new WSDforSinhala().solve(lesk);
		
		
		// example : කොළඹ පිටත වටරවුම් අධිවේගී මාර්ගයේ ඉදිකිරීම් සදහා රැගෙන ආ යකඩ බට සොරකම්කිරීමේ සිද්ධියකට සම්බන්ධ සැකකරුවන් 6 දෙනෙකු අත්අඩංගුවට ගෙන තිබෙන අතරසැකකරුවන් අතර කිරුළපන පොලිස් ස්ථානයට අනුයුක්ත සැරයන්වරයෙකුද සිටින බව පැවසේ.
		// words : බට
	}
}
