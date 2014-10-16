package algorithmTestSinhala;

public class CompareSinhalaWords {

	public static void main(String args[]){
		String s1 = "බටයේ";
		String s2 = "බට";
		String s3 = "බටයක";
		
		if(s1.contains(s2)){
			System.out.println("True word "+s1+" contains word "+s2);
		}else{
			System.out.println("No");			
		}
		
		if(s2.contains(s1)){
			System.out.println("True word "+s2+" contains word "+s1);
		}else{
			System.out.println("No");			
		}
		
		if(s1.contains(s3)){
			System.out.println("True word "+s1+" contains word "+s3);
		}else{
			System.out.println("No");			
		}
		
		
		String d1 = "මුවින්";
		String d2 = "මුව";
		if(d1.contains(d2)){
			System.out.println("True word "+d1+" contains word "+d2);
		}else{
			System.out.println("No");			
		}
		
		System.out.println("length of word "+d1+" is "+d1.length());
		
		// using this , reducing until we end up with the same words , we can do similar thing to morphological analysis
	}
	
}
