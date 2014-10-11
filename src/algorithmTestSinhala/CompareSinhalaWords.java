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
	}
	
}
