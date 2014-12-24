package algorithmTestSinhala;

public class BaseFormCompareForSinhalaWords {

	public static void main(String args[]){
		
//		char c = 'අ';
//		char cc = 'ආ';
//		System.out.println((int)cc);		
//		System.out.println((char)3462);		
//		System.out.println((char)3461+""+(char)3535);
		 
		TrieNode root = new TrieNode();
		//insertString(root, "abcdef");
        //insertString(root, "abchtys");
       // insertString(root, "කිරීමෙන්");
       // insertString(root, "කිරීමක");
        
//		String s1 = "මිලදී";
//		String s2 = "මිල";
//		if(s1.contains(s2)){
//			System.out.println(s2);
//		}else if(s2.contains(s1)){
//			System.out.println(s1);			
//		}else{
//			insertString(root,s1 );
//         	insertString(root, s2); 
//		}
       // insertString(root, "මිලට");
       // insertString(root, "මිලෙන්");
        insertString(root, "මිලදී");
        
        //insertString(root, "කරනවා");
       // insertString(root, "කිරීම");
       // printSorted(root, "");
        //System.out.println("##############");
        printLongestPrefix(root,"");
	}
	
	static class TrieNode {
        TrieNode[] children = new TrieNode[10000];
        boolean leaf;
        int edgeCount=0;
    }

    public static void insertString(TrieNode root, String s) {
        TrieNode v = root;
        for (char ch : s.toCharArray()) {
            TrieNode next = v.children[ch];
            if (next == null) {
                v.children[ch] = next = new TrieNode();
                v.edgeCount++;
            }
            v = next;
        }
        v.leaf = true;
    }

    public static void countPrifix(TrieNode node, String s) {
        TrieNode v = node;
        for (char ch : s.toCharArray()) {
            v = v.children[ch];
        }
        int count = 0;
        for (int i = 0; i < v.children.length; i++) {
            if (v.children[i] != null) {
                count++;
                System.out.println((char) i);
            }
        }
        System.out.println(count);
        printSorted(v, "");
    }

    public static void printSorted(TrieNode node, String s) {
        for (char ch = 0; ch < node.children.length; ch++) {
            TrieNode child = node.children[ch];
            if (child != null) {
            	System.out.println("edgeCount : "+child.edgeCount);
            	System.out.println(s+ch);
                printSorted(child, s + ch);
            }
        }
        if (node.leaf) {
            System.out.println(s);
        }
    }
    
     
    
    public static void printLongestPrefix(TrieNode node, String s) {    	 
        for (char ch = 0; ch < node.children.length; ch++) {
            TrieNode child = node.children[ch];
            if (child != null) {            	
            	if(child.edgeCount>1){
            		System.out.println(s+ch);
            		return;
            	}
            	//System.out.println(s+ch+"   "+ch);
            	System.out.println(ch);
                printLongestPrefix(child, s + ch);               
            }            
        }
        if (node.leaf) {
            System.out.println(s);
            return;
        }
    }
} 

