import java.util.*;
import java.lang.*;
public class StringQueries {
// there are total 256 ascii values
	
	public static char maxOccurance(String s){
		int[] arr = new int[256];
		Arrays.fill(arr, 0);
		for(int i=0; i < s.length(); i++){
			arr[s.charAt(i)]++;
		}
		int max = -1;
		int pos = 0;
		for(int i=0; i<256; i++){
			if(arr[i] > max){
				max = arr[i];
				pos = i;
			}
		}
		return (char)pos;
	}
	
	public static char kthNonRepeating(String s, int k){
		int[] count = new int[256];
		int[] index = new int[256];
		Arrays.fill(count, 0);
		Arrays.fill(index, s.length()+1);
		for(int i=0; i< s.length(); i++){
			count[s.charAt(i)]++;
			if(count[s.charAt(i)] == 1){
				index[s.charAt(i)] = i;
			}else{
				index[s.charAt(i)] = s.length()+1; //index not in string
			}
		}
		Arrays.sort(index);
		
		return s.charAt(index[k-1]);
	}
	
	public static String decryptedKthChar(String s){
		
		
		String res1 = "";
		for(int i=0; i < s.length(); ){
			String res = "";
			int freq = 0;
			while(i < s.length() && s.charAt(i) >= 'a' && s.charAt(i) <= 'z'){
				res = res + s.charAt(i);
				i++;
			}
			
				while(i < s.length() && s.charAt(i) >= '1' && s.charAt(i) <= '9'  ){
					 freq = freq*10 + s.charAt(i)-'0';
					
					i++;
				}
				
				for(int j=1; j<=freq; j++){
					res1 = res1 + res;
				}
				
			}
		
		return res1;
	}
	
	public static char maxConsequtive(String s){
		int max = 0;
		int count = 0;
		char c = s.charAt(0);
		char ans = 0;
		for(int i=0; i<s.length(); i++){
			if(c == s.charAt(i)){
				count++;
			}else{
				if(count > max){
					max = count;
					ans = s.charAt(i-1);
				}
				c = s.charAt(i);
				count = 1;
			}
		}
		if(count > max){
			max = count;
			ans = s.charAt(s.length()-1);
		}
		return ans;
	}
	// kunnuth morris - check if a is present in s or not O(m+n)
	public static int searchKMP(String a, String s){
		int[] temp = new int[a.length()];
		int j =0;
		for(int i =1; i<a.length(); i++){
			if(a.charAt(i) == a.charAt(j)){
				j++;
				temp[i] = j;
			}else{
				if(j == 0){
					temp[i] = 0;
				}else{
					j = temp[j-1];
					i--;
				}
			}
		}
		j = 0;
		
		int i=0;
		for( i=0; i<s.length(); i++){
			if(s.charAt(i) == a.charAt(j)){	
				j++;
				if(j == a.length() - 1){
					i++;
					break;
					
				}
			}
			
			else{
				if(j != 0){
					j = temp[j-1];
					i--;
				}
				
			}
		}
			if((i-j) >= s.length()){
				return -1;
			}else{
				return i-j;
			}	
	}
	
	public static void edmandKarpStrMatch(String a, String s){
		int q = 3;
		int h = 0;
		int p = 0;
		for(int i=0; i<a.length(); i++){
			h = h+ (a.charAt(i)-'a'+1)*((int)Math.pow(q, i));
			p = p+ (s.charAt(i)-'a'+1)*((int)Math.pow(q, i));
		}
		
		for(int i=0; i<=s.length()-a.length(); i++){
			if(h == p){
				int j = 0;
				for(j = 0; j<a.length(); j++){
					if(s.charAt(i+j) != a.charAt(j)){
						break;
					}
				}
					if(j == a.length()){
						System.out.println("Substring at position "+ i);
				}
			}
			else{
				p = ((p-(s.charAt(i)-'a'+1))/q) + ((s.charAt(i+ a.length())-'a'+1)*((int)Math.pow(q, a.length()-1)));
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "testteement";
		String s1 = "geeksforgeeks";
		
		System.out.println(maxOccurance(s));
		System.out.println(kthNonRepeating(s1,3));
		String s3 = "a2b2c3";
		System.out.println(decryptedKthChar(s3));
		System.out.println((int)'0');
		String s4 = "aaaabbcbbb";
		String s5 = "geekkssss";
		System.out.println(maxConsequtive(s5));
		System.out.println(searchKMP("abcaby","abxabyabcaby"));
		edmandKarpStrMatch("abcaby","abxabyabcaby");
	}

}
