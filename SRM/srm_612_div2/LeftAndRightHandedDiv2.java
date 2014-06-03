package srm_612_div2;

public class LeftAndRightHandedDiv2 {
	public int count(String S){
		int collsion = 0;
		int len = S.length();
		for(int i=0;i<len-1;i++){
			char curr = S.charAt(i);
			char next = S.charAt(i+1);
			if(curr == 'R' && next == 'L')
				collsion++;
		}
		return collsion;
	}
}
