package algorithmUse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import net.sf.extjwnl.JWNLException;
import algorithms.Lesk_v_1;

public class EvaluateLeskForSinhala {
	
	public   FileInputStream inputStream ;
	public   Scanner sc;
	public   BufferedReader br;

	 
	public EvaluateLeskForSinhala() throws FileNotFoundException {
		super();
		this.inputStream = new FileInputStream(new File(".\\src\\utilFiles\\TestCasesForLesk"));	
		this.br =new BufferedReader(new InputStreamReader(inputStream));
		this.sc = new Scanner(System.in);
	}
	
	public void evaluate(Lesk_v_1 lesk) throws NumberFormatException, IOException, JWNLException{
		String result = "";
		ArrayList<String> list = new ArrayList<>();		
		int n = Integer.parseInt(br.readLine());
		String wordSet[] = new String[n];
		
		for (int i = 0; i < n; i++) {
			String word = br.readLine().trim();
			lesk.setWord(word);
			wordSet[i]=word;
			for (int j = 0; j < 5; j++) {
				String context = br.readLine().trim();
				lesk.setUserContext(context);
				String meaning = lesk.leskSolve();
				System.out.println("Meaning : "+meaning);
				list.add(word+" : "+context+" : meaning : "+meaning);
			}
			br.readLine();
		}
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("============================================================");
		userTest(list, n , wordSet);		 
	}
	
	public void userTest(ArrayList<String> list ,int n, String []wordSet){
		int successCount[] = new int[n];
		double resultsPrecision[] = new double[n];
		double resultsFscore[] = new double[n];
		int j=0;
		for (int i = 1; i <= list.size(); i++) {
			System.out.println(list.get(i-1));
			String ans = sc.nextLine().toLowerCase();
			if("y".equals(ans)){				 
				successCount[(i-1)/5]++;
			}
			j++;
			if(j==5){
				System.out.println(successCount[(i-1)/5]);
				System.out.println(Arrays.toString(successCount));
				j=0;
			}
			System.out.println("============================================================");			
		}
		
		for (int i = 0; i < successCount.length; i++) {
			resultsPrecision[i]=successCount[i]/5.0;
			resultsFscore[i] = (2*resultsPrecision[i]*resultsPrecision[i])/(resultsPrecision[i]+resultsPrecision[i]);
		}
		
		System.out.println("the number of target words for which our answer matches the human decided answer");
		for (int i = 0; i < wordSet.length; i++) {
			System.out.print(wordSet[i]+"\t");
		}
		System.out.println("");
		for (int i = 0; i < successCount.length; i++) {
			System.out.print(successCount[i]+"\t");
		}
		System.out.println("");
		//System.out.println(wordSet);
		//System.out.println(Arrays.toString(successCount));
		System.out.println("the number of target words for which our answer matches the human decided answer, divided by the number of instances for which answers have been returned by our system..");
		System.out.println(Arrays.toString(resultsPrecision));
		System.out.println("the relevant F-Scores");
		System.out.println(Arrays.toString(resultsFscore));
		
		/*
		 * 
		 * 
		 * the number of target words for which our answer matches the human decided answer
[Ljava.lang.String;@82a5e75
[3, 4, 5, 2, 0, 3]
the number of target words for which our answer matches the human decided answer, divided by the number of instances for which answers have been returned by our system..
[0.6, 0.8, 1.0, 0.4, 0.0, 0.6]
the relevant F-Scores
[0.6, 0.8000000000000002, 1.0, 0.4000000000000001, NaN, 0.6]
		 */
	}
	

	public static void main(String args[]) throws JWNLException, NumberFormatException, IOException{
		
		Lesk_v_1 lesk = new Lesk_v_1();	
		new EvaluateLeskForSinhala().evaluate(lesk);		
		
		// example : කොළඹ පිටත වටරවුම් අධිවේගී මාර්ගයේ ඉදිකිරීම් සදහා රැගෙන ආ යකඩ බට සොරකම්කිරීමේ සිද්ධියකට සම්බන්ධ සැකකරුවන් 6 දෙනෙකු අත්අඩංගුවට ගෙන තිබෙන අතරසැකකරුවන් අතර කිරුළපන පොලිස් ස්ථානයට අනුයුක්ත සැරයන්වරයෙකුද සිටින බව පැවසේ.
		// words : බට
	}
	
}
